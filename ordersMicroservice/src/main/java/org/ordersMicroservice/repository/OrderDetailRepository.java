package org.ordersMicroservice.repository;

import org.ordersMicroservice.entity.OrderDetailDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailDocument, Integer> {


}
