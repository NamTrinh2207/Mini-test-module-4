package com.example.service;

import com.example.model.Painting;

import java.util.List;

public interface IPaintingService {
    List<Painting> showAll();

    void save(Painting painting);

    Painting findById(int id);

    void update(int id, Painting painting);

    void delete(int id);

    List<Painting> search(String name);
}
