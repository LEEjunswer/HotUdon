package com.HotUdon.mapper;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.model.FileUpload;
import com.HotUdon.model.Register;

import java.util.ArrayList;
import java.util.List;

public class FileMapper {
    public static FileUploadDTO mapEntityToDto(FileUpload fileUpload) {
        if (fileUpload == null) {
            return null;
        }
        FileUploadDTO dto = new FileUploadDTO();
        dto.setId(fileUpload.getId());
        dto.setFileName(fileUpload.getFileName());
        dto.setFilePath(fileUpload.getFilePath());
        dto.setRegisterId(fileUpload.getRegister().getId());
        return dto;
    }

    public static FileUpload mapDtoToEntity(FileUploadDTO fileUploadDTO, Register register) {
        if (fileUploadDTO == null) {
            return null;
        }
        FileUpload fileUpload = new FileUpload();
        fileUpload.setId(fileUploadDTO.getId());
        fileUpload.setFileName(fileUploadDTO.getFileName());
        fileUpload.setFilePath(fileUploadDTO.getFilePath());
        fileUpload.setRegister(register); // Set the register directly to avoid recursion
        return fileUpload;
    }

    public static List<FileUploadDTO> mapEntityToDtoList(List<FileUpload> fileUploads) {
        if (fileUploads == null) {
            return null;
        }
        List<FileUploadDTO> fileUploadList = new ArrayList<>();
        for (FileUpload fileUpload : fileUploads) {
            FileUploadDTO fileUploadDTO = mapEntityToDto(fileUpload);
            fileUploadList.add(fileUploadDTO);
        }
        return fileUploadList;
    }

    public static List<FileUpload> mapDtoToEntityList(List<FileUploadDTO> fileUploadDTOs, Register register) {
        if (fileUploadDTOs == null) {
            return null;
        }
        List<FileUpload> fileUploadList = new ArrayList<>();
        for (FileUploadDTO fileUploadDTO : fileUploadDTOs) {
            FileUpload fileUpload = mapDtoToEntity(fileUploadDTO, register);
            fileUploadList.add(fileUpload);
        }
        return fileUploadList;
    }
}