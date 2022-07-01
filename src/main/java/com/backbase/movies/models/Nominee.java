package com.backbase.movies.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_nominees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nominee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNominee;
    @Column(length = 15)
    private String yearOfNominated;
    @Column(length = 150)
    private String category;
    @Column(length = 1_000)
    private String nominated;
    @Column(length = 1_000)
    private String additionalInfo;
    private Boolean won;

}
