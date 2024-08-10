package com.example.gorillacrawl.repository;

import com.example.gorillacrawl.entity.GorillaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GorillaItemRepository extends JpaRepository<GorillaItem, Long> {

    List<GorillaItem> findAllBySoldFlag(String soldFlag);

    GorillaItem findAllByItemNumber(String itemNumber);
}

