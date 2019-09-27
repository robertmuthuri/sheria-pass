package models;

public class Petitioner extends Party {

    public static final String DATABASE_TYPE = "petitioner";

    public Petitioner(String name) {
        super(name);
//        this.name=name;
        type= DATABASE_TYPE;
    }
}
