package com.shop.shop.controller;

import com.shop.shop.data.request.InvoiceRequest;
import com.shop.shop.data.response.InvoiceResponse;
import com.shop.shop.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
	private final InvoiceService invoiceService;


	@PostMapping
	@ApiOperation(value = "", nickname = "saveInvoice")
	public ResponseEntity<InvoiceResponse> saveInvoice(@RequestBody InvoiceRequest invoiceRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(invoiceRequest));
	}

}

