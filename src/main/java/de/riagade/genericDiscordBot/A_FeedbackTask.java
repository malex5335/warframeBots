package de.riagade.genericDiscordBot;

import lombok.Getter;
import lombok.Setter;

import java.util.TimerTask;

/**
 * a task which gives feedback of it's running state.<br>
 * the property {@link #running} says if the task is in execution or not.<br>
 * will execute {@link #runLogic()}.
 */
@Getter
@Setter
public abstract class A_FeedbackTask extends TimerTask{
    private boolean running;
    private A_BasicBot bot;

    /**
     * @param bot the {@link A_BasicBot} that can be used in this task
     */
    public A_FeedbackTask(A_BasicBot bot) {
        setBot(bot);
        setRunning(Boolean.FALSE);
    }

    @Override
    public void run() {
        runLogic();
        setRunning(Boolean.TRUE);
    }

    /**
     * a method to write the task in
     */
    public abstract void runLogic();
}
