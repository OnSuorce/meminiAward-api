package it.mbdev.meminiaward.repository;

import it.mbdev.meminiaward.entity.Token;
import it.mbdev.meminiaward.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, Integer> {


    List<Token> findAllValidTokenByUser(Integer id);

    @Query("{'user': ?0, 'expired': false, 'revoked': false}")
    List<Token> findActiveTokensByUser(User user);

    Optional<Token> findByToken(String token);
}