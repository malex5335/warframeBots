package de.riagade.warframe;

import de.riagade.warframe.nightwave.ChallengeReminderService;
import de.riagade.warframe.nightwave.adapters.*;
import de.riagade.warframe.nightwave.exceptions.MessagesNotSentException;
import de.riagade.warframe.statusupdate.StatusRefresherService;
import de.riagade.warframe.statusupdate.adapters.DiscordStatusSpreader;
import de.riagade.warframe.statusupdate.adapters.WebStatusHolder;
import de.riagade.warframe.statusupdate.exceptions.StatusNotUpdatedException;
import de.riagade.warframe.util.BasicBot;

import java.time.ZoneOffset;
import java.util.Locale;

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
        var challengeReminder = new ChallengeReminderService(
                new WebChallengeHolder(bot.getZoneId()),
                new DiscordChallengeConverter(),
                new DiscordMessageSpreader(bot),
                new WebMessagesConstructor(),
                new DiscordMessageHolder(bot));
        try {
            // TODO: repeat via cron
            challengeReminder.postChallenges(true);
            challengeReminder.updateLastMessages();
        } catch (MessagesNotSentException e) {
            // TODO: warn me or something
        }
    }

    private static void startStatusRefresher(BasicBot bot) {
        var statusRefresher = new StatusRefresherService(
                new WebStatusHolder(bot.getZoneId()),
                new DiscordStatusSpreader(bot));
        try {
            // TODO: repeat via cron
            statusRefresher.updateStatus();
        } catch (StatusNotUpdatedException e) {
            // TODO: warn me or something
        }
    }
}
