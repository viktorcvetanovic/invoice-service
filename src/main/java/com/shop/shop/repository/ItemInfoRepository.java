package com.shop.shop.repository;

import com.shop.shop.data.entity.Item;
import com.shop.shop.data.entity.ItemInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemInfoRepository extends JpaRepository<ItemInfo, Integer> {

    @Query(value = "select * from item_info ii " +
            "join stock s on ii.item_info_id = s.item_info_fk " +
            "where s.quantity != 0 and ii.discount != 0", nativeQuery = true)
    List<ItemInfo> findItemsWithNonZeroQuantityAndDiscount();
}