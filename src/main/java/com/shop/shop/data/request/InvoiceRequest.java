package com.shop.shop.data.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableInvoiceRequest.class)
@JsonDeserialize(as = ImmutableInvoiceRequest.class)
public interface InvoiceRequest{
    String name();
    List<ItemRequest> itemsList();


}

