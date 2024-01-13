import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        double amount = Double.parseDouble(args[0]);
        String toCurrency = args[1];
        String[] currencies = {
                "USD", "EUR", "GBP"
        };
        boolean isSupportedCurrency = Boolean.FALSE;
        for (String currency : currencies) {
            if (currency.equals(toCurrency)) {
                isSupportedCurrency = Boolean.TRUE;
                break;
            }
        }
        if (!isSupportedCurrency) {
            System.out.println("Not a supported currency.");
        } else {
            Map<String, Double> exchangeRates = new HashMap<>();
            exchangeRates.put("USD", 1D);
            exchangeRates.put("EUR", 1.2D);
            exchangeRates.put("GBP", 1.4D);

            Double exchangeRate = exchangeRates.get(toCurrency);
            String newAmount = String.valueOf(amount * exchangeRate);

            System.out.println(newAmount);
        }
    }
}
