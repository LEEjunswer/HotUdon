package com.HotUdon.service.file;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.mapper.FileMapper;
import com.HotUdon.mapper.RegisterMapper;
import com.HotUdon.model.FileUpload;
import com.HotUdon.repository.file.FileUploadRepository;
import com.HotUdon.util.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;
    private final FileStorageProperties fileStorageProperties;

    @Override
    public FileUploadDTO saveFile(MultipartFile file, RegisterDTO registerDTO) throws IOException {
        if (!file.isEmpty()) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(fileStorageProperties.getProductUploadPath() + File.separator + filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            FileUpload fileUpload = FileUpload.builder()
                    .fileName(filename)
                    .filePath(filePath.toString())
                    .register(RegisterMapper.mapDtoToEntity(registerDTO))
                    .build();
            fileUploadRepository.save(fileUpload);
            return FileMapper.mapEntityToDto(fileUpload);
        }
        return null;
    }

    @Override
    public List<FileUploadDTO> saveFiles(List<MultipartFile> files, RegisterDTO registerDTO) throws IOException {
        List<FileUploadDTO> savedFileDTOs = new ArrayList<>();
        for (MultipartFile file : files) {
            FileUploadDTO fileUploadDTO = saveFile(file, registerDTO);
            if (fileUploadDTO != null) {
                savedFileDTOs.add(fileUploadDTO);
            }
        }
        return savedFileDTOs;
    }
}
