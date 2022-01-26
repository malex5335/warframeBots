package de.riagade.warframe.status.ports;

import de.riagade.warframe.status.entities.E_Status;

public interface I_StatusHolderPort {
    E_Status getCurrentStatus();
    String messageForStatus(E_Status status);
}
