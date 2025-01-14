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
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    /**
     * ID로 아이템 조회
     *
     * @param itemId 아이템 ID
     * @return ItemResponseDto
     * @throws ItemException 아이템이 없을 경우
     */
    @Trace
    @Override
    public ItemResponseDto getItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemException(ErrorCode.ITEM_NOT_FOUND, ErrorCode.ITEM_NOT_FOUND.getHttpStatus()))
                .toDto();
    }

    /**
     * 모든 아이템 조회
     *
     * @return List<ItemResponseDto>
     * @throws ItemException 아이템이 없을 경우
     */
    @Trace
    @Override
    public List<ItemResponseDto> getItemList() {
        return itemRepository.findAll().stream()
                .map(Item::toDto)
                .toList();
    }

    /**
     * 아이템 추가
     *
     * @param itemRequestDto 추가할 아이템 정보
     */
    @Trace
    @Retry(value = 5)
    @Transactional
    @Override
    public void addItem(ItemRequestDto itemRequestDto) {
        itemRepository.save(itemRequestDto.toEntity());
    }

    /**
     * 아이템 정보 수정
     *
     * @param itemId         수정할 아이템 ID
     * @param itemRequestDto 수정할 아이템 정보
     * @throws ItemException 아이템이 없을 경우
     */
    @Trace
    @Transactional
    @Override
    public void updateItem(Long itemId, ItemRequestDto itemRequestDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemException(ErrorCode.ITEM_NOT_FOUND, ErrorCode.ITEM_NOT_FOUND.getHttpStatus()));
        item.update(itemRequestDto);
    }

    /**
     * 아이템 삭제
     *
     * @param itemId 삭제할 아이템 ID
     */
    @Trace
    @Transactional
    @Override
    public void removeItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}