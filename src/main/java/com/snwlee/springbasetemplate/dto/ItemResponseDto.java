package com.pgrrr.springbasetemplate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class ItemResponseDto {

    private String itemName;

    private String itemDesc;

}
