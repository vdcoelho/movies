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
    private String yearOfNominated;
    private String category;
    private String nominated;
    private String additionalInfo;
    private Boolean won;

}
