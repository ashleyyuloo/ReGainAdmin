package com.regain.adminzn.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.regain.adminzn.dto.BookingDTO;
import com.regain.adminzn.dto.EquipmentDTO;
import com.regain.adminzn.dto.OrdersDTO;
import com.regain.adminzn.dto.ProductDTO;
import com.regain.adminzn.service.EquipmentService;
import com.regain.adminzn.service.OrderService;
import com.regain.adminzn.service.ProductService;

@Controller
public class OrdersController {

    private final OrderService orderService;
    private final ProductService productService;
    private final EquipmentService equipmentService;

    public OrdersController(OrderService orderService, ProductService productService, EquipmentService equipmentService) {
        this.orderService = orderService;
        this.productService = productService;
        this.equipmentService = equipmentService;
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model) {
        List<OrdersDTO> productTransactions = orderService.getProductTransactions();
        List<BookingDTO> equipmentTransactions = orderService.getEquipmentTransactions();

        model.addAttribute("productTransactions", productTransactions);
        model.addAttribute("equipmentTransactions", equipmentTransactions);
        return "transactions/index";
    }

    @GetMapping("/products/{id}")
    public String showProductDetails(@PathVariable("id") int productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "products/selected-product"; 
    }


    @GetMapping("/equipment/{id}")
    public String showEquipmentDetails(@PathVariable("id") int equipmentId, Model model) {
        EquipmentDTO equipment = equipmentService.getEquipmentById(equipmentId);
        model.addAttribute("equipment", equipment);
        return "equipment/selected-equipment"; 
    }

}