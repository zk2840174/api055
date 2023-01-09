package org.zerock.api01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.api01.dto.AttachFileDTO;
import org.zerock.api01.entity.AttachFile;
import org.zerock.api01.repository.AttachFileRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class AttachFileServiceImpl implements AttachFileService{


    private final AttachFileRepository attachFileRepository;

    private final ModelMapper modelMapper;

    @Override
    public void saveUploadFileInfo(List<AttachFileDTO> attachFileDTOList) {



        attachFileDTOList.forEach(attachFileDTO ->  {

            AttachFile attachFile = modelMapper.map(attachFileDTO, AttachFile.class);

            attachFileRepository.save(attachFile);

        });//foreach

    }

    @Override
    public void removeFileInfo(AttachFileDTO attachFileDTO) {

        String uuid = attachFileDTO.getUuid();

        log.info("uuid: " + uuid);

        attachFileRepository.deleteById(uuid);


    }
}
