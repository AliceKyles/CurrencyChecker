import ReadWriteData.Currency;
import ReadWriteData.GetCurrency;
import ReadWriteData.ReadData;
import ReadWriteData.WriteData;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            Currency a = new Currency("USD", null, 70.1);
            Currency b = new Currency("CAD", 45.1, 65.2);
            Currency c = new Currency("GBP", 55.1, 44.2);
            List<Currency> currencies = Arrays.asList(a, b, c);
            WriteData.write(currencies);
            currencies = ReadData.getData();
            Assertions.assertEquals(2, currencies.size());
            GetCurrency.getCurrency(currencies);
            for (Currency currency : currencies) {
                int result = ObjectUtils.defaultIfNull(currency.getCompare(), -2);
                switch (result) {
                    case Currency.TOO_LOW:
                        System.out.println(currency.getName() + " is too low, should be more than " + currency.getFrom() + ", is " + currency.getActual());
                        break;
                    case Currency.TOO_HIGH:
                        System.out.println(currency.getName() + " is too high, should be less than " + currency.getTo() + ", is " + currency.getActual());
                        break;
                    case Currency.PERFECT:
                        System.out.println(currency.getName() + " is " + currency.getActual() + ", within the correct range");
                        break;
                    default:
                        System.out.println("Something went wrong : " + currency.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
