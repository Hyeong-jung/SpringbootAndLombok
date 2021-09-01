package com.springboot.lombok.controller;



import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.lombok.model.AddItemRequest;
import com.springboot.lombok.model.ItemDetails;
import com.springboot.lombok.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {


	private final ItemService itemService;

	
    @GetMapping("/items/new")
    public String getNewItemPage(Model model) {
        model.addAttribute("form", new AddItemRequest());
        //return "items/registerItemForm";
        return "thymeleaf/items/registerItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@ModelAttribute @Valid AddItemRequest addItemRequest) {
    	
        Long newItemId = itemService.saveItem(addItemRequest);
        
        //return "redirect:/items/"+ newItemId;
        //return newItemId.toString();
        return "redirect:/items/new/";
    }


    @GetMapping("/items/{itemId}")
    public String getItemDetailsPage(@PathVariable("itemId") Long itemId,
                                     Model model) {
        ItemDetails item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        //return "items/itemDetails";
        return "thymeleaf/items/itemDetails";
    }

    
}
