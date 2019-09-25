package models;

import java.util.Objects;

public class Party {
    private  String name;
    private String type;
    private  int id;

    public Party(String name, String type, int id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return id == party.id &&
                Objects.equals(name, party.name) &&
                Objects.equals(type, party.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, id);
    }
}
