package com.devoir1.moetezbenyemna.devoir1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devoir1.moetezbenyemna.devoir1.entities.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
