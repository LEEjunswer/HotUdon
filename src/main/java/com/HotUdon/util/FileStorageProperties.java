package com.HotUdon.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileStorageProperties {

    @Value("${profile.upload.path}")
    private String profileUploadPath;

    @Value("${upload.path}")
    private String productUploadPath;
}
