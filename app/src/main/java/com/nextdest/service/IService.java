package com.nextdest.service;

import java.util.List;

public interface IService<T>{

    int save(T object);
    T load(int id);
    List<T> getAll();
}
