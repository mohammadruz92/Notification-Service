package com.pst.lookup.domain.mapper;

import com.pst.lookup.domain.dto.FileDTO;
import com.pst.lookup.domain.entity.FileEntity;

public class FileMapper {

  public static FileEntity toEntity(FileDTO dto) {

    FileEntity entity = new FileEntity();
    entity.setName(dto.name());
    entity.setData(dto.data());

    return entity;
  }

  public static FileDTO toDTO(FileEntity entity) {
    return new FileDTO(entity.getId(), entity.getName(), entity.getData());
  }
}
