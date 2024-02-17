package com.shop.shop.data.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableItemInfoResponse.class)
@JsonDeserialize(as = ImmutableItemInfoResponse.class)
public interface ItemInfoResponse {

    String name();

    Double price();

    Integer discount();

}
