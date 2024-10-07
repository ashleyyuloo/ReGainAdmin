package com.regain.adminzn.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regain.adminzn.dto.AddressDTO;
import com.regain.adminzn.dto.ProductDTO;
import com.regain.adminzn.dto.UserDTO;
import com.regain.adminzn.model.Product;
import com.regain.adminzn.repository.OrdersRepository;
import com.regain.adminzn.repository.ProductRepository;
import com.regain.adminzn.service.ProductService;
import com.regain.adminzn.service.constants.CategoryConstants;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, OrdersRepository ordersRepository) {
        this.productRepository = productRepository;
        this.ordersRepository = ordersRepository;
    }

    private ProductDTO convertToDTO(Product product) {
        boolean isListedInOrders = ordersRepository.existsByProduct(product); 
        String listingStatus = isListedInOrders ? "Unavailable" : "Available";

        AddressDTO location = product.getLocation() != null ? AddressDTO.builder()
                .addressID(product.getLocation().getAddressID())
                .unitNumber(product.getLocation().getUnitNumber())
                .street(product.getLocation().getStreet())
                .barangay(product.getLocation().getBarangay())
                .city(product.getLocation().getCity())
                .province(product.getLocation().getProvince())
                .zipCode(product.getLocation().getZipCode())
                .build() : null;

        UserDTO seller = product.getSeller() != null ? UserDTO.builder()
                .id(product.getSeller().getId())
                .userName(product.getSeller().getUserName())
                .firstName(product.getSeller().getFirstName())
                .lastName(product.getSeller().getLastName())
                .penaltyPoints(product.getSeller().getPenaltyPoints())
                .commissionBalance(product.getSeller().getCommissionBalance())
                .profileImagePath(product.getSeller().getProfileImagePath())
                .build() : null;

        return ProductDTO.builder()
            .id(product.getId())
            .categoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null)
            .productName(product.getProductName())
            .soldPrice(product.getSoldPrice())
            .listingStatus(listingStatus)
            .imagePath(product.getImagePath()) 
            .location(location)
            .seller(seller)
            .description(product.getDescription())
            .build();
    }


    @Override
    public List<ProductDTO> getProductsBySellerId(int sellerId) {
        return productRepository.findBySellerId(sellerId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getMetalProducts() {
        return productRepository.findByCategoryName(CategoryConstants.METAL).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getPlasticProducts() {
        return productRepository.findByCategoryName(CategoryConstants.PLASTIC).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getPaperProducts() {
        return productRepository.findByCategoryName(CategoryConstants.PAPER).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getGlassProducts() {
        return productRepository.findByCategoryName(CategoryConstants.GLASS).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getElectronicProducts() {
        return productRepository.findByCategoryName(CategoryConstants.ELECTRONICS).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(int id) {
        return productRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }
}
