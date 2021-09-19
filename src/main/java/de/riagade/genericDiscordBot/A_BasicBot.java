package de.riagade.genericDiscordBot;

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

/**
 * a basic Discord bot for generic tasks which connects automatically.<br>
 * extending {@link ListenerAdapter}.
 */
@Getter
@Setter
@SuppressWarnings("unused")
public abstract class A_BasicBot extends ListenerAdapter {
    private long guildId, channelId;
    private String botToken;
    private Locale locale;
    private JDABuilder jdaBuilder;
    private JDA jda;

    /**
     * @param botToken  the token which the bot uses to authenticate
     * @param guildId   the server id to connect the bot to
     * @param channelId the channel id for the bot to write into
     * @param locale    the locale for the bot to use
     */
    public A_BasicBot(String botToken, long guildId, long channelId, Locale locale){
        setBotToken(botToken);
        setGuildId(guildId);
        setChannelId(channelId);
        setLocale(locale);
        connect();
    }

    /**
     * connects the bot to the server getGuildId
     */
    private void connect() {
        try {
            setJdaBuilder(JDABuilder.createDefault(getBotToken()));
            getJdaBuilder().setAutoReconnect(true);
            getJdaBuilder().setStatus(OnlineStatus.ONLINE);
            getJdaBuilder().disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
            getJdaBuilder().setBulkDeleteSplittingEnabled(false);
            getJdaBuilder().addEventListeners(this);
            setJda(getJdaBuilder().build());
            getJda().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * disconnects the bot from the server getGuildId
     */
    public void disconnect() {
        getJda().shutdown();
    }

    /**
     * sends a message into the channel from getChannelId
     * @param msg the message to send
     */
    public void sendMessage(String msg) {
        try {
            Objects.requireNonNull(getJda().getTextChannelById(getChannelId())).sendMessage(msg).queue();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
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
     * reads the last 100 messages send in channel {@link #getChannelId()}
     *
     * @return the List of {@link Message} that where found
     */
    public List<Message> retrieveChannelMessages() {
        MessageHistory history = Objects.requireNonNull(getJda()
                .getTextChannelById(getChannelId())).getHistory();
        return history.retrievePast(100).complete();
    }
}
