package org.zerock.api01.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SampleTodoDTO {

    private Long id;

    private String title;

    private String writer;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dueDate;

    private boolean complete;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate, modDate;
}
