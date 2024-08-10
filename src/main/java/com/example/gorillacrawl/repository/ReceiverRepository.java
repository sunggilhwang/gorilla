package com.example.gorillacrawl.repository;

import com.example.gorillacrawl.entity.GorillaItem;
import com.example.gorillacrawl.entity.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface ReceiverRepository extends JpaRepository<Receiver, Long> {

}

