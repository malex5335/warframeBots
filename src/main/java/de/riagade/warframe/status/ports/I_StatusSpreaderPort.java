package de.riagade.warframe.status.ports;

import de.riagade.warframe.status.entities.E_Status;

public interface I_StatusSpreaderPort {
    boolean updateStatus(E_Status status, String message);
}
