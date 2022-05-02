package edu.egg.spring.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "author_id")
    private Long id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "author_status")
    private Boolean status;

}
