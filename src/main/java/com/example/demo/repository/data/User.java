package com.example.demo.repository.data;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table("users")
@Profile("cassandra")
public class User {

  @PrimaryKeyColumn(name = "userid", type = PrimaryKeyType.PARTITIONED)
  @Id
  private String userId;
  @Column("first_name")
  private String firstName;
  @Column("last_name")
  private String lastName;
  @Column
  private Set<String> emails;
  @Column("top_scores")
  private List<Integer> scores;
  @Column
  private Map<Date, String> todo;

}
