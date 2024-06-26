package com.pst.lookup.service;

import com.pst.lookup.domain.dto.FileDTO;
import com.pst.lookup.domain.entity.FileEntity;
import com.pst.lookup.domain.mapper.FileMapper;
import com.pst.lookup.repository.FileRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
public class FileServiceImpl implements FileService {

  private final FileRepository repository;

  public FileServiceImpl(FileRepository repository) {
    this.repository = repository;
  }

  @Override
  public FileDTO add(FileDTO request) {
    FileEntity entity = repository.save(FileMapper.toEntity(request));
    return request.updateId(entity.getId());
  }

  @Override
  public FileDTO getFileById(Long id) {
    FileEntity fileEntity = repository.findById(id)
            .orElseThrow(NoSuchElementException::new);
    return FileMapper.toDTO(fileEntity);
  }
}
