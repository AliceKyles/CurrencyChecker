package struts;

import ReadWriteData.Currency;
import ReadWriteData.GetCurrency;
import ReadWriteData.ReadData;
import ReadWriteData.WriteData;
import com.opensymphony.xwork2.ActionSupport;
import dictionaries.CurrencyName;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.struts2.action.SessionAware;

import java.util.*;

public class IndexAction extends ActionSupport implements SessionAware {

    protected Map<String, Object> session;
    private final List<Currency> currencies = new ArrayList<>();
    private String delete;
    private String getActual;
    private String getFromFile;
    private String writeToFile;
    private String addCurrency;

    public String execute() {
        if (currencies.isEmpty()) {
            List<Currency> c = (List<Currency>) session.get("currencies");
            if (c != null) {
                currencies.addAll(c);
            }
        }
        try {
            if (getFromFile != null) {
                currencies.clear();
                currencies.addAll(ReadData.getData());
            } else if (writeToFile != null) {
                WriteData.write(currencies);
            } else if (getActual != null) {
                GetCurrency.getCurrency(currencies);
            } else if (addCurrency != null) {
                currencies.add(new Currency());
            } else if (delete != null) {
                removeCurrency(delete);
            }
        } catch (Exception e) {
            addActionError("Internal error: " + e.getMessage());
        }
        session.put("currencies", currencies);
        return INPUT;
    }

    public void removeCurrency(String index) {
        int num = Integer.parseInt(index);
        if (num > currencies.size() - 1 || num < 0) {
            return;
        }
        currencies.remove(currencies.get(num));
    }

    public void validate() {
        if (ObjectUtils.anyNotNull(getFromFile, addCurrency, delete)) {
            return;
        }
        int i = 0;
        Map<String, Pair<Integer, Boolean>> names = new HashMap<>();
        boolean noNameErrors = true, noFromToEmpty = true, noIncorrectValues = true;
        for (Currency currency : getCurrencies()) {
            if (names.containsKey(currency.getName())) {
                Pair<Integer, Boolean> firstName = names.get(currency.getName());
                if (firstName.getRight()) {
                    addFieldError("currencies[" + firstName.getKey() + "].name", "");
                    names.put(currency.getName(), Pair.of(firstName.getKey(), false));
                }
                addFieldError("currencies[" + i + "].name", "");
                noNameErrors = addActionError(noNameErrors, "A currency can't be chosen multiple times");
            } else {
                names.put(currency.getName(), Pair.of(i, true));
            }
            if (ObjectUtils.allNull(currency.getTo(), currency.getFrom())) {
                addFieldError("currencies[" + i + "].from", "");
                addFieldError("currencies[" + i + "].to", "");
                noFromToEmpty = addActionError(noFromToEmpty, "You have to enter a value into From or To");
            } else if (ObjectUtils.allNotNull(currency.getFrom(), currency.getTo()) && currency.getFrom() > currency.getTo()) {
                addFieldError("currencies[" + i + "].from", "");
                addFieldError("currencies[" + i + "].to", "");
                noIncorrectValues = addActionError(noIncorrectValues, "Value of From must be less than value of To");
            }
            i++;
        }
    }

    private boolean addActionError(boolean noErrors, String message) {
        if (noErrors) {
            addActionError(message);
        }
        return false;
    }


    public List<Currency> getCurrencies() {
        return currencies;
    }

    public Map<String, String> getCurrencyNames() {
        return CurrencyName.getValues();
    }

    public Map<Integer, String> getColour() {
        Map<Integer, String> map = new HashMap<>();
        map.put(Currency.PERFECT, "green");
        map.put(Currency.TOO_LOW, "red");
        map.put(Currency.TOO_HIGH, "red");
        map.put(null, "orange");
        return map;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public void setGetActual(String getActual) {
        this.getActual = getActual;
    }

    public void setGetFromFile(String getFromFile) {
        this.getFromFile = getFromFile;
    }

    public void setWriteToFile(String writeToFile) {
        this.writeToFile = writeToFile;
    }

    public void setAddCurrency(String addCurrency) {
        this.addCurrency = addCurrency;
    }

    @Override
    public void withSession(Map<String, Object> map) {
        this.session = map;
    }
}
