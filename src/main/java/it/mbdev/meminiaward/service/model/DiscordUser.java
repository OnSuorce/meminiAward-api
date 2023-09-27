package it.mbdev.meminiaward.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DiscordUser {

    private String id;
    private String username;
    private String avatar;
    private String discriminator;
    @JsonProperty("public_flags")
    private int publicFlags;
    private int flags;
    private String banner;
    @JsonProperty("accent_color")
    private int accentColor;
    @JsonProperty("global_name")
    private String globalName;
    @JsonProperty("banner_color")
    private String bannerColor;
    @JsonProperty("mfa_enabled")
    private boolean mfaEnabled;
    private String locale;
    @JsonProperty("premium_type")
    private int premiumType;

}
