package org.zerock.api01.service;

import org.zerock.api01.dto.AttachFileDTO;

import java.util.List;

public interface AttachFileService {

    
    void saveUploadFileInfo(List<AttachFileDTO> attachFileDTOList);

    void removeFileInfo(AttachFileDTO attachFileDTO);
}
