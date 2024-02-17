package com.shop.shop.service;

import com.shop.shop.data.response.ItemResponse;

import java.util.List;

public interface ItemService {

	List<ItemResponse> getAllDiscountItemsByInvoice(Integer invoiceId);

	List<ItemResponse> getAllDiscountItems();
}