package org.ordersMicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<OrderDto> saveOrder (@RequestBody OrderDocument orderDocument){

        OrderDto documentSaved = orderService.saveOrder(orderDocument);
        return ResponseEntity.ok(documentSaved);

    }

    @Operation(summary = "Get every order", description = "Creates and displays a list containing every order in the database")
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderDto>> getAllOrders (){

        return ResponseEntity.ok(orderService.findAll());
    }


}