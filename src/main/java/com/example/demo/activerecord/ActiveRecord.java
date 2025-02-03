package com.example.demo.activerecord;

import jakarta.persistence.EntityManager;
import java.lang.reflect.*;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class ActiveRecord<T> {

  private final Class<T> entityClass;
  private final EntityManager entityManager;

  protected EntityManager getEntityManager() {
    return this.entityManager;
  }

  public ActiveRecord() {
    this.entityManager = StaticEntityManagerProvider.getEntityManager();
    this.entityClass =
        (Class<T>)
            ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public T save() {
    entityManager.persist(this);
    return (T) this;
  }

  public T update() {
    return entityManager.merge((T) this);
  }

  public void delete() {
    entityManager.remove(entityManager.contains(this) ? this : entityManager.merge(this));
  }

  public T findById(Object id) {
    return entityManager.find(entityClass, id);
  }

  public List<T> findAll() {
    return entityManager
        .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
        .getResultList();
  }

  public static <T> T findById(Class<T> entityClass, Object id) {
    EntityManager entityManager = StaticEntityManagerProvider.getEntityManager();
    return entityManager.find(entityClass, id);
  }

  public static <T> List<T> findAll(Class<T> entityClass) {
    EntityManager entityManager = StaticEntityManagerProvider.getEntityManager();
    return entityManager
        .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
        .getResultList();
  }
}
