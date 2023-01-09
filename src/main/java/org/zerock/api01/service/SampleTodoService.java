package org.zerock.api01.service;


import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.SampleTodoDTO;



@Transactional
public interface SampleTodoService {


    Long register(SampleTodoDTO sampleTodoDTO);

    PageResponseDTO<SampleTodoDTO> getList(PageRequestDTO pageRequestDTO);

    void modify(SampleTodoDTO sampleTodoDTO);

    void remove(Long id);

    SampleTodoDTO get(Long id);

}
