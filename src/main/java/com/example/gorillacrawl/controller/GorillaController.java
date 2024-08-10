package com.example.gorillacrawl.controller;

import com.example.gorillacrawl.entity.GorillaItem;
import com.example.gorillacrawl.entity.Receiver;
import com.example.gorillacrawl.repository.GorillaItemRepository;
import com.example.gorillacrawl.repository.ReceiverRepository;
import com.example.gorillacrawl.service.GorillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GorillaController {

    @Autowired
    private GorillaItemRepository gorillaRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    @Autowired
    private GorillaService gorillaService;


    @GetMapping("/login")
    public void getLogin() throws IOException {
        gorillaService.goJumunLogin();
    }


    @GetMapping("/myentities")
    public Iterable<GorillaItem> getMyEntities() throws IOException {
        return gorillaRepository.findAll();
    }

    @Scheduled(cron = "0 59 2 * * *") //
    @Scheduled(cron = "0 59 7 * * *") //
    @GetMapping("/soldoutentites")
    public void searchSoldOutEntities() throws IOException {
        gorillaService.goJumunLogin();
        gorillaService.getCompareSoldList();
    }

    @GetMapping("/phonelist")
    public Iterable<Receiver> getReciveList() throws IOException {

        return receiverRepository.findAll();
    }
}

