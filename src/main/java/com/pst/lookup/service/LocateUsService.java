package com.pst.lookup.service;

import com.pst.lookup.domain.dto.BranchDTO;

import java.util.List;

public interface LocateUsService {

  public List<BranchDTO> getBranches();

  public BranchDTO addBranch(BranchDTO request);
}
