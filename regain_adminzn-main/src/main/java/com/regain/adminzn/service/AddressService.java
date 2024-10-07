package com.regain.adminzn.service;

import com.regain.adminzn.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddressesByUserId(int userId);
}
