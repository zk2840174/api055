package org.zerock.api01.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.api01.entity.SampleTodo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class SampleTodoRepositoryTests {

    @Autowired
    private SampleTodoRepository repository;



    @Test
    public void testInserts() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            SampleTodo sampleTodo = SampleTodo.builder()
                    .title("Title.."+i)
                    .writer("user"+ (i%10))
                    .dueDate(LocalDate.of(2022,12,25))
                    .complete(false)
                    .build();

            repository.save(sampleTodo);

        });

    }

    @Test
    public void testRead() {

        Long sid = 100L;

        Optional<SampleTodo> result = repository.findById(sid);

        SampleTodo sampleTodo = result.orElseThrow();

        log.info(sampleTodo);

    }

    @Test
    public void testUpadte() {

        Long sid = 100L;

        Optional<SampleTodo> result = repository.findById(sid);

        SampleTodo sampleTodo = result.orElseThrow();
        sampleTodo.changeTitle("Updated Title..100");
        sampleTodo.changeDueDate(LocalDate.now());
        sampleTodo.changeComplete(true);

        repository.save(sampleTodo);
    }

    @Test
    public void testDelete() {

        Long sid = 1L;

        repository.deleteById(sid);

    }

    @Test
    public void testPaging() {

        //시작번호가 0부터 시작
        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());

        Page<SampleTodo> result = repository.findAll(pageable);


        log.info("TOTAL: "+result.getTotalElements());

        List<SampleTodo> list = result.getContent();

        list.forEach( sampleTodo -> log.info(sampleTodo));

    }

}
