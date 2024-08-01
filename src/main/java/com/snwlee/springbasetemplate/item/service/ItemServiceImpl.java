package com.snwlee.springbasetemplate.item.service;

import com.snwlee.springbasetemplate.common.annotation.Retry;
import com.snwlee.springbasetemplate.common.annotation.Trace;
import com.snwlee.springbasetemplate.item.dto.ItemRequestDto;
import com.snwlee.springbasetemplate.item.dto.ItemResponseDto;
import com.snwlee.springbasetemplate.item.entity.Item;
import com.snwlee.springbasetemplate.common.exception.ErrorCode;
import com.snwlee.springbasetemplate.common.exception.ItemException;
import com.snwlee.springbasetemplate.item.repository.ItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true, timeout = 30, rollbackFor = ItemException.class)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Trace
    @Override
    public ItemResponseDto getItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new ItemException(ErrorCode.ITEM_NOT_FOUND, ErrorCode.ITEM_NOT_FOUND.getHttpStatus())
        ).toDto();
    }

    @Trace
    @Override
    public List<ItemResponseDto> getItemList() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new ItemException(ErrorCode.ITEM_NOT_FOUND, ErrorCode.ITEM_NOT_FOUND.getHttpStatus());
        }
        return items.stream()
                .map(Item::toDto)
                .toList();
    }

    @Trace
    @Retry(value = 5)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30, rollbackFor = ItemException.class)
    @Override
    public void addItem(ItemRequestDto itemRequestDto) {
        itemRepository.save(itemRequestDto.toEntity());
    }

    @Trace
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30, rollbackFor = ItemException.class)
    @Override
    public void updateItem(Long itemId, ItemRequestDto itemRequestDto) {
        itemRepository.findById(itemId).orElseThrow(
                () -> new ItemException(ErrorCode.ITEM_NOT_FOUND, ErrorCode.ITEM_NOT_FOUND.getHttpStatus())
        );
        itemRepository.save(itemRequestDto.toEntity(itemId));
    }

    @Trace
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 30, rollbackFor = ItemException.class)
    @Override
    public void removeItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}
