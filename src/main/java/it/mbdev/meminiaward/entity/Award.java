package it.mbdev.meminiaward.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "awards")
public class Award {

    @Id
    private String id; // MongoDB ObjectId (automatically generated)
    private String title;
    private String description;
    private String imageUrl;
    @DBRef
    private User creator;
    private Set<Votes> votesList;
    @DBRef
    private AwardingEvent awardingEvent;

}
