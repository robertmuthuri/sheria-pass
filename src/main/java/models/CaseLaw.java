package models;

import java.util.Objects;

public class CaseLaw {
    private  int id;
    private String case_citation;
    private String case_summary;
    private String case_court;
    private String case_holding;
    private String case_url;

    public CaseLaw(String case_citation, String case_summary, String case_court, String case_holding, String case_url) {
        this.case_citation = case_citation;
        this.case_summary = case_summary;
        this.case_court = case_court;
        this.case_holding = case_holding;
        this.case_url = case_url;
    }
    // getter methods
    public int getId() { return id; }
    public String getCase_citation() { return case_citation; }
    public String getCase_court() { return case_court; }
    public String getCase_holding() { return case_holding; }
    public String getCase_summary() { return case_summary; }
    public String getCase_url() { return case_url; }

    //setter methods
    public void setId(int id) {this.id = id; }
    public void setCase_citation(String case_citation) {this.case_citation = case_citation; }
    public void setCase_court(String case_court) {this.case_court = case_court; }
    public void setCase_holding(String case_holding) {this.case_holding = case_holding; }
    public void setCase_summary(String case_summary) {this.case_summary = case_summary; }
    public void setCase_url(String case_url) {this.case_url = case_url; }
    //override equals and hashcode method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseLaw caseLaw = (CaseLaw) o;
        return getId() == caseLaw.getId() &&
                Objects.equals(getCase_citation(), caseLaw.getCase_citation()) &&
                Objects.equals(getCase_summary(), caseLaw.getCase_summary()) &&
                Objects.equals(getCase_court(), caseLaw.getCase_court()) &&
                Objects.equals(getCase_holding(), caseLaw.getCase_holding()) &&
                Objects.equals(getCase_url(), caseLaw.getCase_url());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCase_citation(), getCase_summary(), getCase_court(), getCase_holding(), getCase_url());
    }
}
