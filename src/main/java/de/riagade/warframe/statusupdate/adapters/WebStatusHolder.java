package de.riagade.warframe.statusupdate.adapters;

import de.riagade.warframe.statusupdate.entities.E_Status;
import de.riagade.warframe.statusupdate.ports.I_StatusHolderPort;

public class WebStatusHolder implements I_StatusHolderPort {

    @Override
    public E_Status getLastStatus() {
        return null;
    }

    @Override
    public E_Status getCurrentStatus() {
        return null;
    }
}
