package com.HotUdon.mapper;

import com.HotUdon.dto.FileUploadDTO;
import com.HotUdon.model.FileUpload;

import java.util.ArrayList;
import java.util.List;

public class FileMapper {

    public static FileUploadDTO mapEntityToDto(FileUpload fileUpload){
        if(fileUpload == null){
            return  null;
        }
        FileUploadDTO fileUploadDTO = new FileUploadDTO();
        fileUploadDTO.setFileName(fileUpload.getFileName());
        fileUploadDTO.setRegisterDTO(RegisterMapper.mapEntityToDTO(fileUpload.getRegister()));
        fileUploadDTO.setFilePath(fileUpload.getFilePath());
        return fileUploadDTO;
    }
    public static FileUpload mapDtoToEntity(FileUploadDTO fileUploadDTO){
        if(fileUploadDTO == null){
          return  null;
        }
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileUploadDTO.getFileName());
        fileUpload.setFilePath(fileUploadDTO.getFilePath());
        fileUpload.setRegister(RegisterMapper.mapDtoToEntity(fileUploadDTO.getRegisterDTO()));
        return fileUpload;
    }

    public static List<FileUploadDTO> mapEntityToDtoList(List<FileUpload> fileUploads){
        if(fileUploads == null){
            return  null;
        }
        List<FileUploadDTO> fileUploadList = new ArrayList<>();
        for(FileUpload fileUpload : fileUploads){
            FileUploadDTO fileUploadDTO = mapEntityToDto(fileUpload);
            fileUploadList.add(fileUploadDTO);
        }
        return fileUploadList;
    }
    public static List<FileUpload> mapDtoToEntityList(List<FileUploadDTO> fileUploadDTOs){
        if(fileUploadDTOs == null){
            return  null;
        }
        List<FileUpload> fileUploadList = new ArrayList<>();
        for(FileUploadDTO fileUploadDTO : fileUploadDTOs){
            FileUpload fileUpload = mapDtoToEntity(fileUploadDTO);
            fileUploadList.add(fileUpload);
        }
        return  fileUploadList;
    }
}
