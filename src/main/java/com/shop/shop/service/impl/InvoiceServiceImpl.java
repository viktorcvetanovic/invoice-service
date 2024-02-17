package com.shop.shop.service.impl;

import com.shop.shop.data.entity.Invoice;
import com.shop.shop.data.entity.Item;
import com.shop.shop.data.entity.ItemInfo;
import com.shop.shop.data.entity.Stock;
import com.shop.shop.data.request.InvoiceRequest;
import com.shop.shop.data.response.InvoiceResponse;
import com.shop.shop.exceptions.InternalErrorException;
import com.shop.shop.mapper.InvoiceMapper;
import com.shop.shop.repository.InvoiceRepository;
import com.shop.shop.repository.StockRepository;
import com.shop.shop.service.InvoiceService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.shop.shop.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final StockService stockService;
    private final InvoiceMapper invoiceMapper;

    /**
     * Method used to save invoice, with logic to combine same items and sum their quantity.
     * @param invoiceRequest request invoice.
     * @return saved invoice
     */
    @Override
    public InvoiceResponse save(InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceMapper.toInvoice(invoiceRequest);
        List<Item> itemList = sumItemInfoQuantities(invoice.getItemList());
        invoice.setItemList(itemList);
        if (!stockService.checkItemsQuantity(itemList)) {
            throw new InternalErrorException("There is no enough items in stock");
        }
        stockService.updateItemsQuantity(itemList);
        return invoiceMapper.toResponse(invoiceRepository.save(invoice));
    }


    /**
     * Util method which merge items list and sum their quantity.
     * @param itemList list of items to be merged
     * @return merged items with summed quantities
     */
    private List<Item> sumItemInfoQuantities(List<Item> itemList){
        Map<ItemInfo, Item> itemInfoMap = itemList.stream()
                .collect(Collectors.toMap(
                        Item::getItemInfo,
                        Function.identity(),
                        (existingItem, newItem) -> {
                            existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
                            return existingItem;
                        }
                ));

        itemInfoMap.values().forEach(item -> {
            item.setDiscountOnBought(item.getItemInfo().getDiscount());
        });

        return new ArrayList<>(itemInfoMap.values());
    }


}