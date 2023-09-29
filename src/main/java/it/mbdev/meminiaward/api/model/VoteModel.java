package it.mbdev.meminiaward.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.mbdev.meminiaward.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VoteModel {

    @JsonProperty("user_voting")
    String userVoting;
    @JsonProperty("user_voted")
    String userVoted;
    @JsonProperty("award_id")
    String awardId;

    public VoteModel(Vote vote, String awardId){
        userVoting = vote.getVoter().getUsername();
        userVoted = vote.getVoted().getUsername();
        this.awardId = awardId;

    }
}
