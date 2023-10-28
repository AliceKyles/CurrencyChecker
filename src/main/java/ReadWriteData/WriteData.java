package ReadWriteData;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteData {

    public static void write(@NotNull List<Currency> currency) throws XMLStreamException, IOException {
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter("data.xml"));

        writer.writeStartDocument("1.0");
        writer.writeStartElement("Currencies");
        for (Currency curr : currency) {

            if (StringUtils.isBlank(curr.getName()) || ObjectUtils.firstNonNull(curr.getTo(), curr.getFrom()) == null ||
                    curr.getTo() != null && curr.getFrom() != null && curr.getFrom() > curr.getTo()) {
                continue;
            }
            writer.writeStartElement("Currency");
            writer.writeStartElement("CharCode");
            writer.writeCharacters(curr.getName());
            writer.writeEndElement();

            String from = String.valueOf(curr.getFrom());
            if (!from.equals("null")) {
                writer.writeStartElement("From");
                writer.writeCharacters(from);
                writer.writeEndElement();
            }

            String to = String.valueOf(curr.getTo());
            if (!to.equals("null")) {
                writer.writeStartElement("To");
                writer.writeCharacters(to);
                writer.writeEndElement();
            }

            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
    }
}
