package models;

public class InterestedParty extends Party {

    public static final String DATABASE_TYPE = "interested party";

    public InterestedParty(String name) {
        this.name=name;
        type= DATABASE_TYPE;
    }
}
