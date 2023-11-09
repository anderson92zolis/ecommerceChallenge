package org.ecommerceChallenge.stockMicro.repository;

import org.ecommerceChallenge.stockMicro.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Integer> {
}
