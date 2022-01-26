package de.riagade.warframe.nightwave.adapters;

import de.riagade.warframe.nightwave.entities.MessageId;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;

@Getter
@Setter
public class DiscordMessageId implements MessageId {

    private Message message;

    public DiscordMessageId(Message message) {
       setMessage(message);
    }

    @Override
    public String getId() {
        return getMessage().getId();
    }

    @Override
    public String getContent() {
        return getMessage().getContentRaw();
    }
}
