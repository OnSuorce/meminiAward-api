package it.mbdev.meminiaward.repository;

import it.mbdev.meminiaward.entity.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, Integer> {


    List<Token> findAllValidTokenByUser(Integer id);

    @Query("{'user.id': ?0, 'expired': false, 'revoked': false}")
    List<Token> findActiveTokensByUserId(String userId);

    Optional<Token> findByToken(String token);
}