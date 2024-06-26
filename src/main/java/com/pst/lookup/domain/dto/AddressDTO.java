package com.pst.lookup.domain.dto;

public record AddressDTO(
    String country, String city, String state, String street, String zipCode, Long languageId) {}
