package com.HotUdon.repository.file;

import com.HotUdon.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    FileUpload findByRegisterId(Long registerId);
    List<FileUpload> findAllByRegisterId(Long registerId);
}
