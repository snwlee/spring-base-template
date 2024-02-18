package com.pgrrr.springbasetemplate.service;

import com.pgrrr.springbasetemplate.dto.ItemRequestDto;
import com.pgrrr.springbasetemplate.dto.ItemResponseDto;

import java.util.List;

public interface ItemService {

    ItemResponseDto getItem(Long itemId);

    void addItem(ItemRequestDto itemRequestDto);

    void updateItem(Long itemId, ItemRequestDto itemRequestDto);

    void removeItem(Long itemId);

    List<ItemResponseDto> getItemList();
}
