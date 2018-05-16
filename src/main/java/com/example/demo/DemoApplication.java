package com.example.demo;

import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EntityScan(basePackages = {"com.example.demo.repository.jpa", "com.example.demo.repository.data",
    "com.example.demo.repository.cassandra"})
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }


  /**
   * Creates transactional manager for relational database operation
   *
   * @return PlatformTransactionManager of type {@link HibernateTransactionManager}
   */
  @Bean("transactionManager")
  public PlatformTransactionManager relationalTransactionManager(
      EntityManagerFactory entityManagerFactory) {
    HibernateTransactionManager manager = new HibernateTransactionManager(
        entityManagerFactory.unwrap(SessionFactory.class));
    manager.setAllowResultAccessAfterCompletion(true);
    return manager;
  }

}
