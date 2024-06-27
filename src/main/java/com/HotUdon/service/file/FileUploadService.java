package com.HotUdon.service.file;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.dto.RegisterDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    FileUploadDTO saveFile(MultipartFile file, RegisterDTO registerDTO,String loginId) throws IOException;
    List<FileUploadDTO> saveFiles(List<MultipartFile> files, RegisterDTO registerDTO,String loginId) throws IOException;
    List<FileUploadDTO> findAllByRegisterId(Long registerId);
}
