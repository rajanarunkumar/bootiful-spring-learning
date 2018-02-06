package com.example.demo.repository.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table("users")
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
