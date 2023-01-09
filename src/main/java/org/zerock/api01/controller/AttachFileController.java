package org.zerock.api01.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.dto.AttachFileDTO;
import org.zerock.api01.service.AttachFileService;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class AttachFileController {

    @Value("${org.zerock.upload.path}")// import 시에 springframework으로 시작하는 Value
    private String uploadPath;

    private final AttachFileService attachFileService;


    @PostMapping("/upload")
    public List<AttachFileDTO> uploadFiles(MultipartFile[] files) {

        log.info("----------------------------------------");

        log.info(files);

        if(files != null && files.length > 0){

            List<AttachFileDTO> attachFileDTOS = new ArrayList<>();

            for(MultipartFile file: files){

                String uuid = UUID.randomUUID().toString();
                String fileName = file.getOriginalFilename();

                String saveFileName = uuid+"_"+fileName;

                File saveFile = new File(uploadPath, saveFileName);

                try(FileOutputStream fos = new FileOutputStream(saveFile);
                    InputStream fin = file.getInputStream();
                ){

                    FileCopyUtils.copy(fin, fos);

                    attachFileDTOS.add(AttachFileDTO.builder()
                                    .uuid(uuid)
                                    .fileName(fileName)
                            .build());

                }catch(Exception e){

                    log.error(e);
                }//end catch
            }//end for

            attachFileService.saveUploadFileInfo(attachFileDTOS);

            return attachFileDTOS;
        }

        return null;

    }

    @DeleteMapping("/delete")
    public Map<String, String> deleteFile( @RequestBody  AttachFileDTO fileDTO){


        String fileName = fileDTO.getUuid()+"_" + fileDTO.getFileName();

        attachFileService.removeFileInfo(fileDTO);

        File targetFile = new File(uploadPath, fileName);


        targetFile.delete();

        return Map.of("RESULT", "success");

    }



    @GetMapping("/view/{file}")
    public ResponseEntity<Resource> getImageDynamicType(@PathVariable("file") String fileName) {

        log.info("fileName: " + fileName);

        File file = new File(uploadPath, fileName);


        try {
            String contentType = Files.probeContentType(file.toPath());
            Resource resource =  new InputStreamResource(new FileInputStream(file));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Content-Type", contentType);

            return ResponseEntity.ok().headers(responseHeaders).body(resource);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }

    }
}
