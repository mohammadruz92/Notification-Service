package com.pst.lookup.domain.mapper;

import com.pst.lookup.domain.dto.AddressDTO;
import com.pst.lookup.domain.dto.BranchDTO;
import com.pst.lookup.domain.dto.LanguageDTO;
import com.pst.lookup.domain.entity.AddressEntity;
import com.pst.lookup.domain.entity.BranchEntity;
import com.pst.lookup.domain.entity.LanguageEntity;

import java.util.List;

public class LocateUsMapper {

  public static BranchEntity toBranchEntity(BranchDTO dto) {

    BranchEntity entity = new BranchEntity();

    entity.setName(dto.name());
    entity.setEmail(dto.email());
    entity.setLatitude(dto.latitude());
    entity.setLongitude(dto.longitude());
    entity.setPhoneNumber(dto.phoneNumber());
    entity.setAddress(toAddressEntityList(dto.address()));

    return entity;
  }

  public static List<AddressEntity> toAddressEntityList(List<AddressDTO> addressEntities) {
    return addressEntities.stream().map(LocateUsMapper::toAddressEntity).toList();
  }

  public static AddressEntity toAddressEntity(AddressDTO dto) {

    AddressEntity addressEntity = new AddressEntity();

    addressEntity.setCity(dto.city());
    addressEntity.setCountry(dto.country());
    addressEntity.setState(dto.state());
    addressEntity.setLanguageId(dto.languageId());
    addressEntity.setStreet(dto.street());
    addressEntity.setZipCode(dto.zipCode());

    return addressEntity;
  }

  public static List<BranchDTO> toBranchDTOList(List<BranchEntity> entities) {
    return entities.stream().map(LocateUsMapper::toBranchDTO).toList();
  }

  public static BranchDTO toBranchDTO(BranchEntity entity) {
    return new BranchDTO(
        entity.getId(),
        entity.getName(),
        toAddressDTOList(entity.getAddress()),
        entity.getLatitude(),
        entity.getLongitude(),
        entity.getPhoneNumber(),
        entity.getEmail());
  }

  public static List<AddressDTO> toAddressDTOList(List<AddressEntity> entities) {
    return entities.stream().map(LocateUsMapper::toAddressDTO).toList();
  }

  public static AddressDTO toAddressDTO(AddressEntity entity) {
    return new AddressDTO(
        entity.getCountry(),
        entity.getCity(),
        entity.getState(),
        entity.getStreet(),
        entity.getZipCode(),
        entity.getLanguageId());
  }
}
