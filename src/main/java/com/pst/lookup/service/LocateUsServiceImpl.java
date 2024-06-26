package com.pst.lookup.service;

import com.pst.lookup.domain.dto.BranchDTO;
import com.pst.lookup.domain.entity.BranchEntity;
import com.pst.lookup.domain.mapper.LocateUsMapper;
import com.pst.lookup.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocateUsServiceImpl implements LocateUsService {

  private final BranchRepository branchRepository;

  public LocateUsServiceImpl(BranchRepository branchRepository) {
    this.branchRepository = branchRepository;
  }

  @Override
  public List<BranchDTO> getBranches() {
    return LocateUsMapper.toBranchDTOList(branchRepository.findAll());
  }

  @Override
  public BranchDTO addBranch(BranchDTO request) {
    branchRepository.save(LocateUsMapper.toBranchEntity(request));
    return request;
  }
}
