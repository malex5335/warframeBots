package de.riagade.warframe.status;

import de.riagade.warframe.status.exceptions.StatusNotUpdatedException;
import de.riagade.warframe.status.ports.I_StatusHolderPort;
import de.riagade.warframe.status.ports.I_StatusSpreaderPort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PRIVATE)
@Setter
public class StatusService {
    private I_StatusHolderPort statusHolder;
    private I_StatusSpreaderPort statusSpreader;

    public StatusService(I_StatusHolderPort statusHolder, I_StatusSpreaderPort statusSpreader) {
        setStatusHolder(statusHolder);
        setStatusSpreader(statusSpreader);
    }

    public void updateStatus() throws StatusNotUpdatedException {
        var currentStatus = getStatusHolder().getCurrentStatus();
        if(currentStatus != null) {
            var message = getStatusHolder().messageForStatus(currentStatus);
            if(!getStatusSpreader().updateStatus(currentStatus, message)) {
                throw new StatusNotUpdatedException("the status could not be updated");
            }
        }
    }
}
