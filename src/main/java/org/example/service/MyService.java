package org.example.service;

import java.sql.SQLException;

public interface MyService<T> {
    T save(T t) throws SQLException;

    T findById(Long id) throws ClassNotFoundException;

    boolean deleteById(Long id);

    T update(T t) throws ClassNotFoundException;
}
