package org.zerock.api01.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.SampleTodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class SampleTodoServiceTests {

    @Autowired
    private SampleTodoService service;

    @Test
    public void testRegister(){

        SampleTodoDTO sampleTodoDTO = SampleTodoDTO.builder()
                .title("샘플 타이틀")
                .writer("user00")
                .dueDate(LocalDate.of(2022,12,30))
                .build();

        Long id = service.register(sampleTodoDTO);

        log.info("ID: " + id);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build(); //1 page, 10

        PageResponseDTO<SampleTodoDTO> response = service.getList(pageRequestDTO);

        log.info(response);

    }
}
