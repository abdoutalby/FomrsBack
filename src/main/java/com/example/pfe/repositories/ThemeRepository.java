package com.example.pfe.repositories;

import com.example.pfe.Models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme , Long> {
    boolean existsByColor(String color);
}
