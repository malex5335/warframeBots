package de.riagade.warframeBots.util;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.Locale;

public class BasicBot extends ListenerAdapter {
    private long guildId, channelId;
    private String botToken;
    private Locale locale;
    private JDA jda;

    public BasicBot (String botToken, long guildId, long channelId, Locale locale){
        setBotToken(botToken);
        setGuildId(guildId);
        setChannelId(channelId);
        setLocale(locale);
    }

    public long getGuildId() {
        return guildId;
    }

    private void setGuildId(long guildId) {
        this.guildId = guildId;
    }

    public long getChannelId() {
        return channelId;
    }

    private void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getBotToken() {
        return botToken;
    }

    private void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public Locale getLocale() {
        return locale;
    }

    private void setLocale(Locale locale) {
        this.locale = locale;
    }

    public JDA getJda() {
        return jda;
    }

    private void setJda(JDA jda) {
        this.jda = jda;
    }

    public void connect() {
        try {
            JDABuilder builder = new JDABuilder(AccountType.BOT);
            builder.setToken(getBotToken());
            builder.setAutoReconnect(true);
            builder.setStatus(OnlineStatus.ONLINE);
            setJda(builder.build());
            getJda().awaitReady();
        } catch (LoginException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void disconnect() {
        getJda().shutdown();
    }

    public void sendMessage(String msg) {
        getJda().getTextChannelById(getChannelId()).sendMessage(msg).queue();
    }

}