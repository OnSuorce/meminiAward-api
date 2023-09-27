package it.mbdev.meminiaward.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

@Data
public class Votes {

    @DBRef
    private User voter;
    @DBRef
    private User voted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votes votes = (Votes) o;
        return Objects.equals(voter, votes.voter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voter);
    }
}
