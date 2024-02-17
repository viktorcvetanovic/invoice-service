package com.shop.shop.repository;

import com.shop.shop.data.entity.ItemInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInfoRepository extends JpaRepository<ItemInfo, Integer> {

}