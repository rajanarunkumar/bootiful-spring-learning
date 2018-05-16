package com.example.demo.config;

import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories(basePackages = {"com.example.demo.repository.cassandra"})
@Profile("cassandra")
@Slf4j
public class DemoCassandraConfig extends AbstractCassandraConfiguration {

  @Autowired
  private CassandraProperties cassandraProperties;

  @Bean
  public CassandraMappingContext cassandraMapping() {
    return new CassandraMappingContext();
  }

  @Override
  protected String getKeyspaceName() {
    return cassandraProperties.getKeyspaceName();
  }

  @Override
  protected List<String> getStartupScripts() {
    return Arrays.asList("CREATE KEYSPACE IF NOT EXISTS " + getKeyspaceName()
        + " WITH durable_writes = true "
        + " AND replication = { 'replication_factor' : 1, 'class' : 'SimpleStrategy' };");
  }

  @Override
  public CassandraClusterFactoryBean cluster() {
    CassandraClusterFactoryBean factoryBean = new CassandraClusterFactoryBean();
    factoryBean.setContactPoints(String.join(",", cassandraProperties.getContactPoints()));
    factoryBean.setPort(cassandraProperties.getPort());
    factoryBean.setClusterName(cassandraProperties.getClusterName());
    factoryBean.setKeyspaceCreations(getKeyspaceCreations());
    return factoryBean;
  }

  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    return Arrays.asList(CreateKeyspaceSpecification
        .createKeyspace(getKeyspaceName()).ifNotExists()
        .with(KeyspaceOption.DURABLE_WRITES, true));
  }
}
