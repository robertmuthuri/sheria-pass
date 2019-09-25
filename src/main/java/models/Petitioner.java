package models;

public class Petitioner extends Party {

    public static final String DATABASE_TYPE = "petitioner";

    public Petitioner(String name) {
        this.name=name;
        type= DATABASE_TYPE;
    }
}
