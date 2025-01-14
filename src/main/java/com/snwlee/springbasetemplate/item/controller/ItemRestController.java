package com.snwlee.springbasetemplate.item.controller;

import com.snwlee.springbasetemplate.common.entity.BaseResponse;
import com.snwlee.springbasetemplate.item.dto.ItemRequestDto;
import com.snwlee.springbasetemplate.item.dto.ItemResponseDto;
import com.snwlee.springbasetemplate.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api")
public class ItemRestController {

    private final ItemService baseService;

    @GetMapping("/items/{itemId}")
    public ResponseEntity<BaseResponse<ItemResponseDto>> getItem(@PathVariable("itemId") Long itemId) {
        ItemResponseDto itemResponseDto = baseService.getItem(itemId);
        return ResponseEntity.ok(BaseResponse.success(itemResponseDto));
    }

    @GetMapping("/items")
    public ResponseEntity<BaseResponse<List<ItemResponseDto>>> getItemList() {
        List<ItemResponseDto> itemResponseDtoList = baseService.getItemList();
        return ResponseEntity.ok(BaseResponse.success(itemResponseDtoList));
    }

    @PostMapping("/items")
    public ResponseEntity<BaseResponse<String>> addItem(@RequestBody @Valid ItemRequestDto itemRequestDto) {
        baseService.addItem(itemRequestDto);
        return ResponseEntity.ok(BaseResponse.success());
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<BaseResponse<String>> updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Valid ItemRequestDto itemRequestDto) {
        baseService.updateItem(itemId, itemRequestDto);
        return ResponseEntity.ok(BaseResponse.success());
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<BaseResponse<String>> removeItem(@PathVariable("itemId") Long itemId) {
        baseService.removeItem(itemId);
        return ResponseEntity.ok(BaseResponse.success());
    }
}
