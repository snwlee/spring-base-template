package com.snwlee.springbasetemplate.item.repository;

import com.snwlee.springbasetemplate.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
