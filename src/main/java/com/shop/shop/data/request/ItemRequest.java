package com.shop.shop.data.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableItemRequest.class)
@JsonDeserialize(as = ImmutableItemRequest.class)
public interface ItemRequest {
    Integer itemInfoId();
    Integer discount();

}
