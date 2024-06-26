package com.pst.lookup.domain.dto;

public record FAQDTO(Long id, String question, String answer, Long languageId) {

  public FAQDTO updateId(Long id) {
    return new FAQDTO(id, this.question(), this.answer(), this.languageId);
  }
}
