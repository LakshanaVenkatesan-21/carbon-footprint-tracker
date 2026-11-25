package com.ecotrack.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecotrack.model.Habit;
import com.ecotrack.repository.HabitRepository;

import java.time.LocalDate;

@Controller
public class HabitController {

    @Autowired
    private HabitRepository habitRepo;

    // Display home page
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("listHabits", habitRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddHabitPage(Model model) {
        model.addAttribute("habit", new Habit());
        return "add-habit";
    }

    @PostMapping("/save")
    public String saveHabit(@ModelAttribute("habit") Habit habit) {
        habit.setHabit(habit.getActivity());
        if (habit.getDuration() == null) {
            habit.setDuration(0.0);
        }
        habit.setCarbonEmission(habit.getDuration() * 0.21);
        habit.setDateAdded(LocalDate.now().toString());
        habitRepo.save(habit);
        return "redirect:/";
    }

    // Handle add habit form submission
    @PostMapping("/addHabit")
    public String addHabit(@RequestParam("habit") String habit, Model model) {

        // Ensure the habit string isn’t empty
        if (habit == null || habit.trim().isEmpty()) {
            model.addAttribute("message", "⚠️ Please enter a habit before submitting.");
        } else {
            Habit newHabit = new Habit();
            newHabit.setHabit(habit);
            newHabit.setActivity(habit);
            newHabit.setDuration(0.0);
            newHabit.setCarbonEmission(0.0);
            newHabit.setDateAdded(LocalDate.now().toString());
            habitRepo.save(newHabit);
            model.addAttribute("message", "✅ Habit added successfully!");
        }

        // Refresh list after adding
        model.addAttribute("listHabits", habitRepo.findAll());
        return "index";
    }
}