package com.pst.lookup.service;

import com.pst.lookup.domain.dto.LanguageDTO;

import java.util.List;

public interface LanguageService {

  public LanguageDTO addLanguage(LanguageDTO request);

  public List<LanguageDTO> getLanguages();
}
