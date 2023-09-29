package it.mbdev.meminiaward.repository;

import it.mbdev.meminiaward.entity.Award;
import it.mbdev.meminiaward.entity.Token;
import it.mbdev.meminiaward.entity.User;
import it.mbdev.meminiaward.entity.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AwardRepository extends MongoRepository<Award, String> {

    Optional<Award> findByTitle(String title);

    @Query("{ 'voteList.voted': ?0 }")
    List<Vote> findEntitiesByVoteVoted(User voted);


}
