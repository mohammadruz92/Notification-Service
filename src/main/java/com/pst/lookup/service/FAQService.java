package com.pst.lookup.service;

import com.pst.lookup.domain.dto.FAQDTO;

import java.util.List;

public interface FAQService {

    public FAQDTO add(FAQDTO request);

    public List<FAQDTO> getFAQList();
}
