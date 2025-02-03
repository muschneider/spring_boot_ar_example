package com.example.demo.activerecord;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerProvider {

  @PersistenceContext private EntityManager entityManager;

  public EntityManager getEntityManager() {
    return entityManager;
  }
}
