package it.mbdev.meminiaward.service.model;

public enum Guilds {
    MEMINI("583014508516212742"),
    MOMBELLO("677159940661575681"),
    PENTACOLO("434782206313889792");

    private final String id;
    Guilds(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }
}
