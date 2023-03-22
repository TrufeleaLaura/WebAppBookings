package com.projectFortech.ProjectFortech.domain;

import javax.persistence.*;

@Entity
@Table(name="offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer offerId;

    @Column(name="discount")
    private int percents;

    @Column(name="description")
    private String whatfor;

    public Offer(int percents, String whatfor) {
        this.percents = percents;
        this.whatfor = whatfor;
    }

    public Offer() {

    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    public String getWhatfor() {
        return whatfor;
    }

    public void setWhatfor(String whatfor) {
        this.whatfor = whatfor;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", percents=" + percents +
                ", whatfor='" + whatfor + '\'' +
                '}';
    }
}
