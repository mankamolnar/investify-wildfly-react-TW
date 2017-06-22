package hu.standapp.investify.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gombaspeteer on 6/22/17.
 */

@Entity
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Sharehold shareholdId;
    private int shareholdForSale;
    private int price;
    private Date startDate;
    private Date endDate;
    private boolean active;

    public Market (){}

    public Market(Sharehold shareholdId, int shareholdForSale, int price, Date startDate, Date endDate){
        this.shareholdId = shareholdId;
        this.shareholdForSale = shareholdForSale;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    public int getShareholdForSale() {
        return shareholdForSale;
    }

    public void setShareholdForSale(int shareholdForSale) {
        this.shareholdForSale = shareholdForSale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}