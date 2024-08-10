package com.example.gorillacrawl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GorillaItem {

    @Id
    private String itemNumber;
    private String itemName;
    private String  soldFlag;
    private String  brandId;




    // getters and setters
}
