package com.example.spring_web_crawler_demo.controllers;

import com.example.spring_web_crawler_demo.entities.Estate;
import com.example.spring_web_crawler_demo.repositories.EstateRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/estates")
public class EstateController {

    private final EstateRepository estateRepository;

    public EstateController(EstateRepository estateRepository){
        this.estateRepository = estateRepository;
    }

    @GetMapping("/get")
    public String getAllEstates(Model model){
        model.addAttribute("estates", estateRepository.findAll());

        return "table/get_table";
    }

    @GetMapping("/search")
    public String searchEstates(@RequestParam("keyword") String keyword, Model model){
        List<Estate> searchedEstates = estateRepository.searchEstates(keyword);

        if(searchedEstates.isEmpty()){
            return "table/not_found";
        }else{
            model.addAttribute("estates", searchedEstates);
            return "table/get_table";
        }

    }
}
