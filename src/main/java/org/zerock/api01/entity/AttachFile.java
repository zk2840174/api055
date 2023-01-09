package org.zerock.api01.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;



@Entity
@Getter
@ToString
public class AttachFile {

    @Id
    private String uuid;

    private String fileName;



}
