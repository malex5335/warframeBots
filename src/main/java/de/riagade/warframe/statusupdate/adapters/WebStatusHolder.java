package de.riagade.warframe.statusupdate.adapters;

import de.riagade.warframe.statusupdate.entities.E_Status;
import de.riagade.warframe.statusupdate.ports.I_StatusHolderPort;
import de.riagade.warframe.util.DateHelper;
import de.riagade.warframe.util.GenericJSONParser;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class WebStatusHolder implements I_StatusHolderPort {

    public static final String WORLD_STATE = "http://content.warframe.com/dynamic/worldState.php";

    private ZoneId zoneId;

    public WebStatusHolder(ZoneId zoneId) {
        setZoneId(zoneId);
    }

    @Override
    public E_Status getCurrentStatus() {
        try {
            var seasonInfo = GenericJSONParser
                    .retrieveJSONObject(WebStatusHolder.WORLD_STATE)
                    .getJSONObject("SeasonInfo");
            var activation = getDateIn(seasonInfo
                    .getJSONObject("Activation"),
                    getZoneId());
            var expiry = getDateIn(seasonInfo
                    .getJSONObject("Expiry"),
                    getZoneId());
            var now = LocalDateTime.now(getZoneId());
            if(now.isAfter(activation) && now.isBefore(expiry)) {
                return E_Status.ACTIVE;
            }
        } catch (Exception e){
            // TODO: warn me or something
        }
        return E_Status.WAITING;
    }

    @Override
    public String messageForStatus(E_Status status) {
        switch (status) {
            case ACTIVE:
                return "Season " + getSeasonInfo() + " Episode " + (getEpisodeInfo() + 1);
            case WAITING:
                return "For your dreams";
            default:
                break;
        }
        return "";
    }

    private LocalDateTime getDateIn(JSONObject jsonObject, ZoneId zoneId) {
        return DateHelper.parseTimestamp(jsonObject
                .getJSONObject("$date")
                .getString("$numberLong"),
                zoneId);
    }

    private int getSeasonInfo() {
        try {
            return GenericJSONParser
                    .retrieveJSONObject(WebStatusHolder.WORLD_STATE)
                    .getJSONObject("SeasonInfo")
                    .getInt("Season");
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    private int getEpisodeInfo() {
        try {
            return GenericJSONParser
                    .retrieveJSONObject(WebStatusHolder.WORLD_STATE)
                    .getJSONObject("SeasonInfo")
                    .getInt("Phase");
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
