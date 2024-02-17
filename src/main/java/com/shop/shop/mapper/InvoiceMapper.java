package com.shop.shop.mapper;

import com.shop.shop.data.entity.Invoice;
import com.shop.shop.data.request.InvoiceRequest;
import com.shop.shop.data.response.ImmutableInvoiceResponse;
import com.shop.shop.data.response.InvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {
    private final ItemMapper itemMapper;

    public InvoiceResponse toResponse(Invoice invoice) {
        Double entirePrice = invoice.getItemList().stream()
                .mapToDouble(item ->{
                    double itemPrice =item.getItemInfo().getPrice() * item.getQuantity();
                    return itemPrice - (itemPrice / 100 * item.getItemInfo().getDiscount());
                } )
                .sum();
        return ImmutableInvoiceResponse
                .builder()
                .id(invoice.getId())
                .date(invoice.getDate())
                .name(invoice.getName())
                .itemsList(invoice.getItemList()
                        .stream()
                        .map(itemMapper::toResponse)
                        .collect(Collectors.toList()))
                .totalPrice(entirePrice)
                .build();
    }



    public Invoice toInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setDate(new Date());
        invoice.setName(invoiceRequest.name());
        invoice.setItemList(invoiceRequest.itemsList()
                .stream()
                .map(itemMapper::toItem)
                .collect(Collectors.toList()));
        return invoice;
    }


}
