package controller.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ResultCalculatingPresenter implements Comparator<String>{

    Map<String, Double> resultTaxMap = new HashMap<>();

    public void addResultItem(String viewNameOfTax, double taxNeedToPay){
        resultTaxMap.put(viewNameOfTax, taxNeedToPay);
    }

    @Override
    public int compare(String o1, String o2) {
        if (resultTaxMap.get(o1).equals(resultTaxMap.get(o2)))
            return 0;
        if (resultTaxMap.get(o1) > resultTaxMap.get(o2)){
            return -1;
        } else {
            return 1;
        }
    }
}
