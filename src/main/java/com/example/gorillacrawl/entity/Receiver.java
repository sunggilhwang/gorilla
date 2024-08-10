package com.example.gorillacrawl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Receiver {

    @Id
    private String phoneNumber;

}
