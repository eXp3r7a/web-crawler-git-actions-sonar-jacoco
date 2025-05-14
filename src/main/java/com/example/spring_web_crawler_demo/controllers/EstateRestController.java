package com.example.spring_web_crawler_demo.controllers;

import com.example.spring_web_crawler_demo.entities.Estate;
import com.example.spring_web_crawler_demo.entities.ResponseMessage;
import com.example.spring_web_crawler_demo.repositories.EstateRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/estates")
public class EstateRestController {

    private final EstateRepository estateRepository;

    public EstateRestController(EstateRepository estateRepository){
        this.estateRepository = estateRepository;
    }

    @GetMapping("/get")
    public List<Estate> getAllEstates(){
        return estateRepository.findAll();
    }

    @GetMapping("/search")
    public Object searchEstates(@RequestParam("keyword") String keyword, Model model){
        List<Estate> searchedEstates = estateRepository.searchEstates(keyword);

        if(searchedEstates.isEmpty()){
            ResponseMessage responseMessage = new ResponseMessage();
            responseMessage.setMessage("Not found estate with this keyword!");
            return responseMessage;
        }else{
            return searchedEstates;
        }

    }
}
