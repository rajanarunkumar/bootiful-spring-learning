package com.example.demo.repository.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Embeddable
public class Phone implements Serializable {

  private Long number;
  @Enumerated(value = EnumType.ORDINAL)
  private ContactType contactType;
}
