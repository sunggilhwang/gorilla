package com.example.gorillacrawl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Brand {

    @Id
    private String brandId;

}
