package it.mbdev.meminiaward.service;

import it.mbdev.meminiaward.api.model.VoteCount;
import it.mbdev.meminiaward.api.model.VoteModel;
import it.mbdev.meminiaward.entity.Award;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.entity.Vote;
import it.mbdev.meminiaward.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AwardService {

    @Autowired
    AwardRepository awardRepository;

    public List<Award> getAwardList(){
        return awardRepository.findAll();
    }

    public void saveAward(Award award){

        awardRepository.save(award);
    }

    public Optional<Award> getAwardById(String awardId) {
        return awardRepository.findById(awardId);
    }

    public void addVote(User voter, User voted, Award award) {

        Vote v = new Vote();
        v.setVoted(voted);
        v.setVoter(voter);
        if(award.getVoteList()==null) award.setVoteList(new HashSet<>());

        award.getVoteList().add(v);
        saveAward(award);

    }

    public List<VoteModel> getAwardVotings(Award award) {

        return award.getVoteList()
                .stream()
                .map(vote -> new VoteModel(vote, award.getId()))
                .collect(Collectors.toList());

    }
}
