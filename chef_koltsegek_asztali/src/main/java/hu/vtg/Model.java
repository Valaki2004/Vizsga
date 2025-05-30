package hu.vtg;

import java.time.LocalDate;

public class Model {
    public Integer id;
    public String chefname;
    public LocalDate datum;
    public String type;
    public Integer currency;
    public String comment;
    public Model(Integer id, String chefname, LocalDate datum, String type, Integer currency, String comment) {
        this.id = id;
        this.chefname = chefname;
        this.datum = datum;
        this.type = type;
        this.currency = currency;
        this.comment = comment;
    }
    public Model() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getChefname() {
        return chefname;
    }
    public void setChefname(String chefname) {
        this.chefname = chefname;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getCurrency() {
        return currency;
    }
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
