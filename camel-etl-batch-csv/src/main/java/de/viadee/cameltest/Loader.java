package de.viadee.cameltest;

import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Loader<T> {

    private JpaRepository jpaRepo;

    public Loader(JpaRepository repo) {
        this.jpaRepo = repo;
    }

    public T load(T entity) {
        return (T) jpaRepo.saveAndFlush(entity);
    }

}
