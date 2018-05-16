package com.example.demo.repository.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CONTACTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Contact implements Serializable {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @Embedded
  private Phone contact;

  @ManyToOne
  private Employee employee;
}
