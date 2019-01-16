package com.nextdest.service;

import java.text.ParseException;
import java.util.List;

public interface IService<T>{

    int save(T object);
    T load(int id) throws ParseException;
    List<T> getAll();
}
