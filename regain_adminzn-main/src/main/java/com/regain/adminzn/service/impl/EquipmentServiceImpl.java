package com.regain.adminzn.service.impl;

import com.regain.adminzn.dto.AddressDTO;
import com.regain.adminzn.dto.BookingDTO;
import com.regain.adminzn.dto.EquipmentDTO;
import com.regain.adminzn.dto.UserDTO;
import com.regain.adminzn.model.Address;
import com.regain.adminzn.model.Booking;
import com.regain.adminzn.model.Equipment;
import com.regain.adminzn.model.User;
import com.regain.adminzn.repository.EquipmentRepository;
import com.regain.adminzn.service.EquipmentService;
import com.regain.adminzn.service.util.BookingUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public List<EquipmentDTO> getEquipmentByRenterId(int renterId) {
        List<Equipment> equipmentList = equipmentRepository.findByRenterId(renterId);
        return equipmentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private EquipmentDTO convertToDto(Equipment equipment) {
        List<BookingDTO> bookingDTOs = equipment.getBookings().stream()
                .map(this::convertToBookingDto)
                .peek(this::calculateTotalPrice) // Calculate total price for each booking
                .collect(Collectors.toList());
    
        return EquipmentDTO.builder()
                .id(equipment.getId())
                .name(equipment.getName())
                .price(equipment.getPrice())
                .imagePath(equipment.getImagePath())
                .location(convertToAddressDto(equipment.getLocation()))
                .renter(convertToUserDto(equipment.getRenter()))
                .rentee(convertToUserDto(getCurrentRentee(equipment)))
                .status(determineStatus(equipment))
                .bookings(bookingDTOs)
                .build();
    }
    
    private void calculateTotalPrice(BookingDTO bookingDTO) {
        double totalPrice = BookingUtil.calculateTotalPrice(
                            bookingDTO.getPrice(), 
                            bookingDTO.getStartDate(),
                            bookingDTO.getEndDate());
        bookingDTO.setTotalPrice(totalPrice);
    }
    

    private AddressDTO convertToAddressDto(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDTO.builder()
                .addressID(address.getAddressID())
                .unitNumber(address.getUnitNumber())
                .street(address.getStreet())
                .barangay(address.getBarangay())
                .city(address.getCity())
                .province(address.getProvince())
                .build();
    }

    private UserDTO convertToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .profileImagePath(user.getProfileImagePath())
                .penaltyPoints(user.getPenaltyPoints())
                .commissionBalance(user.getCommissionBalance())
                .build();
    }

    private BookingDTO convertToBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        return BookingDTO.builder()
                .id(booking.getId())
                .equipmentName(booking.getEquipment().getName())
                .renter(booking.getEquipment().getRenter().getUserName())
                .rentee(booking.getRentee().getUserName())
                .price(booking.getEquipment().getPrice())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .build();
    }

    private User getCurrentRentee(Equipment equipment) {
        Booking currentBooking = getCurrentBooking(equipment);
        return currentBooking != null ? currentBooking.getRentee() : null;
    }

    private Booking getCurrentBooking(Equipment equipment) {
        LocalDate today = LocalDate.now();
        return equipment.getBookings().stream()
                .filter(Booking::isAccepted)
                .filter(booking -> !today.isBefore(booking.getStartDate().toLocalDate()) &&
                                   !today.isAfter(booking.getEndDate().toLocalDate()))
                .findFirst()
                .orElse(null);
    }

    private String determineStatus(Equipment equipment) {
        return getCurrentBooking(equipment) != null ? "Booked" : "Available";
    }

    @Override
    public EquipmentDTO getEquipmentById(int id) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            return convertToDto(equipment);
        } else {
            throw new RuntimeException("Equipment not found with id: " + id);
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> getAvailableEquipment() {
        return equipmentRepository.findAll().stream()
                .map(this::convertToDto)
                .filter(equipment -> "Available".equals(equipment.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipmentDTO> getBookedEquipment() {
        return equipmentRepository.findAll().stream()
                .map(this::convertToDto)
                .filter(equipment -> "Booked".equals(equipment.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEquipmentById(int id) {
        equipmentRepository.deleteById(id);
    }

}
