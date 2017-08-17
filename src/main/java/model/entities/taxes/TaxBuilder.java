package model.entities.taxes;

/**
 * Created by troll on 17.08.2017.
 */
public class TaxBuilder {

    private int id = 0 ;
    private double taxPercent = 0.;
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
        Tax tax = new Tax(taxPercent);
        tax.setId(id);
        tax.setName(name);

        return tax;
    }
}
