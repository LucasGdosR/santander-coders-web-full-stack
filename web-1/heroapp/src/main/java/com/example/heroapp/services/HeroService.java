package com.example.heroapp.services;

import com.example.heroapp.model.Hero;
import com.example.heroapp.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {
    @Autowired
    private HeroRepository heroRepository;

    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    public Hero findByNameContaining(String name) {
        return heroRepository.findByNameContaining(name);
    }

    public List<Hero> queryByName(String name) {
        return heroRepository.queryByName(name);
    }
    public Hero findById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        heroRepository.deleteById(id);
    }

    public List<Hero> findAllByAvailable(Boolean available) {
        return heroRepository.findAllByAvailable(available);
    }

    public void editAvailableById(Long id, Boolean available) {
        Hero hero = heroRepository.findById(id).orElse(null);

        if (hero == null)
            throw new RuntimeException("ID não localizado.");

        hero.setAvailable(available);

        heroRepository.save(hero);
    }

    public List<Hero> findAllByPowerLevelGreaterThanEqual(Integer powerLevel) {
        return heroRepository.findAllByPowerLevelGreaterThanEqual(powerLevel);
    }
}
