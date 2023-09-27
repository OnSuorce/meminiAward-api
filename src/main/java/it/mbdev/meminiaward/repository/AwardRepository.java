package it.mbdev.meminiaward.repository;

import it.mbdev.meminiaward.entity.Award;
import it.mbdev.meminiaward.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AwardRepository extends MongoRepository<Award, String> {

    Optional<Award> findByTitle(String title);


}
