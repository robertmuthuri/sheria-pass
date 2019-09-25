package models;

public class Respondent extends Party {
    public static final String DATABASE_TYPE = "respondent";
    public Respondent(String name) {
        this.name= name;
        type = DATABASE_TYPE;
    }


}
