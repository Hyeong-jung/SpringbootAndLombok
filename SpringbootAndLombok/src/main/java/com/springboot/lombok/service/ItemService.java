package com.springboot.lombok.service;



import com.springboot.lombok.model.AddItemRequest;
import com.springboot.lombok.model.ItemDetails;
import com.springboot.lombok.model.ItemEntity;
import com.springboot.lombok.model.ItemRegisteredEvent;
import com.springboot.lombok.repository.ItemRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class ItemService {

	private final ItemRepository itemRepository;
	

    public Long saveItem(AddItemRequest request) {
        ItemEntity newItem = ItemEntity.builder()
                .name(request.getName())
                .imagePath(request.getImagePath())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .categoryId(request.getCategoryId())
                .build();
        ItemEntity savedItem = itemRepository.save(newItem);

        return savedItem.getItemId();
    }

    public ItemDetails findItem(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ItemDetails(itemEntity);
    }

	
}
