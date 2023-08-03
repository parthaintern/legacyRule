package com.example.legacyRule.repository;

import com.example.legacyRule.entity.legacy_rules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegacyRepository extends JpaRepository<legacy_rules, Integer> {
    @Query(value = "SELECT * FROM armour_schema.legacy_rules", nativeQuery = true)
    List<legacy_rules> getAllValues();

}
