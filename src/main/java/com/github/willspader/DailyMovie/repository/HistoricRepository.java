package com.github.willspader.DailyMovie.repository;

import com.github.willspader.DailyMovie.model.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricRepository extends JpaRepository<Historic, Long> {

    @Query(value = "SELECT id, name FROM HISTORIC WHERE rownum <= :limit ORDER BY id desc", nativeQuery = true)
    public List<Historic> findLastSuggestions(@Param("limit") final Integer limit);

}
