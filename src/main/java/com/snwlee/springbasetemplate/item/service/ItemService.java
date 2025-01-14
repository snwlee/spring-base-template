package com.snwlee.springbasetemplate.item.service;

import com.snwlee.springbasetemplate.item.dto.ItemRequestDto;
import com.snwlee.springbasetemplate.item.dto.ItemResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    ItemResponseDto getItem(Long itemId);

    void addItem(ItemRequestDto itemRequestDto);

    void updateItem(Long itemId, ItemRequestDto itemRequestDto);

    void removeItem(Long itemId);

    Page<ItemResponseDto> getItemList(Pageable pageable);
}
