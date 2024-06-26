package com.pst.lookup.domain.dto;

import java.util.List;

public record BranchDTO(
    Long id,
    String name,
    List<AddressDTO> address,
    Integer latitude,
    Integer longitude,
    String phoneNumber,
    String email) {}
