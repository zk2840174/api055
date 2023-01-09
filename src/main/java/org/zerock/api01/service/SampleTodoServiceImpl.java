package org.zerock.api01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.SampleTodoDTO;
import org.zerock.api01.entity.SampleTodo;
import org.zerock.api01.repository.SampleTodoRepository;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class SampleTodoServiceImpl implements SampleTodoService{

    private final ModelMapper modelMapper;
    private final SampleTodoRepository repository;

    @Override
    public Long register(SampleTodoDTO sampleTodoDTO) {

        SampleTodo sampleTodo = modelMapper.map(sampleTodoDTO, SampleTodo.class);

        log.info("register......................");
        log.info(sampleTodo);

        repository.save(sampleTodo);

        Long id = sampleTodo.getId();

        return id;
    }

    @Override
    public PageResponseDTO<SampleTodoDTO> getList(PageRequestDTO pageRequestDTO) {

        int page = pageRequestDTO.getPage() - 1;
        int size = pageRequestDTO.getSize();

        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());

        Page<SampleTodo> result = repository.findAll(pageable);

        PageResponseDTO<SampleTodoDTO> pageResult =
                new PageResponseDTO(pageRequestDTO,
                        result.get().map(entity -> modelMapper.map(entity,SampleTodoDTO.class)).collect(Collectors.toList()),
                        (int)result.getTotalElements());

        return pageResult;
    }

    @Override
    public void modify(SampleTodoDTO sampleTodoDTO) {

        Optional<SampleTodo> result = repository.findById(sampleTodoDTO.getId());

        SampleTodo sampleTodo = result.orElseThrow();

        sampleTodo.changeTitle(sampleTodoDTO.getTitle());
        sampleTodo.changeDueDate(sampleTodoDTO.getDueDate());
        sampleTodo.changeComplete(sampleTodoDTO.isComplete());

        repository.save(sampleTodo);

    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public SampleTodoDTO get(Long id) {

        Optional<SampleTodo> result = repository.findById(id);

        SampleTodo sampleTodo = result.orElseThrow();

        return modelMapper.map(sampleTodo, SampleTodoDTO.class);
    }
}













