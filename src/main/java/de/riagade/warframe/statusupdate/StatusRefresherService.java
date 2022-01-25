package de.riagade.warframe.statusupdate;

import de.riagade.warframe.statusupdate.exceptions.StatusNotUpdatedException;
import de.riagade.warframe.statusupdate.ports.I_StatusHolderPort;
import de.riagade.warframe.statusupdate.ports.I_StatusSpreaderPort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter(AccessLevel.PRIVATE)
@Setter
public class StatusRefresherService {
    private I_StatusHolderPort statusHolder;
    private I_StatusSpreaderPort statusSpreader;

    public StatusRefresherService(I_StatusHolderPort statusHolder, I_StatusSpreaderPort statusSpreader) {
        setStatusHolder(statusHolder);
        setStatusSpreader(statusSpreader);
    }

    public void updateStatus() throws StatusNotUpdatedException {
        var lastStatus = getStatusHolder().getLastStatus();
        var currentStatus = getStatusHolder().getCurrentStatus();
        if(Objects.equals(lastStatus, currentStatus) && currentStatus != null) {
            if(!getStatusSpreader().updateStatus(currentStatus)) {
                throw new StatusNotUpdatedException("the status could not be updated");
            }
        }
    }
}
