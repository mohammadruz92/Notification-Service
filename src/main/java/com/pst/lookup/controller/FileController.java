package com.pst.lookup.controller;

import com.pst.lookup.domain.dto.FileDTO;
import com.pst.lookup.exception.ErrorResponse;
import com.pst.lookup.service.FileService;
import com.pst.lookup.util.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/file")
public class FileController {

  private final FileService service;

  public FileController(FileService service) {
    this.service = service;
  }

  @PostMapping(
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<FileDTO> add(@RequestBody FileDTO request) {
    return ResponseEntity.ok(service.add(request));
  }

  @GetMapping(
      value = "/{id}",
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  public ResponseEntity<Object> getFileById(
      @PathVariable(value = "id") Long id,
      @RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String local) {
    try {
      return ResponseEntity.ok(service.getFileById(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(
              new ErrorResponse(
                  404,
                  ResourceBundle.getBundle(Constants.BUNDLE_BASE_NAME, new Locale(local))
                      .getString("404")));
    }
  }
}
