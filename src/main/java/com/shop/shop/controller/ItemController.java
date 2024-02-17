package com.shop.shop.controller;

import com.shop.shop.data.response.ItemResponse;
import com.shop.shop.service.*;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/discount/invoice/{invoiceId}")
	@ApiOperation(value = "", nickname = "getAllDiscountItemsByInvoice")
	public ResponseEntity<List<ItemResponse>> getAllDiscountItemsByInvoice(@PathVariable Integer invoiceId) {
		return ResponseEntity.ok(itemService.getAllDiscountItemsByInvoice(invoiceId));
	}

	@GetMapping("/discount")
	@ApiOperation(value = "", nickname = "getAllDiscountItems")
	public ResponseEntity<List<ItemResponse>> getAllDiscountItems() {
		return ResponseEntity.ok(itemService.getAllDiscountItems());
	}

}

