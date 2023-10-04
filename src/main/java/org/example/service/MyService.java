package org.example.service;

import org.example.model.Clinic;
import org.example.model.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface MyService<T> {
    T save(T t) throws SQLException;

    T findById(Long id);

    boolean deleteById(Long id);

    T update(T t);

    List<T> findAll();


}
