package it.mbdev.meminiaward.service.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class DiscordGuildMember {
    private String avatar;
    private OffsetDateTime communication_disabled_until;
    private int flags;
    private OffsetDateTime joined_at;
    private String nick;
    private boolean pending;
    private OffsetDateTime premium_since;
    private List<String> roles;
    private DiscordUser user;
    private boolean mute;
    private boolean deaf;
    private String bio;
    private String banner;
}
