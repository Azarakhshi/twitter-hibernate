package base.service;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService <E extends BaseEntity> {
    E save(E e);

    E update(E e);

    void delete(E e);

    Optional<E> findById(Long id);

    List<E> findAll();

    boolean isExistsById(Long id);

    Long countAll();
}
