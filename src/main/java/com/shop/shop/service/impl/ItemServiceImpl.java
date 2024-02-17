package com.shop.shop.service.impl;

import com.shop.shop.data.response.ItemResponse;
import com.shop.shop.mapper.ItemMapper;
import com.shop.shop.repository.InvoiceRepository;
import com.shop.shop.repository.ItemRepository;
import com.shop.shop.repository.StockRepository;
import com.shop.shop.service.ItemService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final InvoiceRepository invoiceRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemResponse> getAllDiscountItemsByInvoice(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(NoSuchElementException::new)
                .getItemList()
                .stream()
                .filter(e -> e.getDiscountOnBought() != null && e.getDiscountOnBought() != 0)
                .map(itemMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResponse> getAllDiscountItems() {
        return itemRepository.findItemsWithNonZeroQuantityAndDiscount()
                .stream()
                .map(itemMapper::toResponse)
                .collect(Collectors.toList());
    }


}