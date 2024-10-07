package com.regain.adminzn.service;

import java.util.List;

import com.regain.adminzn.dto.OrdersDTO;
import com.regain.adminzn.dto.BookingDTO;

public interface OrderService {
    List<OrdersDTO> getProductTransactions();
    List<BookingDTO> getEquipmentTransactions();
    OrdersDTO getOrderByProductId(int productId);
    int getTotalTransactions();
    double getAverageTransactionValue();
}
