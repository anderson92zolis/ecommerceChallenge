package org.ordersMicroservice.service;

import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetailDocument> saveOrderDetail(OrderDocument orderDocument) {

        List<OrderDetailDocument> details = orderDocument.getOrderDetail();
        details.stream().forEach(s -> orderDetailRepository.save(s));
        return details;
    }
}
