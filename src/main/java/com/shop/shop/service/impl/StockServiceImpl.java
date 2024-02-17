package com.shop.shop.service.impl;

import com.shop.shop.data.entity.Item;
import com.shop.shop.data.entity.Stock;
import com.shop.shop.repository.StockRepository;
import com.shop.shop.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    /**
     * Method used to check if there is enough items in stock.
     * @param itemList list of items
     * @return true if there is
     */
    @Override
    public boolean checkItemsQuantity(List<Item> itemList) {
        boolean hasQuantity = true;
        for (Item item : itemList) {
            Stock stock = stockRepository.findByItemInfo_Id(item.getItemInfo().getId())
                    .orElseThrow(() -> new NoSuchElementException("There is no stock with that item info id"));
            if (stock.getQuantity() < item.getQuantity()) {
                return false;
            }
        }
        return hasQuantity;
    }

    /**
     * Method used to update items quantity.
     * @param itemList list of items
     */
    @Override
    public void updateItemsQuantity(List<Item> itemList) {
        List<Stock> stockList = itemList.stream().map(item ->
        {
            Stock stock = stockRepository.findByItemInfo_Id(item.getItemInfo().getId())
                    .orElseThrow(() -> new NoSuchElementException("There is no stock with that item info id"));
            stock.setQuantity(stock.getQuantity() - item.getQuantity());
            return stock;
        }).collect(Collectors.toList());
        stockRepository.saveAll(stockList);
    }
}
