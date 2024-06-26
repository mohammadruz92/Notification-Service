package com.pst.lookup.controller;

import com.pst.lookup.domain.dto.FAQDTO;
import com.pst.lookup.service.FAQService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
public class FAQController {

  private final FAQService service;

  public FAQController(FAQService service) {
    this.service = service;
  }

  @PostMapping(
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<FAQDTO> add(@RequestBody FAQDTO request) {
    return ResponseEntity.ok(service.add(request));
  }

  @GetMapping(
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<List<FAQDTO>> getList() {
    return ResponseEntity.ok(service.getFAQList());
  }
}
