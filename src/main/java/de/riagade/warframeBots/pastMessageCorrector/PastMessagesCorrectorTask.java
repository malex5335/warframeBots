package de.riagade.warframeBots.pastMessageCorrector;

import de.riagade.genericDiscordBot.A_BasicBot;
import de.riagade.genericDiscordBot.A_TimedTask;
import de.riagade.warframeBots.util.RegexHelper;
import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public abstract class PastMessagesCorrectorTask extends A_TimedTask {

    public PastMessagesCorrectorTask(A_BasicBot bot, String cronExpression) {
        super(bot, cronExpression);
    }

    @Override
    public void runTimedLogic() {
        for (Message message : getBot().retrieveChannelMessages()) {
            updateMessage(message);
        }
    }

    /**
     * checks a messages content with known keys to update it's content
     *
     * @param message the {@link Message} to check
     */
    private void updateMessage(Message message) {
        if(isRawContent(message.getContentRaw())) {
            for (String key : getKeys()) {
                try {
                    updateMessageForKey(key, message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * updates the messages if the key is referenced in it
     *
     * @param key the key to check and get the description of using {@link #getDescription(String)}
     * @param message the message to check
     */
    private void updateMessageForKey(String key, Message message) {
        String newText = message.getContentRaw();
        if (newText.contains(key)) {
            if(RegexHelper.containsExactSubstring(newText, key)) {
                getBot().editMessage(message.getId(), newText.replace(key, getDescription(key)));
            }
        }
    }

    /**
     * the keys to iterate through
     *
     * @return List of String keys
     */
    protected abstract List<String> getKeys();

    /**
     * the target description of this key
     *
     * @param key a key from within {@link #getKeys()}
     * @return the description represented by the key
     */
    protected abstract String getDescription(String key);

    /**
     * checks if this description is considered raw content
     *
     * @param description the text to check
     * @return true if considered raw and needs to be updated
     */
    protected abstract boolean isRawContent(String description);
}
