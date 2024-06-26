package com.pst.lookup.service;

import com.pst.lookup.domain.dto.FAQDTO;
import com.pst.lookup.domain.entity.FAQEntity;
import com.pst.lookup.domain.mapper.FAQMapper;
import com.pst.lookup.repository.FAQRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQServiceImpl implements FAQService {

  private final FAQRepository repository;

  public FAQServiceImpl(FAQRepository repository) {
    this.repository = repository;
  }

  @Override
  public FAQDTO add(FAQDTO request) {
    FAQEntity entity = repository.save(FAQMapper.toEntity(request));
    return request.updateId(entity.getId());
  }

  @Override
  public List<FAQDTO> getFAQList() {
    return FAQMapper.toFAQDTOList(repository.findAll());
  }
}
