package base.service.impl;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import base.service.BaseService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl <E extends BaseEntity,R extends BaseRepository<E>>
        implements BaseService<E> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.save(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public E update(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.update(e);
        repository.getEntityManager().getTransaction().commit();
        return e;
    }

    @Override
    public void delete(E e) {
        repository.getEntityManager().getTransaction().begin();
        repository.delete(e);
        repository.getEntityManager().getTransaction().commit();
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean isExistsById(Long id) {
        return repository.isExistsById(id);
    }

    @Override
    public Long countAll() {
        return repository.countAll();
    }
}