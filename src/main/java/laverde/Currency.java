package laverde;

import java.util.Map;

public class Currency {

    private final Map<String, Double> conversion_rates;

    public Currency(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

     @Override
        public String toString() {
         return
                 "conversion_rates=" + conversion_rates;

     }


}
