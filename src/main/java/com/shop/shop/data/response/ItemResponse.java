package com.shop.shop.data.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableItemResponse.class)
@JsonDeserialize(as = ImmutableItemResponse.class)
public interface ItemResponse {

    String name();

    Double price();

    Double discountValue();

    Integer discountPercentage();

    Integer quantity();

}
