package com.pst.lookup.domain.dto;

public record LanguageDTO(Long id, String name, String code) {
  public LanguageDTO updateId(Long id) {
    return new LanguageDTO(id, this.name(), this.code());
  }
}
