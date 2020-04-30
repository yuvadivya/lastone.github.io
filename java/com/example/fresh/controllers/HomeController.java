package com.example.fresh.controllers;

import com.example.fresh.models.LocationStats;
import com.example.fresh.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getConfirmed()).sum();
        int totalRecoveredCases = allStats.stream().mapToInt(stat -> stat.getRecovered()).sum();
        int totalDeceasedCases = allStats.stream().mapToInt(stat -> stat.getDeceased()).sum();
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalRecoveredCases",totalRecoveredCases);
        model.addAttribute("totalDeceasedCases",totalDeceasedCases);
        model.addAttribute("locationStats",allStats);

        return "home";
    }
}
