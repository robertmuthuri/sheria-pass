package dao;

import models.CaseLaw;
import models.Petitioner;

import java.util.List;

public interface PetitionerDao {
    void add(Petitioner petitioner);
    void addPetitionerToCase(Petitioner petitioner, CaseLaw caseLaw);

    //read
    List<Petitioner> getAllPetioners();
    Petitioner findById(int id);
    List<CaseLaw> getCaseByPetitionerId();

    //delete
    void deleteById(int id);
    void clearAll();
}
