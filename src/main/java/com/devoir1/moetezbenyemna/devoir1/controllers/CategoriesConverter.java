package com.devoir1.moetezbenyemna.devoir1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;
import com.devoir1.moetezbenyemna.devoir1.repo.CategoriesRepository;

/**
 * Converts the idCat string submitted from a <select> into a Categories entity.
 * Spring MVC registers this automatically since it's a @Component Converter.
 */
@Component
public class CategoriesConverter implements Converter<String, Categories> {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public Categories convert(String id) {
        if (id == null || id.isBlank()) return null;
        try {
            return categoriesRepository.findById(Long.parseLong(id)).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
