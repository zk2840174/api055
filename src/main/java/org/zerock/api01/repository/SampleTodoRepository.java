package org.zerock.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api01.entity.SampleTodo;

public interface SampleTodoRepository extends JpaRepository<SampleTodo, Long> {
}
