package com.example.demo.activerecord;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticEntityManagerProvider {

  private final EntityManagerProvider provider;

  private static EntityManager entityManager;

  @Autowired
  public StaticEntityManagerProvider(EntityManagerProvider provider) {
    this.provider = provider;
  }

  @PostConstruct
  private void init() {
    entityManager = provider.getEntityManager();
  }

  public static EntityManager getEntityManager() {
    return entityManager;
  }
}
