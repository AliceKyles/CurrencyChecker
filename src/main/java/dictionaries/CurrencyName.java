package dictionaries;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyName {

    private static final Map<String, String> VALUES = new LinkedHashMap<>();


    static {
        VALUES.put("USD", "United States dollar");
        VALUES.put("EUR", "Euro");
        VALUES.put("GBP", "Sterling (British pound)");
        VALUES.put("CAD", "Canadian dollar");
        VALUES.put("AUD", "Australian dollar");
        VALUES.put("CHF", "Swiss franc");
        VALUES.put("CNY", "Renminbi (yuan)");
        VALUES.put("JPY", "Japanese yen");
        VALUES.put("AED", "UAEmirates dirham");
        VALUES.put("AMD", "Armenian dram");
        VALUES.put("AZM", "Azerbaijani manat");
        VALUES.put("BGN", "Bulgarian lev");
        VALUES.put("BRL", "Brazilian real");
        VALUES.put("BYN", "Belarusian ruble");
        VALUES.put("CZK", "Czech koruna");
        VALUES.put("DKK", "Danish krone");
        VALUES.put("EGP", "Egyptian pound");
        VALUES.put("GEL", "Georgian lari");
        VALUES.put("HKD", "Hong Kong dollar");
        VALUES.put("HUF", "Hungarian forint");
        VALUES.put("IDR", "Indonesian rupiah");
        VALUES.put("INR", "Indian rupee");
        VALUES.put("KGS", "Kyrgyz som");
        VALUES.put("KRW", "South Korean won");
        VALUES.put("KZT", "Kazakhstani tenge");
        VALUES.put("MDL", "Moldovan leu");
        VALUES.put("NOK", "Norwegian krone");
        VALUES.put("NZD", "New Zealand dollar");
        VALUES.put("PLN", "Polish złoty");
        VALUES.put("QAR", "Qatari riyal");
        VALUES.put("RON", "Romanian leu");
        VALUES.put("RSD", "Serbian dinar");
        VALUES.put("SEK", "Swedish krona");
        VALUES.put("SGD", "Singapore dollar");
        VALUES.put("THB", "Thai baht");
        VALUES.put("TJS", "Tajikistani somoni");
        VALUES.put("TMT", "Turkmenistani manat");
        VALUES.put("TRY", "Turkish lira");
        VALUES.put("UAH", "Ukrainian hryvnia");
        VALUES.put("UZS", "Uzbekistani sum");
        VALUES.put("VND", "Vietnamese đồng");
        VALUES.put("XDR", "Special drawing rights");
        VALUES.put("ZAR", "South African rand");
    }

    @NotNull
    public static Map<String, String> getValues() {
        return Collections.unmodifiableMap(VALUES);
    }
}
