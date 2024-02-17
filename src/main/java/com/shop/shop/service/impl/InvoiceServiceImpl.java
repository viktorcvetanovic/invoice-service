package com.shop.shop.service.impl;

import com.shop.shop.data.entity.Invoice;
import com.shop.shop.data.entity.Item;
import com.shop.shop.data.entity.Stock;
import com.shop.shop.data.request.InvoiceRequest;
import com.shop.shop.data.response.InvoiceResponse;
import com.shop.shop.mapper.InvoiceMapper;
import com.shop.shop.repository.InvoiceRepository;
import com.shop.shop.repository.StockRepository;
import com.shop.shop.service.InvoiceService;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final StockRepository stockRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    @Transactional
    public InvoiceResponse save(InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceMapper.toInvoice(invoiceRequest);
        if (!checkItemsQuantity(invoice.getItemList())) {
            throw new RuntimeException("There is no enough items in stock");
        }
        Invoice savedInvoice = invoiceRepository.save(invoice);
        updateItemsQuantity(savedInvoice.getItemList());

        return invoiceMapper.toResponse(invoice);
    }


    private boolean checkItemsQuantity(List<Item> itemList) {
        boolean hasQuantity = true;
        for (Item item : itemList) {
            Stock stock = stockRepository.findByItemInfo_Id(item.getItemInfo().getId())
                    .orElseThrow(NoSuchElementException::new);
            if (stock.getQuantity() == 0) {
                return false;
            }
        }
        return hasQuantity;
    }

    private void updateItemsQuantity(List<Item> itemList) {
        for (Item item : itemList) {
            Stock stock = stockRepository.findByItemInfo_Id(item.getItemInfo().getId())
                    .orElseThrow(NoSuchElementException::new);
            stock.setQuantity(stock.getQuantity() - 1);
            stockRepository.save(stock);
        }
    }

}