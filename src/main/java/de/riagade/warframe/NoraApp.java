package de.riagade.warframe;

import de.riagade.warframe.nightwave.NightwaveService;
import de.riagade.warframe.nightwave.adapters.*;
import de.riagade.warframe.nightwave.exceptions.MessagesNotSentException;
import de.riagade.warframe.status.StatusService;
import de.riagade.warframe.status.adapters.DiscordStatusSpreader;
import de.riagade.warframe.status.adapters.WebStatusHolder;
import de.riagade.warframe.status.exceptions.StatusNotUpdatedException;
import de.riagade.warframe.util.BasicBot;

import java.time.ZoneOffset;

public class NoraApp {
    public static void startBot() {
        var bot = new BasicBot(System.getenv().get("NORA_TOKEN"),
                Long.parseLong(System.getenv().get("NORA_SERVER")),
                Long.parseLong(System.getenv().get("NORA_CHANNEL")),
                ZoneOffset.UTC.normalized());
        startChallengeReminder(bot);
        startStatusRefresher(bot);
    }

    private static void startChallengeReminder(BasicBot bot) {
        var nightwaveService = new NightwaveService(
                new WebChallengeHolder(bot.getZoneId()),
                new DiscordChallengeConverter(),
                new DiscordMessageSpreader(bot),
                new WebMessagesConstructor(),
                new DiscordMessageHolder(bot));
        try {
            // TODO: repeat via cron
            nightwaveService.postChallenges(true);
            nightwaveService.updateLastMessages();
        } catch (MessagesNotSentException e) {
            // TODO: warn me or something
        }
    }

    private static void startStatusRefresher(BasicBot bot) {
        var statusService = new StatusService(
                new WebStatusHolder(bot.getZoneId()),
                new DiscordStatusSpreader(bot));
        try {
            // TODO: repeat via cron
            statusService.updateStatus();
        } catch (StatusNotUpdatedException e) {
            // TODO: warn me or something
        }
    }
}
