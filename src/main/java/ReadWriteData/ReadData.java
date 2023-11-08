package ReadWriteData;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadData {

    public static List<Currency> getData() throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = new GetFile().getFile();
        if (file == null) {
            throw new Exception("File wasn't found");
        }
        Document document = documentBuilder.parse(new FileInputStream(file));
        Node root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            NodeList nodes = childNodes.item(i).getChildNodes();
            Currency currency = new Currency();
            for (int j = 0; j < nodes.getLength(); j++) {
                Node code = nodes.item(j);
                String name = StringUtils.defaultString(code.getNodeName());
                if (name.equals("CharCode")) {
                    currency.setName(code.getFirstChild().getNodeValue());
                } else if (name.equals("From")) {
                    currency.setFrom(Double.valueOf(code.getFirstChild().getNodeValue()));
                } else if (name.equals("To")) {
                    currency.setTo(Double.valueOf(code.getFirstChild().getNodeValue()));
                } else {
                    throw new Exception("The structure of the received file is different than expected");
                }
            }
            currencies.add(currency);
        }

        return currencies;
    }
}
