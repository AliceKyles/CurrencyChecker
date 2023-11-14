package struts;

import ReadWriteData.Currency;
import ReadWriteData.GetCurrency;
import ReadWriteData.ReadData;
import ReadWriteData.WriteData;
import com.opensymphony.xwork2.ActionSupport;
import dictionaries.CurrencyName;
import org.apache.struts2.action.SessionAware;

import java.util.*;

public class IndexAction extends ActionSupport implements SessionAware {

    protected Map<String, Object> session;
    private final List<Currency> currencies = new ArrayList<>();
    private Integer deleteCurrency;
    private String getActual;
    private String getFromFile;
    private String writeToFile;
    private String addCurrency;

    public String execute() throws Exception {
        if (getFromFile != null) {
            currencies.clear();
            currencies.addAll(ReadData.getData());
        } else if (writeToFile != null) {
            WriteData.write(currencies);
        } else if (getActual != null) {
            GetCurrency.getCurrency(currencies);
        } else if (addCurrency != null) {
            currencies.add(new Currency());
        }
        removeCurrency(deleteCurrency);
        session.put("currencies", currencies);
        return SUCCESS;
    }

    public void removeCurrency(Integer num) {
        if (num == null || num > currencies.size() || num < 1) {
            return;
        }
        currencies.remove(currencies.get(num - 1));
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
        map.put(null, "yellow");
        return map;
    }

    public Integer getDeleteCurrency() {
        return deleteCurrency;
    }

    public void setDeleteCurrency(Integer deleteCurrency) {
        this.deleteCurrency = deleteCurrency;
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
