package com.pst.lookup.repository;

import com.pst.lookup.domain.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
  Optional<FileEntity> findById(Long id);
}
