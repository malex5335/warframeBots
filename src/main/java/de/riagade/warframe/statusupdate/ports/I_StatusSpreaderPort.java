package de.riagade.warframe.statusupdate.ports;

import de.riagade.warframe.statusupdate.entities.E_Status;

public interface I_StatusSpreaderPort {
    boolean updateStatus(E_Status newStatus);
}
