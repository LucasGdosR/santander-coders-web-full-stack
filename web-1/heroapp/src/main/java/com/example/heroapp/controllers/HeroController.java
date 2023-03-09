package com.example.heroapp.controllers;

import com.example.heroapp.model.Hero;
import com.example.heroapp.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/heroes")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @GetMapping("/heroes")
    public String getAllHeroes(Model model) {
        List<Hero> heroes = heroService.findAll();
        model.addAttribute("heroes", heroes);
        return "heroes";
    }

    @PostMapping("/heroes")
    public String addHero(@RequestParam("name") String name,
                          @RequestParam("superPower") String superPower,
                          @RequestParam("powerLevel") Integer powerLevel,
                          @RequestParam("weakness") String weakness,
                          @RequestParam("available") Boolean available) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setSuperPower(superPower);
        hero.setPowerLevel(powerLevel);
        hero.setWeakness(weakness);
        hero.setAvailable(available);
        heroService.save(hero);

        return "redirect:heroes";
    }

    @GetMapping("add-hero")
    public String createHero() {
        return "add-heroes";
    }

    @PostMapping("/heroes/delete/{id}")
    public String deleteHero(@PathVariable("id") Long id) {
        heroService.deleteById(id);;
        return "redirect:../../heroes";
    }

    @PostMapping("/search")
    public String searchFor(@RequestParam("name") String name, Model model) {
        List<Hero> heroes = heroService.queryByName(name);
        model.addAttribute("heroes", heroes);
        return "heroes";
    }
}
