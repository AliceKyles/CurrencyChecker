package struts;

import ReadWriteData.Currency;
import ReadWriteData.ReadData;

public class IndexAction {

    private String currency;
    private Double value;

    public String execute() throws Exception {
        Currency curr = ReadData.getData().get(0);
        setCurrency(curr.getName());
        setValue(curr.getActual());
        return "SUCCESS";
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
