package com.example.springcodeapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
    //@SequenceGenerator(
    //        name = "seq_number_id",
    //        allocationSize = 1
    //)
@Table(name = "demos")
public class Demo {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name="sequence_id", columnDefinition="serial")
    @Generated(GenerationTime.INSERT)
    private Long seqId;

    private String name;
}
