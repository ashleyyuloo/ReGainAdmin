package com.regain.adminzn.controller;

import com.regain.adminzn.dto.OrdersDTO;
import com.regain.adminzn.dto.ProductDTO;
import com.regain.adminzn.service.ProductService;
import com.regain.adminzn.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class ProductsController {

    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public ProductsController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        List<ProductDTO> metalProducts = productService.getMetalProducts();
        List<ProductDTO> plasticProducts = productService.getPlasticProducts();
        List<ProductDTO> paperProducts = productService.getPaperProducts();
        List<ProductDTO> glassProducts = productService.getGlassProducts();
        List<ProductDTO> electronicProducts = productService.getElectronicProducts();
        model.addAttribute("products", products);
        model.addAttribute("metalProducts", metalProducts);
        model.addAttribute("plasticProducts", plasticProducts);
        model.addAttribute("paperProducts", paperProducts);
        model.addAttribute("glassProducts", glassProducts);
        model.addAttribute("electronicProducts", electronicProducts);
        return "products/index";
    }

    @GetMapping("/products/details/{id}") 
    public String viewProductDetails(@PathVariable("id") int id, Model model) {
        ProductDTO product = productService.getProductById(id);
        OrdersDTO order = orderService.getOrderByProductId(id);
        model.addAttribute("product", product);
        model.addAttribute("order", order);
        return "products/selected-product";
    }


    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProductById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product");
        }
        
        return "redirect:/products";
    }
}
