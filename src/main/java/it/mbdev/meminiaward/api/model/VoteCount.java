package it.mbdev.meminiaward.api.model;

import lombok.Data;

import java.util.List;

@Data
public class VoteCount {

    private List<String> voters;
    private String voted;

    private Integer votes;
}
