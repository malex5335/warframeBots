package de.riagade.warframeBots.util;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
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

    public void connect() {
        setJdaBuilder(JDABuilder.createDefault(getBotToken()));
        getJdaBuilder().disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        getJdaBuilder().setBulkDeleteSplittingEnabled(false);
        getJdaBuilder().setAutoReconnect(true);
        getJdaBuilder().setStatus(OnlineStatus.ONLINE);
        rebuild();
    }

    public void sendMessage(String msg) {
        Objects.requireNonNull(getJda().getTextChannelById(getChannelId())).sendMessage(msg).queue();
    }

    public void editMessage(String id, String msg) {
        Objects.requireNonNull(getJda().getTextChannelById(getChannelId())).editMessageById(id, msg).queue();
    }

    public void overwriteActivity(Activity activity) {
        if(!Objects.equals(getJda().getPresence().getActivity(), activity)) {
            getJda().getPresence().setActivity(activity);
        }
    }

    public void overwriteOnlineStatus(OnlineStatus onlineStatus) {
        if(!Objects.equals(getJda().getPresence().getStatus(), onlineStatus)) {
            getJda().getPresence().setStatus(onlineStatus);
        }
    }

    public void overwriteIdle(boolean idle) {
        if(!Objects.equals(getJda().getPresence().isIdle(), idle)) {
            getJda().getPresence().setIdle(idle);
        }
    }

    private void rebuild() {
        try {
            setJda(getJdaBuilder().build());
            getJda().awaitReady();
        } catch (LoginException | InterruptedException e){
            e.printStackTrace();
        }
    }
}