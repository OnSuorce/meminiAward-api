package it.mbdev.meminiaward.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

@Data
public class Vote {

    @DBRef
    private User voter;
    @DBRef
    private User voted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(voter, vote.voter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voter);
    }
}
