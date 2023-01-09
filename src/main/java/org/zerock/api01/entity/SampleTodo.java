package org.zerock.api01.entity;


import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="tbl_sample_todo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

public class SampleTodo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="sid")
    private Long id;

    private String title;

    private String writer;

    private LocalDate dueDate;

    private boolean complete;


    public void changeTitle(String title){
        this.title = title;
    }

    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public void changeComplete(boolean complete){
        this.complete = complete;
    }

}
