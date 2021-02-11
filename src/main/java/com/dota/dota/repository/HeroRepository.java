package com.dota.dota.repository;

import com.dota.dota.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    List<Hero> findHeroByHeroType(String heroType);
    Optional<Hero> findHeroByHeroName(String heroName);
}
