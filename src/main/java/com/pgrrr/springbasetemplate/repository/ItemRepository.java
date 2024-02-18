package com.pgrrr.springbasetemplate.repository;

import com.pgrrr.springbasetemplate.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
