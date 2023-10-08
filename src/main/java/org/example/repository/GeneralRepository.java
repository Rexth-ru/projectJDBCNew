package org.example.repository;

import java.sql.SQLException;

public interface GeneralRepository<T, K> {
    T findById(K id) throws ClassNotFoundException;

    boolean deleteById(K id);

    T save(T t) throws SQLException;

    T update(T t) throws ClassNotFoundException;
}
