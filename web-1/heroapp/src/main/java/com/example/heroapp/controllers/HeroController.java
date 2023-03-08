package com.example.heroapp.controllers;

import com.example.heroapp.model.Hero;
import com.example.heroapp.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {
    @Autowired
    private HeroService heroService;

    @PostMapping("/save")
    public Hero save(@RequestBody Hero hero) {
        return heroService.save(hero);
    }

    @GetMapping("/show")
    public List<Hero> findAll() {
        return heroService.findAll();
    }

    @GetMapping("/show/name/{name}")
    public Hero findByNameContaining(@PathVariable String name) {
        return heroService.findByNameContaining(name);
    }

    @GetMapping("/show/{id}")
    public Hero findById(@PathVariable Long id) {
        return heroService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        heroService.deleteById(id);
    }

    @GetMapping("show/available")
    public List<Hero> findAllAvailable() {
        return heroService.findAllByAvailable(true);
    }

    @GetMapping("show/unavailable")
    public List<Hero> findAllUnavailable() {
        return heroService.findAllByAvailable(false);
    }

    @PostMapping("edit/{id}/{available}")
    public ResponseEntity<String> editAvailableById(@PathVariable Long id, @PathVariable Boolean available) {
        if (available == null)
            return ResponseEntity.badRequest().body("Availability must be either true or false.");
        try {
            heroService.editAvailableById(id, available);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok("%d is now %s.".formatted(id, available ? "available" : "unavailable"));
    }

    @GetMapping("show/min-power-level/{powerLevel}")
    public List<Hero> findAllByPowerLevelGreaterThanEqual(@PathVariable Integer powerLevel) {
        return heroService.findAllByPowerLevelGreaterThanEqual(powerLevel);
    }
}
