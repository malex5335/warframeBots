package de.riagade.warframe.nightwave.ports;

import java.util.Map;

public interface I_MessageHolderPort {
    Map<String, String> getLastMessages(int amount);
}
