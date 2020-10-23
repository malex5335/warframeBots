package de.riagade.warframeBots.util;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

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

    public BasicBot (String botToken, long guildId, long channelId, Locale locale){
        setBotToken(botToken);
        setGuildId(guildId);
        setChannelId(channelId);
        setLocale(locale);
    }

    public void connect() {
        try {
            JDABuilder builder = new JDABuilder(AccountType.BOT);
            builder.setToken(getBotToken());
            builder.setAutoReconnect(true);
            builder.setStatus(OnlineStatus.ONLINE);
            setJda(builder.build());
            getJda().awaitReady();
        } catch (LoginException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        Objects.requireNonNull(getJda().getTextChannelById(getChannelId())).sendMessage(msg).queue();
    }

}