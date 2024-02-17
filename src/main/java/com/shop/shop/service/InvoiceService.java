package com.shop.shop.service;

import com.shop.shop.data.request.InvoiceRequest;
import com.shop.shop.data.response.InvoiceResponse;

public interface InvoiceService {


	InvoiceResponse save(InvoiceRequest invoice);


}