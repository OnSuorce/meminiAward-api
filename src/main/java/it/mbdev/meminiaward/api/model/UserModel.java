package it.mbdev.meminiaward.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.security.Roles;
import lombok.Data;

import java.util.List;

@Data
public class UserModel {

    @JsonProperty("number_of_awards")
    private int numberOfAwards;
    @JsonProperty("global_name")
    private String globalName;
    @JsonProperty("avatar_url")
    private String avatarUrl;

    private String username;

    public UserModel(User u){
        if(u == null)return;
        this.username = u.getUsername();
        this.avatarUrl = u.getDiscordAvatarUrl();
        this.numberOfAwards = u.getNumberOfAwards();
        this.globalName = u.getDiscordGlobalName();
    }
}
