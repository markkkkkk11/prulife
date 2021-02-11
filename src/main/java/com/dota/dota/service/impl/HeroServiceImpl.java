package com.dota.dota.service.impl;


import com.dota.dota.model.Hero;
import com.dota.dota.repository.HeroRepository;
import com.dota.dota.service.HeroService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    HeroRepository heroRepository;


    @Transactional
    @Override
    public void createHero(Hero hero) throws Exception {

        if(heroRepository.findHeroByHeroName(hero.getHeroName()).isPresent()) {
            throw  new Exception("Hero already exists");
        }
        else {
            heroRepository.save(hero);
        }

    }

    @Override
    public void updateHero(String heroName, Hero hero) throws Exception {

        final Hero editedHero = heroRepository
                .findHeroByHeroName(heroName)
                .orElseThrow(() -> new NotFoundException("hero not found"));

        editedHero.setHeroName(hero.getHeroName());
        editedHero.setHeroType(hero.getHeroType());
        editedHero.setAgility(hero.getAgility());
        editedHero.setStrength(hero.getStrength());
        editedHero.setIntelligence(hero.getIntelligence());

        heroRepository.save(editedHero);
    }

    @Override
    public void deleteHero(String heroName) throws NotFoundException {
        final Hero hero =heroRepository
                .findHeroByHeroName(heroName)
                .orElseThrow(() -> new NotFoundException("hero not found"));
        heroRepository.delete(hero);
    }
}
