package com.pst.lookup.service;

import com.pst.lookup.domain.dto.FileDTO;

import java.util.Optional;

public interface FileService {

    public FileDTO add(FileDTO request);

    FileDTO getFileById(Long id);
}
