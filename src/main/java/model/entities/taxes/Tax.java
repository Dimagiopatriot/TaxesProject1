package model.entities.taxes;

/**
 * Created by troll on 13.08.2017.
 */
public class Tax {

    private int id;
    private double taxPercent;
    private String name;

    public Tax(){}

    public Tax(double taxPercent){
        this.taxPercent = taxPercent;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
