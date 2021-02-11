package com.dota.dota.controller;

import com.dota.dota.model.Hero;
import com.dota.dota.repository.HeroRepository;
import com.dota.dota.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/v1/dota")
@RequiredArgsConstructor
public class controller {

    @Autowired
    private final HeroService heroService;

    @Autowired
    private final HeroRepository heroRepository;


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity list() {
        List<Hero> heroList =heroRepository.findAll();

        Collections.sort(heroList, Comparator.comparing(Hero::getHeroName));

        return new ResponseEntity<>(heroList, HttpStatus.OK);
    }

    @GetMapping( value = "/hero/{heroType}", produces = "application/json")
    public ResponseEntity findByHeroType(@PathVariable() final String heroType) {
        return  new ResponseEntity(heroRepository.findHeroByHeroType(heroType), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createHero(final @RequestBody  Hero hero) throws Exception {
        heroService.createHero(hero);
        return  new ResponseEntity("hero successfully created", HttpStatus.OK);
    }


    @RequestMapping(value = "{heroName}", method = RequestMethod.PUT)
    public ResponseEntity updateHero(final @RequestParam String heroName,
                                     final @RequestBody Hero hero) throws Exception {
        heroService.updateHero(heroName, hero);
        return  new ResponseEntity("hero successfully edited", HttpStatus.OK);
    }

    @DeleteMapping(value = "{heroName}")
    public ResponseEntity deleteHero(final @PathVariable String heroName) throws Exception {
        heroService.deleteHero(heroName);
        return  new ResponseEntity("hero successfully deleted", HttpStatus.OK);
    }

}
