package models;

import java.util.Objects;

public  abstract class Party {
    public String name;
    public String type;
    public int id;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return getName().equals(party.getName()) &&
                getType().equals(party.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
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
}
