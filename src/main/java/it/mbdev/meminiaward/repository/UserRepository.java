package it.mbdev.meminiaward.repository;

import it.mbdev.meminiaward.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find a user by username
    Optional<User> findByUsername(String username);

    // Find a user by both Discord token and username
    Optional<User> findByDiscordTokenAndUsername(String discordToken, String username);
}
