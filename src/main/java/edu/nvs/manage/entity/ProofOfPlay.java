package edu.nvs.manage.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("sshow.proof_of_play")
@Data
@ToString
@Builder
public class ProofOfPlay {
    @Id
    private Long id;
    private Long imageId;
    private Long slideshowId;
    @CreatedDate
    private Timestamp playTime;
 }