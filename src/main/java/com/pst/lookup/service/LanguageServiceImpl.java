package com.pst.lookup.service;

import com.pst.lookup.domain.dto.LanguageDTO;
import com.pst.lookup.domain.entity.LanguageEntity;
import com.pst.lookup.domain.mapper.LanguageMapper;
import com.pst.lookup.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

  private final LanguageRepository repository;

  public LanguageServiceImpl(LanguageRepository repository) {
    this.repository = repository;
  }

  @Override
  public LanguageDTO addLanguage(LanguageDTO request) {
    LanguageEntity entity = LanguageMapper.toEntity(request);
    repository.save(entity);
    return request.updateId(entity.getId());
  }

  @Override
  public List<LanguageDTO> getLanguages() {
    return LanguageMapper.toDTOList(repository.findAll());
  }
}
