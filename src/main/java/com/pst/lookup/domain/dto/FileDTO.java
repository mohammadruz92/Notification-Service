package com.pst.lookup.domain.dto;

public record FileDTO(Long id, String name, String data) {

  public FileDTO updateId(Long id) {
    return new FileDTO(id, this.name(), "");
  }
}
