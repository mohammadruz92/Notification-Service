package com.pst.lookup.domain.mapper;

import com.pst.lookup.domain.dto.LanguageDTO;
import com.pst.lookup.domain.entity.LanguageEntity;

import java.util.List;

public class LanguageMapper {

  public static LanguageEntity toEntity(LanguageDTO dto) {

    LanguageEntity entity = new LanguageEntity();

    entity.setCode(dto.code());
    entity.setName(dto.name());

    return entity;
  }

  public static List<LanguageDTO> toDTOList(List<LanguageEntity> entities) {
    return entities.stream().map(LanguageMapper::toDTO).toList();
  }

  public static LanguageDTO toDTO(LanguageEntity entity) {
    return new LanguageDTO(entity.getId(), entity.getName(), entity.getCode());
  }
}
