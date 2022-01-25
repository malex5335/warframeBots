package de.riagade.warframe.util;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class BasicBot extends ListenerAdapter {
    public static final String WORLD_STATE = "http://content.warframe.com/dynamic/worldState.php";
    private long guildId;
    private long channelId;
    private String botToken;
    private Locale locale;
    private JDA jda;
    private JDABuilder jdaBuilder;

    public BasicBot (String botToken, long guildId, long channelId, Locale locale){
        setBotToken(botToken);
        setGuildId(guildId);
        setChannelId(channelId);
        setLocale(locale);
    }

    /**
     * connects the bot to the server and sets it's {@link OnlineStatus} to Online
     */
    public void connect() {
        setJdaBuilder(JDABuilder.createDefault(getBotToken()));
        getJdaBuilder().disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        getJdaBuilder().setBulkDeleteSplittingEnabled(false);
        getJdaBuilder().setAutoReconnect(true);
        getJdaBuilder().setStatus(OnlineStatus.ONLINE);
        rebuild();
    }

    /**
     * sends a message to the connected channel
     *
     * @param msg the message to send
     */
    public void sendMessage(String msg) {
        Objects.requireNonNull(getJda().getTextChannelById(getChannelId())).sendMessage(msg).queue();
    }

    /**
     * edits a message from the connected channel
     *
     * @param id the id of the message to edit
     * @param msg the message replace the old one with
     */
    public void editMessage(String id, String msg) {
        Objects.requireNonNull(getJda().getTextChannelById(getChannelId())).editMessageById(id, msg).queue();
    }

    /**
     * sets an {@link Activity} if the given one differs to the current one
     *
     * @param activity the {@link Activity} to set to
     */
    public void overwriteActivity(Activity activity) {
        if(!Objects.equals(getJda().getPresence().getActivity(), activity)) {
            getJda().getPresence().setActivity(activity);
        }
    }

    /**
     * sets an {@link OnlineStatus} if the given one differs to the current one
     *
     * @param onlineStatus the {@link OnlineStatus} to set to
     */
    public void overwriteOnlineStatus(OnlineStatus onlineStatus) {
        if(!Objects.equals(getJda().getPresence().getStatus(), onlineStatus)) {
            getJda().getPresence().setStatus(onlineStatus);
        }
    }

    /**
     * sets the idle state if the given one differs to the current one
     *
     * @param idle the idle state to set to
     */
    public void overwriteIdle(boolean idle) {
        if(!Objects.equals(getJda().getPresence().isIdle(), idle)) {
            getJda().getPresence().setIdle(idle);
        }
    }

    /**
     * rebuilds the {@link JDA} using the current {@link JDABuilder}
     * this will take a moment and slows down the other processes
     */
    private void rebuild() {
        try {
            setJda(getJdaBuilder().build());
            getJda().awaitReady();
        } catch (LoginException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Message> retrieveChannelMessages(int amount) {
        MessageHistory history = Objects.requireNonNull(getJda()
                .getTextChannelById(getChannelId())).getHistory();
        return history.retrievePast(amount).complete();
    }
}
