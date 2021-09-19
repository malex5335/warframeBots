package de.riagade.warframeBots.dailyReminder;

import de.riagade.genericDiscordBot.A_BasicBot;
import de.riagade.genericDiscordBot.A_TimedTask;
import de.riagade.warframeBots.bots.nightwave.util.ChallengeDao;
import de.riagade.warframeBots.bots.nightwave.util.ChallengeUtil;
import de.riagade.warframeBots.bots.nightwave.util.enums.E_ChallengeType;
import java.util.List;

public class NoraDailyReminderTask extends A_TimedTask {

    public NoraDailyReminderTask(A_BasicBot bot, String cronExpression) {
        super(bot, cronExpression);
    }

    @Override
    public void runTimedLogic() {
        List<ChallengeDao> challengeDaoList = generateMissions();
        challengeDaoList.get(challengeDaoList.size() - 1).sendMessage(getBot());
    }

    private List<ChallengeDao> generateMissions() {
        return ChallengeUtil.challengeList(getBot().getLocale(), E_ChallengeType.DAILY);
    }
}
