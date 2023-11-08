package ReadWriteData;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCurrency {

    public static void getCurrency(List<Currency> curr) throws Exception {
        if (curr.isEmpty()) {
            return;
        }
        Map<String, Currency> currencies = curr.stream().collect(Collectors.toMap(Currency::getName, cur -> cur));
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("https://www.cbr-xml-daily.ru/daily.xml");
            Node root = document.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            int count = 0, max = currencies.size();
            for (int i = 0; i < childNodes.getLength(); i++) {
                NodeList currency = childNodes.item(i).getChildNodes();
                Node code = currency.item(1);
                if (!code.getNodeName().equals("CharCode")) {
                    throw new Exception("The structure of the received file is different than expected");
                }
                if (currencies.containsKey(code.getFirstChild().getNodeValue())) {
                    currencies.get(code.getFirstChild().getNodeValue()).setActual(Double.valueOf(StringUtils.replace(currency.item(4).getFirstChild().getNodeValue(), ",", ".")));
                    count++;
                    if (count == max) {
                        return;
                    }
                }
            }
        } catch (ParserConfigurationException pe) {
            throw new Exception("Can't parse received file");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}

