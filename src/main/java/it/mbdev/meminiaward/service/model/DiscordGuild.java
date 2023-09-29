package it.mbdev.meminiaward.service.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class DiscordGuild {
    private String id;
    private String name;
    private String icon;
    private boolean owner;
    private String permissions;
    private List<String> features;

}