package it.mbdev.meminiaward.api;

import it.mbdev.meminiaward.api.model.VoteCount;
import it.mbdev.meminiaward.api.model.VoteModel;
import it.mbdev.meminiaward.entity.Award;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.entity.Vote;
import it.mbdev.meminiaward.exceptions.AwardNotFound;
import it.mbdev.meminiaward.exceptions.ForbiddenException;
import it.mbdev.meminiaward.exceptions.UserNotFound;
import it.mbdev.meminiaward.security.MeminiAuthn;
import it.mbdev.meminiaward.service.AwardService;
import it.mbdev.meminiaward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("api/vote")
public class VoteController {


    @Autowired
    private UserService userService;
    @Autowired
    private AwardService awardService;

    @PostMapping
    public void postVote(@RequestBody VoteModel voteModel){

        User u = userService.getUser(AuthController.getLoggedUsername()).orElseThrow(ForbiddenException::new);
        User voted = userService.getUser(voteModel.getUserVoted()).orElseThrow(UserNotFound::new);
        Award award = awardService.getAwardById(voteModel.getAwardId()).orElseThrow(AwardNotFound::new);

        awardService.addVote(u, voted, award);

    }

    @MeminiAuthn
    @GetMapping
    public List<VoteModel> getVotes(@RequestParam("award") String awardid){
        Award a = awardService.getAwardById(awardid).orElseThrow(AwardNotFound::new);
        return awardService.getAwardVotings(a);
    }

}
