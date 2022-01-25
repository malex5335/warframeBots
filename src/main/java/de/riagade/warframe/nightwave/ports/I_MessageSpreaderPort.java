package de.riagade.warframe.nightwave.ports;

public interface I_MessageSpreaderPort {
    boolean postMessage(String message);
    boolean updateMessage(String id, String newMessage);
}
