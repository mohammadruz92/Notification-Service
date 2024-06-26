package com.pst.lookup.controller;

import com.pst.lookup.domain.dto.BranchDTO;
import com.pst.lookup.service.LocateUsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locateUs")
public class LocateUsController {

  private final LocateUsService service;

  public LocateUsController(LocateUsService service) {
    this.service = service;
  }

  @GetMapping(
      value = "/branch",
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<List<BranchDTO>> getList() {
    return ResponseEntity.ok(service.getBranches());
  }

  @PostMapping(
      value = "/branch",
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<BranchDTO> addBranch(@RequestBody BranchDTO request) {
    return ResponseEntity.ok(service.addBranch(request));
  }
}
