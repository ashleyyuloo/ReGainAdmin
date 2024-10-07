package com.regain.adminzn.service;

import com.regain.adminzn.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getMetalProducts();
    List<ProductDTO> getPlasticProducts();
    List<ProductDTO> getPaperProducts();
    List<ProductDTO> getGlassProducts();
    List<ProductDTO> getElectronicProducts();
    List<ProductDTO> getProductsBySellerId(int sellerId);
    ProductDTO getProductById(int id);
    void deleteProductById(int id);
}
