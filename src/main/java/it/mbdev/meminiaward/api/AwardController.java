package it.mbdev.meminiaward.api;

import it.mbdev.meminiaward.api.model.AwardModel;
import it.mbdev.meminiaward.api.model.UserModel;
import it.mbdev.meminiaward.entity.Award;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.exceptions.ForbiddenException;
import it.mbdev.meminiaward.security.MeminiAuthn;
import it.mbdev.meminiaward.service.AwardService;
import it.mbdev.meminiaward.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/award")
public class AwardController {


    @Autowired
    private AwardService awardService;

    @Autowired
    private UserService userService;

    @MeminiAuthn
    @GetMapping
    public List<AwardModel> getAwardList(){
        List<Award> awards =  awardService.getAwardList();
       List<AwardModel> awardModels = awards.stream().map(award -> new AwardModel(award)).toList();
       return awardModels;
    }

    @PostMapping
    public void postAward(@RequestBody AwardModel awardModel){

        User u = userService.getUser(AuthController.getLoggedUsername()).orElseThrow(ForbiddenException::new);
        if(u.getNumberOfAwards()<=0) return;

        Award a = Award.builder()
                .title(awardModel.getTitle())
                .description(awardModel.getDescription())
                .imageUrl(awardModel.getImageUrl())
                .creator(u)
                .build();

        awardService.saveAward(a);
        u.setNumberOfAwards(u.getNumberOfAwards()-1);
    }

}
