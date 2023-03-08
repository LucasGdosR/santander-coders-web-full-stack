package com.example.heroapp.repositories;

import com.example.heroapp.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

    Hero findByNameContaining(String name);

    List<Hero> findAllByAvailable(Boolean available);

    List<Hero> findAllByPowerLevelGreaterThanEqual(Integer powerLevel);
}
