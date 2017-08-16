package model.entities.taxes;

import model.entities.incomes.Income;

/**
 * Created by troll on 17.08.2017.
 */
public class TaxBuilder {

    private int id = 0 ;
    double taxPercent = 0.;
    private String name = "";

    public TaxBuilder setId(int id){
        this.id = id;
        return this;
    }

    public TaxBuilder setTaxPercent(double taxPercent){
        this.taxPercent = taxPercent;
        return this;
    }

    public TaxBuilder setName(String name){
        this.name = name;
        return this;
    }

    public Tax createTax(){
        //TODO доделать
        return new Tax(new Income(), 0.);
    }
}
