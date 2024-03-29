package de.riagade.genericDiscordBot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.support.CronExpression;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * a timed task that runs using a cron expression.<br>
 * will execute {@link #runTimedLogic()}.
 */
@Getter
@Setter
public abstract class A_TimedTask extends TimerTask {
    public static final long CHECK_INTERVAL = TimeUnit.SECONDS.toMillis(1);
    private String cronExpression;
    private A_FeedbackTask task;
    private A_BasicBot bot;

    /**
     * @param bot the {@link A_BasicBot} that can be used in this task
     * @param cronExpression the cron expression to calculate the next execution
     */
    public A_TimedTask(A_BasicBot bot, String cronExpression){
        setBot(bot);
        setCronExpression(cronExpression);
        setTask(createFeedbackTask());
    }

    /**
     * initiate the timer and checking every time if the {@link A_FeedbackTask}
     * should be scheduled, using CHECK_INTERVAL as an interval
     */
    public void initiate() {
        new Timer().scheduleAtFixedRate(this, new Date(), CHECK_INTERVAL);
    }

    @Override
    public void run() {
        if(!getTask().isRunning()){
            CronExpression expression = CronExpression.parse(getCronExpression());
            new Timer().schedule(getTask(),
                    Date.from(Objects.requireNonNull(expression.next(ZonedDateTime.now())).toInstant()));
            getTask().setRunning(Boolean.FALSE);
        }
    }

    /**
     * @return the new {@link A_FeedbackTask} that will be scheduled
     */
    private A_FeedbackTask createFeedbackTask() {
        return new A_FeedbackTask(getBot()){
            @Override
            public void runLogic() {
                runTimedLogic();
            }
        };
    }

    /**
     * implement your logic to run here
     */
    public abstract void runTimedLogic();
}
