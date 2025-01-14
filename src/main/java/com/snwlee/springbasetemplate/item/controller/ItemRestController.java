package com.snwlee.springbasetemplate.item.controller;

import com.snwlee.springbasetemplate.common.entity.BaseResponse;
import com.snwlee.springbasetemplate.item.dto.ItemRequestDto;
import com.snwlee.springbasetemplate.item.dto.ItemResponseDto;
import com.snwlee.springbasetemplate.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api")
public class ItemRestController {

    private final ItemService itemService;

    @GetMapping("/items/{itemId}")
    public ResponseEntity<BaseResponse<ItemResponseDto>> getItem(@PathVariable("itemId") Long itemId) {
        ItemResponseDto itemResponseDto = itemService.getItem(itemId);
        return ResponseEntity.ok(BaseResponse.success(itemResponseDto));
    }

    @GetMapping("/items")
    public ResponseEntity<BaseResponse<Page<ItemResponseDto>>> getItemList(@PageableDefault(size = 10, sort = "itemName", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ItemResponseDto> itemResponseDtoPage = itemService.getItemList(pageable);
        return ResponseEntity.ok(BaseResponse.success(itemResponseDtoPage));
    }

    @PostMapping("/items")
    public ResponseEntity<BaseResponse<String>> addItem(@RequestBody @Valid ItemRequestDto itemRequestDto) {
        itemService.addItem(itemRequestDto);
        return ResponseEntity.ok(BaseResponse.success());
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<BaseResponse<String>> updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Valid ItemRequestDto itemRequestDto) {
        itemService.updateItem(itemId, itemRequestDto);
        return ResponseEntity.ok(BaseResponse.success());
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<BaseResponse<String>> removeItem(@PathVariable("itemId") Long itemId) {
        itemService.removeItem(itemId);
        return ResponseEntity.ok(BaseResponse.success());
    }
}
