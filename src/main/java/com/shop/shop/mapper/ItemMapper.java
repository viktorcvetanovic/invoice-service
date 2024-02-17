package com.shop.shop.mapper;

import com.shop.shop.data.entity.Item;
import com.shop.shop.data.entity.ItemInfo;
import com.shop.shop.data.request.ItemRequest;
import com.shop.shop.data.response.ImmutableItemResponse;
import com.shop.shop.data.response.ItemResponse;
import com.shop.shop.repository.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ItemMapper {
    private final ItemInfoRepository itemInfoRepository;

    public ItemResponse toResponse(Item item){
        double itemPrice =  item.getItemInfo().getPrice() * item.getQuantity();
        return ImmutableItemResponse
                .builder()
                .name(item.getItemInfo().getName())
                .price(itemPrice)
                .discountPercentage(item.getItemInfo().getDiscount())
                .quantity(item.getQuantity())
                .discountValue(itemPrice / 100 * item.getItemInfo().getDiscount())
                .build();
    }


    public Item toItem(ItemRequest itemRequest){
        Item item = new Item();
        ItemInfo itemInfo = itemInfoRepository.findById(itemRequest.itemInfoId())
                .orElseThrow(() -> new NoSuchElementException("That item is currently not defined"));
        item.setItemInfo(itemInfo);
        item.setQuantity(itemRequest.quantity());
        return item;
    }



}
