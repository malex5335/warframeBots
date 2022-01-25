package de.riagade.warframe.statusupdate.ports;

import de.riagade.warframe.statusupdate.entities.E_Status;

public interface I_StatusHolderPort {
    E_Status getCurrentStatus();
    String messageForStatus(E_Status status);
}
