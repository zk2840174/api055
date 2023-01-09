package org.zerock.api01.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.SampleTodoDTO;
import org.zerock.api01.service.SampleTodoService;

import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/sample/todos")
@RequiredArgsConstructor
@CrossOrigin
public class SampleTodoController {

    private final SampleTodoService sampleTodoService;

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody SampleTodoDTO sampleTodoDTO){

        log.info("register..............."  + sampleTodoDTO);

        Long id = sampleTodoService.register(sampleTodoDTO);

        return Map.of("NEW", id);

    }


    @GetMapping("/list")
    public PageResponseDTO<SampleTodoDTO> getList(PageRequestDTO pageRequestDTO){

        log.info("getList..............." + pageRequestDTO);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return sampleTodoService.getList(pageRequestDTO);

    }


    @GetMapping("/{id}")
    public SampleTodoDTO get(@PathVariable("id") Long id){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return sampleTodoService.get(id);
    }




    @PutMapping("/{id}")
    public Map<String, String> modify( @PathVariable("id") Long id, @RequestBody SampleTodoDTO sampleTodoDTO){

        log.info("modify................" + id);
        log.info(sampleTodoDTO);
        sampleTodoDTO.setId(id);//fix id

        sampleTodoService.modify(sampleTodoDTO);

        return Map.of("Modified", "SUCCESS");

    }

    @DeleteMapping("/{id}")
    public Map<String, String> remove( @PathVariable("id") Long id) {


        log.info("remove.............." + id);

        sampleTodoService.remove(id);

        return Map.of("Removed", "SUCCESS");

    }




}
