package com.pgrrr.springbasetemplate.controller;

import com.pgrrr.springbasetemplate.dto.ItemRequestDto;
import com.pgrrr.springbasetemplate.dto.ItemResponseDto;
import com.pgrrr.springbasetemplate.service.ItemService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "ItemRestController")
@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api")
public class ItemRestController {

    private final ItemService baseService;

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable("itemId") Long itemId) {
        ItemResponseDto itemResponseDto = baseService.getItem(itemId);
        return ResponseEntity.ok().body(itemResponseDto);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponseDto>> getItemList() {
        List<ItemResponseDto> itemResponseDtoList = baseService.getItemList();
        return ResponseEntity.ok().body(itemResponseDtoList);
    }

    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody @Valid ItemRequestDto itemRequestDto) {
        baseService.addItem(itemRequestDto);
        return ResponseEntity.ok().body("");
    }

    @PatchMapping("/items/{itemId}")
    public ResponseEntity<String> updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Valid ItemRequestDto itemRequestDto) {
        baseService.updateItem(itemId, itemRequestDto);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<String> removeItem(@PathVariable("itemId") Long itemId) {
        baseService.removeItem(itemId);
        return ResponseEntity.ok().body("");
    }
}
