package de.riagade.warframeBots;

import de.riagade.warframeBots.bots.nightwave.NoraBot;
import de.riagade.warframeBots.voidtrader.BaroKiTeer;

import java.util.Locale;

public class BotInjector {

    public static void main(String[] args) {
        try {
            startNoraNight();
            BaroKiTeer.injectInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startNoraNight() {
        new NoraBot(System.getenv().get("NORA_TOKEN"),
                Long.parseLong(System.getenv().get("NORA_SERVER")),
                Long.parseLong(System.getenv().get("NORA_CHANNEL")),
                Locale.UK);
    }

}
