package com.snwlee.springbasetemplate.item.entity;

import com.snwlee.springbasetemplate.common.entity.BaseTimeEntity;
import com.snwlee.springbasetemplate.item.dto.ItemResponseDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_desc", nullable = false)
    private String itemDesc;

    public ItemResponseDto toDto() {
        return ItemResponseDto.builder()
                .itemName(this.itemName)
                .itemDesc(this.itemDesc)
                .build();
    }

}
