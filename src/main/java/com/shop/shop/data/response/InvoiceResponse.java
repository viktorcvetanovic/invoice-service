package com.shop.shop.data.response;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Value.Immutable
@JsonSerialize(as = ImmutableInvoiceResponse.class)
@JsonDeserialize(as = ImmutableInvoiceResponse.class)
public interface InvoiceResponse {
    Integer id();
    Date date();
    String name();
    Double totalPrice();
    List<ItemResponse> itemsList();

}
