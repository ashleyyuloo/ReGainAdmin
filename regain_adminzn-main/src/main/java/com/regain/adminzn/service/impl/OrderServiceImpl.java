package com.regain.adminzn.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.regain.adminzn.dto.BookingDTO;
import com.regain.adminzn.dto.OrdersDTO;
import com.regain.adminzn.dto.UserDTO;
import com.regain.adminzn.model.Booking;
import com.regain.adminzn.model.Orders;
import com.regain.adminzn.repository.EquipmentRepository;
import com.regain.adminzn.repository.OrdersRepository;
import com.regain.adminzn.service.OrderService;
import com.regain.adminzn.service.util.BookingUtil;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public List<OrdersDTO> getProductTransactions() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getEquipmentTransactions() {
        List<Booking> bookings = equipmentRepository.findAll()
            .stream().flatMap(equipment -> equipment.getBookings().stream())
            .collect(Collectors.toList());

        return bookings.stream().map(booking -> {
            double totalPrice = BookingUtil.calculateTotalPrice(
                booking.getEquipment().getPrice(),
                booking.getStartDate(),
                booking.getEndDate()
            );

            return BookingDTO.builder()
                    .id(booking.getId())
                    .equipmentName(booking.getEquipment().getName())
                    .renter(booking.getEquipment().getRenter().getUserName())
                    .rentee(booking.getRentee().getUserName())
                    .price(booking.getEquipment().getPrice())
                    .startDate(booking.getStartDate())
                    .endDate(booking.getEndDate())
                    .totalPrice(totalPrice)
                    .equipmentId(booking.getEquipment().getId())
                    .build();
        }).collect(Collectors.toList());
    }


    private OrdersDTO convertToDTO(Orders order) {
    return OrdersDTO.builder()
            .id(order.getId())
            .productName(order.getProduct().getProductName())
            .seller(order.getProduct().getSeller().getUserName())
            .buyer(UserDTO.builder()
                        .id(order.getBuyer().getId())
                        .userName(order.getBuyer().getUserName())
                        .firstName(order.getBuyer().getFirstName())
                        .lastName(order.getBuyer().getLastName())
                        .penaltyPoints(order.getBuyer().getPenaltyPoints())
                        .commissionBalance(order.getBuyer().getCommissionBalance())
                        .profileImagePath(order.getBuyer().getProfileImagePath())
                        .build())
            .sellingPrice(order.getProduct().getSoldPrice())
            .boughtPrice(order.getTotalAmount())
            .status(order.getStatus())
            .paymentType(order.getPaymentMethod() != null ? order.getPaymentMethod().getPaymentType() : null)
            .referenceNumber(order.getPaymentMethod() != null ? order.getPaymentMethod().getReferenceNumber() : null)
            .deliveryMethod(order.getDeliveryMethod())
            .productId(order.getProduct().getId())
            .build();
}

    @Override
    public OrdersDTO getOrderByProductId(int productId) {
        Orders order = ordersRepository.findByProductId(productId);
        return order != null ? convertToDTO(order) : null;
    }

    @Override
    public int getTotalTransactions() {
        return (int) ordersRepository.count();
    }

    @Override
    public double getAverageTransactionValue() {
        List<Orders> orders = ordersRepository.findAll();
        double totalAmount = orders.stream().mapToDouble(Orders::getTotalAmount).sum();
        int totalTransactions = orders.size();
        return totalTransactions == 0 ? 0 : totalAmount / totalTransactions;
    }
}

