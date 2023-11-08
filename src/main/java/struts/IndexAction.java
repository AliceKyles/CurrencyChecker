package struts;

import ReadWriteData.Currency;
import ReadWriteData.GetCurrency;
import ReadWriteData.ReadData;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Collections;

public class IndexAction extends ActionSupport {

    private String currency;
    private Double value;

    public String execute() throws Exception {
        Currency curr = ReadData.getData().get(0);
        GetCurrency.getCurrency(Collections.singletonList(curr));
        setCurrency(curr.getName());
        setValue(curr.getActual());
        return SUCCESS;
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
