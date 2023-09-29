package it.mbdev.meminiaward.service;

import lombok.Data;


public enum DiscordApiUrl {

    BASE_URL("https://discord.com/api/v10"),

    OAUTH("/oauth2/token"),
    USER_INFO("/users/@me"),
    USER_GUILDS("/users/@me/guilds"),

    USER_GUILD_INFO("/users/@me/guilds/#/member"),

    CDN_AVATAR("https://cdn.discordapp.com/avatars/");

    private final String url;
    DiscordApiUrl(String s) {
        url = s;
    }

    public String getUrl() {
        return url;
    }
}
