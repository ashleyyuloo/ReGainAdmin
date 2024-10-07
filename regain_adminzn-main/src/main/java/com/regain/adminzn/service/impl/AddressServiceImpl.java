package com.regain.adminzn.service.impl;

import com.regain.adminzn.dto.AddressDTO;
import com.regain.adminzn.model.Address;
import com.regain.adminzn.repository.AddressRepository;
import com.regain.adminzn.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> getAddressesByUserId(int userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AddressDTO convertToDTO(Address address) {
        return AddressDTO.builder()
                .addressID(address.getAddressID())
                .unitNumber(address.getUnitNumber())
                .street(address.getStreet())
                .barangay(address.getBarangay())
                .city(address.getCity())
                .province(address.getProvince())
                .zipCode(address.getZipCode())
                .build();
    }
}
