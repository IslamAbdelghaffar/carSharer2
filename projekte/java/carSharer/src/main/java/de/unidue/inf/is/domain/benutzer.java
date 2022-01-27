package de.unidue.inf.is.domain;

public class benutzer {

    /**
     * Implementation of the Singleton pattern.
     *
     * @return
     */


    int bid;
    String name;
    String email;

    public benutzer(int bid, String name, String email) {
        this.bid = bid;
        this.name = name;
        this.email = email;
    }

    public benutzer(int bid,String email) {
        this.bid=bid;
        this.email = email;
    }

    public benutzer(String email) {
        this.email = email;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void hatFahrerlaubnis(){}
    public void schreiben(){}
    public void erstellen(){}
    public void reservieren(){}


}
