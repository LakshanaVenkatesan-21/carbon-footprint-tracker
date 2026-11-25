package com.ecotrack.repository;

import com.ecotrack.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> { }
