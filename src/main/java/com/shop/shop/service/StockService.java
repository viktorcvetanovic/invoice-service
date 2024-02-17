package com.shop.shop.service;

import com.shop.shop.data.entity.Item;

import java.util.List;

public interface StockService {

    boolean checkItemsQuantity(List<Item> itemList);

    void updateItemsQuantity(List<Item> itemList);
}
