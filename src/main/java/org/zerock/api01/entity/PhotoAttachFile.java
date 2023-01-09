package org.zerock.api01.entity;


import lombok.*;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Embeddable
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhotoAttachFile {

    @Id
    private String uuid;

    private String fileName;

    private int fileIdx;
}
