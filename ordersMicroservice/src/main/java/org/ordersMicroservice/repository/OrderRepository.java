package org.ordersMicroservice.repository;

import org.ordersMicroservice.entity.OrderDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDocument, Integer> {



}
