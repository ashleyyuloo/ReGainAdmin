package com.regain.adminzn.dto;

import java.util.StringJoiner;
import java.util.stream.Stream;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Integer addressID;
    private String unitNumber;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String zipCode;

    public String fullAddress() {
        StringJoiner joiner = new StringJoiner(", ");
        Stream.of(unitNumber, street, barangay, city, province, zipCode)
                .filter(part -> part != null && !part.isEmpty())
                .forEach(joiner::add);
        return joiner.toString();
    }
}
