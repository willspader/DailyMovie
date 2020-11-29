package com.github.willspader.DailyMovie.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historic historic = (Historic) o;
        return Objects.equals(id, historic.id) &&
                Objects.equals(name, historic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
