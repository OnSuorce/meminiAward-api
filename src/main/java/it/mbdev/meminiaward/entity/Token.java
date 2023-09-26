package it.mbdev.meminiaward.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "tokens")
public class Token {
    @Id
    private String id;

    private String token;

    private String tokenType;

    private boolean revoked;

    private boolean expired;

    @DBRef
    private User user;

    // Constructors, getters, and setters
}
