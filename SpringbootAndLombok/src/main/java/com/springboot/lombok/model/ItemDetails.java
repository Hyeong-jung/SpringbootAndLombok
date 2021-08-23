package com.springboot.lombok.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ItemDetails {

    private Long itemId;
    private String imagePath;
    private String name;
    private int price;
    private int stockQuantity;

    public ItemDetails(ItemEntity itemEntity) {
        this.itemId = itemEntity.getItemId();
        this.imagePath = itemEntity.getImagePath();
        this.name = itemEntity.getName();
        this.price = itemEntity.getPrice();
        this.stockQuantity = itemEntity.getStockQuantity();
    }

	
}
