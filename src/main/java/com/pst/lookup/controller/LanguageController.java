package com.pst.lookup.controller;

import com.pst.lookup.domain.dto.LanguageDTO;
import com.pst.lookup.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {

  private final LanguageService service;

  public LanguageController(LanguageService service) {
    this.service = service;
  }

  @PostMapping(
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<LanguageDTO> add(@RequestBody LanguageDTO request) {
    return ResponseEntity.ok(service.addLanguage(request));
  }

  @GetMapping(
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<List<LanguageDTO>> getList() {
    return ResponseEntity.ok(service.getLanguages());
  }
}
