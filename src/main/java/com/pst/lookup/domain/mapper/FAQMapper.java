package com.pst.lookup.domain.mapper;

import com.pst.lookup.domain.dto.FAQDTO;
import com.pst.lookup.domain.entity.FAQEntity;

import java.util.List;

public class FAQMapper {

  public static FAQEntity toEntity(FAQDTO dto) {

    FAQEntity entity = new FAQEntity();

    entity.setQuestion(dto.question());
    entity.setAnswer(dto.answer());
    entity.setLanguageId(dto.languageId());

    return entity;
  }

  public static List<FAQDTO> toFAQDTOList(List<FAQEntity> entities) {
    return entities.stream().map(FAQMapper::toFAQDTO).toList();
  }

  public static FAQDTO toFAQDTO(FAQEntity entity) {
    return new FAQDTO(
        entity.getId(), entity.getQuestion(), entity.getAnswer(), entity.getLanguageId());
  }
}
