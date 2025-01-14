package com.snwlee.springbasetemplate.item.dto;

import com.snwlee.springbasetemplate.item.entity.Item;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ItemRequestDto {
    @NotBlank
    private String itemName;
    @NotBlank
    private String itemDesc;

    public Item toEntity() {
        return Item.builder()
                .itemName(this.itemName)
                .itemDesc(this.itemDesc)
                .build();
    }
}