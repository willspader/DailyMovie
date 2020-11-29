package com.github.willspader.DailyMovie.repository;

import com.github.willspader.DailyMovie.model.Random;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomRepository extends JpaRepository<Random, Long> {

}
