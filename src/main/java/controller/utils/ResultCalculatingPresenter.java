package controller.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultCalculatingPresenter {

    private Map<String, Double> resultTaxMap = new HashMap<>();

    public void addResultItem(String viewNameOfTax, double taxNeedToPay){
        resultTaxMap.put(viewNameOfTax, taxNeedToPay);
    }

    public Map<String, Double> getResultTaxMap() {
        return resultTaxMap;
    }

    public Map<String, Double> sortResultTaxMap(){
        return sortByValue(resultTaxMap);
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
