package com.github.willspader.DailyMovie.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Random {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "RANDOMID")
    private String randomId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRandomId() {
        return randomId;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Random random = (Random) o;
        return id == random.id &&
                Objects.equals(randomId, random.randomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, randomId);
    }
}
