package com.example.service;

import com.example.model.Painting;

import java.util.*;

public class PaintingService implements IPaintingService {
    private static final Map<Integer, Painting> paintings = new HashMap<>();

    static {
        paintings.put(1, new Painting(1, "mt1", 5.5, 3.5, "gỗ", "new", 500000));
        paintings.put(2, new Painting(2, "mt1", 5.5, 3.5, "gỗ", "new", 500000));
        paintings.put(3, new Painting(3, "mt1", 5.5, 3.5, "gỗ", "new", 500000));
        paintings.put(4, new Painting(4, "mt1", 5.5, 3.5, "gỗ", "new", 500000));
        paintings.put(5, new Painting(5, "mt1", 5.5, 3.5, "gỗ", "new", 500000));
    }

    @Override
    public List<Painting> showAll() {
        return new ArrayList<>(paintings.values());
    }

    @Override
    public void save(Painting painting) {
        paintings.put(painting.getId(), painting);
    }

    @Override
    public Painting findById(int id) {
        return paintings.get(id);
    }

    @Override
    public void update(int id, Painting painting) {
        paintings.replace(id, painting);
    }

    @Override
    public void delete(int id) {
        paintings.remove(id);
    }

    @Override
    public List<Painting> search(String name) {
        List<Painting> paintingList = new ArrayList<>();
        String regex = ".*" + name.toLowerCase().trim() + ".*";
        Set<Map.Entry<Integer, Painting>> entrySet = paintings.entrySet();
        for (Map.Entry<Integer, Painting> e : entrySet
        ) {
            if (e.getValue().getCode().toLowerCase().matches(regex)) {
                paintingList.add(e.getValue());
            }
        }
        return paintingList;
    }
}
