package it.mbdev.meminiaward.service;

import it.mbdev.meminiaward.entity.Award;
import it.mbdev.meminiaward.entity.AwardingEvent;
import it.mbdev.meminiaward.repository.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
