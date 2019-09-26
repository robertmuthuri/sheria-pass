package dao;

import models.Law;

import java.util.List;

public interface LawDao {

    //create
    void add(Law law);

    //read
    List<Law> getAllLaws();
    Law findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
