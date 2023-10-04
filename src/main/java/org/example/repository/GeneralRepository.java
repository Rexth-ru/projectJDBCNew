package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface GeneralRepository<T,K>{
    T findById(K id);

    boolean deleteById(K id);

    List<T> findAll();

    T save(T t) throws SQLException;
    T update(T t);
}
