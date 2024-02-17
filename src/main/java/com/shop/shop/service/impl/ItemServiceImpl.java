package com.shop.shop.service.impl;

import com.shop.shop.data.response.ItemInfoResponse;
import com.shop.shop.data.response.ItemResponse;
import com.shop.shop.mapper.ItemMapper;
import com.shop.shop.repository.InvoiceRepository;
import com.shop.shop.repository.ItemInfoRepository;
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
    private final ItemInfoRepository itemInfoRepository;
    private final InvoiceRepository invoiceRepository;
    private final ItemMapper itemMapper;

    /**
     * Method get all items that are bought and they had discount by invoice.
     * @param invoiceId id of invoice
     * @return items
     */
    @Override
    public List<ItemResponse> getAllDiscountItemsByInvoice(Integer invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NoSuchElementException("Item service with that id does not exist"))
                .getItemList()
                .stream()
                .filter(e -> e.getDiscountOnBought() != null && e.getDiscountOnBought() != 0)
                .map(itemMapper::toResponseWithDiscountOnBought)
                .collect(Collectors.toList());
    }

    /**
     * This method will get all items that are currently on sale.
     * @return list of items info
     */
    @Override
    public List<ItemInfoResponse> getAllDiscountItems() {
        return itemInfoRepository.findItemsWithNonZeroQuantityAndDiscount()
                .stream()
                .map(itemMapper::toResponse)
                .collect(Collectors.toList());
    }


}