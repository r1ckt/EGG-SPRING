package edu.egg.spring.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Publisher")
@Getter
@Setter
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "publisher_id")
    private Long id;
    @Column(name = "publisher_name")
    private String name;
    @Column(name = "publisher_status")
    private Boolean status;

}
