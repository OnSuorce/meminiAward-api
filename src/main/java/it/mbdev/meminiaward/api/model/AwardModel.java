package it.mbdev.meminiaward.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.mbdev.meminiaward.entity.Award;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AwardModel {

    public AwardModel(Award a){

        title = a.getTitle();

        description = a.getDescription();

        imageUrl = a.getImageUrl();

        creator = a.getCreator().getUsername();

    }
    private String title;
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    private String creator;
}
