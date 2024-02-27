package com.example.employeeportal.manager;

import java.util.List;
import java.util.Optional;

public interface GenericManager<T, ID> {

    T save(T t);

    T get(ID var1);

    List<T> getAll();

    List<T> saveAll(List<T> list);

    List<T> get(List<ID> list);

    boolean delete(T t);

    boolean delete(List<T> list);

    Optional<T> findById(ID var1);

}
