package de.riagade.warframeBots.weeklyReminder;

import de.riagade.genericDiscordBot.A_BasicBot;
import de.riagade.genericDiscordBot.A_TimedTask;
import de.riagade.warframeBots.bots.nightwave.util.ChallengeDao;
import de.riagade.warframeBots.bots.nightwave.util.ChallengeUtil;
import de.riagade.warframeBots.bots.nightwave.util.enums.E_ChallengeType;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NoraWeeklyReminderTask extends A_TimedTask {

    public NoraWeeklyReminderTask(A_BasicBot bot, String cronExpression) {
        super(bot, cronExpression);
    }

    @Override
    public void runTimedLogic() {
        log.debug("weekly reminder running");
        List<ChallengeDao> challengeDaoList = generateMissions();
        for(ChallengeDao challengeDao : challengeDaoList){
            challengeDao.sendMessage(getBot());
        }
    }

    private List<ChallengeDao> generateMissions() {
        return ChallengeUtil.challengeList(getBot().getLocale(), E_ChallengeType.WEEKLY, E_ChallengeType.ELITE);
    }
}
