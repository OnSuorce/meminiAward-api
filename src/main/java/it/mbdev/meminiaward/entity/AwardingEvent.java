package it.mbdev.meminiaward.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "awarding_events")
public class AwardingEvent {

    @Id
    private String id;
    private String awardingTitle;

    private String awardingDescription;

    private Date startDate;

    private Date endDate;


}
