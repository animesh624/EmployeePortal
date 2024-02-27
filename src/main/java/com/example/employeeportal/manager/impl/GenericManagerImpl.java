package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.GenericManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class GenericManagerImpl<T, ID> implements GenericManager<T, ID> {

    protected JpaRepository<T, ID> repository;

    public GenericManagerImpl() {

    }

    public GenericManagerImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public T get(ID var1) {
        return repository.getOne(var1);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public List<T> saveAll(List<T> list) {
        List<T> retList = new ArrayList<>();
        for(T t : list) {
            retList.add(save(t));
        }
        return retList;
    }

    @Override
    public List<T> get(List<ID> list) {
        List<T> retList = new ArrayList<>();
        for(ID ele : list) {
            retList.add(repository.getOne(ele));
        }
        return retList;
    }

    @Override
    public boolean delete(T t) {
        try {
            this.repository.delete(t);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(List<T> list) {
        try {
            this.repository.deleteAll(list);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Optional<T> findById(ID var1){
        return this.repository.findById(var1);
    }
}
