package de.riagade.warframeBots.pastMessageCorrector;

import de.riagade.genericDiscordBot.A_BasicBot;
import de.riagade.warframeBots.bots.nightwave.util.ChallengeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class NoraPastMessagesCorrectorTask extends PastMessagesCorrectorTask {

    public NoraPastMessagesCorrectorTask(A_BasicBot bot, String cronExpression) {
        super(bot, cronExpression);
    }

    @Override
    protected List<String> getKeys() {
        return ChallengeUtil.getKeys();
    }

    @Override
    protected String getDescription(String key) {
        return ChallengeUtil.getDescription(key);
    }

    @Override
    protected boolean isRawContent(String newText) {
        return newText.contains("/Lotus/");
    }
}
