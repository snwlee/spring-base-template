package com.snwlee.springbasetemplate.item.service;

import com.snwlee.springbasetemplate.item.dto.ItemRequestDto;
import com.snwlee.springbasetemplate.item.dto.ItemResponseDto;

import java.util.List;

public interface ItemService {

    ItemResponseDto getItem(Long itemId);

    void addItem(ItemRequestDto itemRequestDto);

    void updateItem(Long itemId, ItemRequestDto itemRequestDto);

    void removeItem(Long itemId);

    List<ItemResponseDto> getItemList();
}
