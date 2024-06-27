package com.HotUdon.service.file;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.mapper.FileMapper;
import com.HotUdon.mapper.RegisterMapper;
import com.HotUdon.model.FileUpload;
import com.HotUdon.model.Register;
import com.HotUdon.repository.file.FileUploadRepository;
import com.HotUdon.repository.register.RegisterRepository;
import com.HotUdon.util.FileStorageProperties;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;
    private final FileStorageProperties fileStorageProperties;
    private  final RegisterRepository registerRepository;

    @Override
    @Transactional
    public FileUploadDTO saveFile(MultipartFile file, RegisterDTO registerDTO,String loginId) throws IOException {
        if (!file.isEmpty()) {
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String filename = loginId+"_" + originalFilename;
            Path filePath = Paths.get(fileStorageProperties.getProductUploadPath() + File.separator + filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            Register register = RegisterMapper.mapDtoToEntity(registerDTO);

            if (register.getId() != null) {
                register = registerRepository.findById(register.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Register not found with id: " ));
            } else {
                throw new IllegalStateException("Register must be saved before being associated with FileUpload.");
            }
            FileUpload fileUpload = FileUpload.builder()
                    .fileName(filename)
                    .filePath(filePath.toString())
                    .register(register)
                    .build();
            fileUploadRepository.save(fileUpload);
            return FileMapper.mapEntityToDto(fileUpload);
        }
        return null;
    }

    @Override
    @Transactional
    public List<FileUploadDTO> saveFiles(List<MultipartFile> files, RegisterDTO registerDTO,String loginId) throws IOException {
        List<FileUploadDTO> savedFileDTOs = new ArrayList<>();
        for (MultipartFile file : files) {
            FileUploadDTO fileUploadDTO = saveFile(file, registerDTO,loginId);
            if (fileUploadDTO != null) {
                savedFileDTOs.add(fileUploadDTO);
            }
        }
        return savedFileDTOs;
    }

    @Override
    public List<FileUploadDTO> findAllByRegisterId(Long registerId) {

        List<FileUpload> entities = fileUploadRepository.findAllByRegisterId(registerId);
        if (entities != null && !entities.isEmpty()) {
            return entities.stream().map(FileMapper::mapEntityToDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
