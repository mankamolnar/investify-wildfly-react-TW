package hu.standapp.investify.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by flowerpower on 2017. 06. 22..
 */
@Entity
public class MoneyPool {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private int payedIn;
    private int goal;
    private int shareholdPrice;

    public MoneyPool() {}

    public MoneyPool(int payedIn, int goal, int shareholdPrice) {
        this.payedIn = payedIn;
        this.goal = goal;
        this.shareholdPrice = shareholdPrice;
    }

    public int getPayedIn() {
        return payedIn;
    }

    public void setPayedIn(int payedIn) {
        this.payedIn = payedIn;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getShareholdPrice() {
        return shareholdPrice;
    }

    public void setShareholdPrice(int shareholdPrice) {
        this.shareholdPrice = shareholdPrice;
    }
}