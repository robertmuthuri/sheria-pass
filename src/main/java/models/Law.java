package models;

import java.util.Objects;

public class Law {
 private  String law_citation;
 private String law_summary;
 private String law_url;
 private int id;

    public Law(String law_citation,String law_summary, String law_url) {
        this.law_citation = law_citation;
        this.law_summary= law_summary;
        this.law_url = law_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Law law = (Law) o;
        return getId() == law.getId() &&
                Objects.equals(getLaw_citation(), law.getLaw_citation()) &&
                Objects.equals(law_summary, law.law_summary) &&
                Objects.equals(getLaw_url(), law.getLaw_url());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLaw_citation(), law_summary, getLaw_url(), getId());
    }

    public String getLaw_summary() {
        return law_summary;
    }

    public void setLaw_summary(String law_summary) {
        this.law_summary = law_summary;
    }

    public String getLaw_citation() {
        return law_citation;
    }

    public void setLaw_citation(String law_citation) {
        this.law_citation = law_citation;
    }

    public String getLaw_url() {
        return law_url;
    }

    public void setLaw_url(String law_url) {
        this.law_url = law_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
