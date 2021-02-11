package com.dota.dota.service;

import com.dota.dota.model.Hero;
import javassist.NotFoundException;

import java.util.List;

public interface HeroService {

    void createHero(Hero hero) throws Exception;
    void updateHero(String heroName, Hero hero) throws Exception;
    void deleteHero(String heroName) throws NotFoundException;
}
