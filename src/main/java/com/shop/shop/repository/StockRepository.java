package com.shop.shop.repository;

import com.shop.shop.data.entity.Stock;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Optional<Stock> findByItemInfo_Id(Integer itemInfoId);
}