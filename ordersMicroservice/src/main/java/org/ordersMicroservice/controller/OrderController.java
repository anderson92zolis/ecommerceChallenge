package org.ordersMicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/v1/orders")
@Tag(name = "eCommerce", description = "this controller contains the methods to work with the orders")
public class OrderController {

    private static Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Adds an order", description = "Creates a new order and its detail")
    @ApiResponse(responseCode = "200", description = "Order created correctly",
        content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = OrderDocument.class))})
    @PostMapping("/add")
    public ResponseEntity<OrderDocument> saveOrder (@RequestBody OrderDocument orderDocument){

        OrderDocument documentSaved = orderService.saveOrder(orderDocument);
        return ResponseEntity.ok(orderDocument);

    }


}
