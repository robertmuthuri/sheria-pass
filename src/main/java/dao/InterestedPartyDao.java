package dao;

import models.CaseLaw;
import models.InterestedParty;

import java.util.List;

public interface InterestedPartyDao {
    void add(InterestedParty interestedParty);
    void addPetitionerToCase(InterestedParty interestedParty, CaseLaw caseLaw);

    //read
    List<InterestedParty> getAllInterestedParties();
    InterestedParty findById(int id);
    List<CaseLaw> getCaseByPetitionerId();

    //delete
    void deleteById(int id);
    void clearAll();
}