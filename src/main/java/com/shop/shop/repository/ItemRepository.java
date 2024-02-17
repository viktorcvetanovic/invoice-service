package com.shop.shop.repository;

import com.shop.shop.data.entity.Item;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {


    @Query(value = "select * from item i " +
            "join item_info ii on ii.item_info_id = i.item_info_fk " +
            "join stock s on ii.item_info_id = s.item_info_fk " +
            "where s.quantity != 0 and ii.discount != 0", nativeQuery = true)
    List<Item> findItemsWithNonZeroQuantityAndDiscount();
}