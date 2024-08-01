package com.snwlee.springbasetemplate.item.dto;

import com.snwlee.springbasetemplate.item.entity.Item;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ItemRequestDto {
    private String itemName;
    private String itemDesc;

    public Item toEntity() {
        return Item.builder()
                .itemName(this.itemName)
                .itemDesc(this.itemDesc)
                .build();
    }

    public Item toEntity(Long itemId) {
        return Item.builder()
                .id(itemId)
                .itemName(this.itemName)
                .itemDesc(this.itemDesc)
                .build();
    }

}

