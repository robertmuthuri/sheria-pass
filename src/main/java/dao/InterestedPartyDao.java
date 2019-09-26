package dao;

import models.CaseLaw;
import models.InterestedParty;
import models.Party;

import java.util.List;

public interface InterestedPartyDao {
    void add(InterestedParty interestedParty);
    void addInterestedPartyToCaselaw(InterestedParty interestedParty, CaseLaw caseLaw);

    //read
    List<InterestedParty> getAllInterestedParties();
    InterestedParty findById(int id);
    List<CaseLaw> getCaseLawsForInterestedParty(int party_id);

    //delete
    void deleteById(int id);
    void clearAll();
}
