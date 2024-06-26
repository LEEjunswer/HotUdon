package com.HotUdon.dto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileUploadDTO {
    private Long id;

    private String fileName;

    private String filePath;


    private RegisterDTO registerDTO;
}
