package org.zerock.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api01.entity.AttachFile;

public interface AttachFileRepository  extends JpaRepository<AttachFile, String> {
}
