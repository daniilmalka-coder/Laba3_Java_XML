import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class EthnicityHandler extends DefaultHandler {

    private Set<String> ethnicities = new HashSet<>();
    private boolean isEthcty = false;
    private StringBuilder currentValue = new StringBuilder();

    public Set<String> getEthnicities() {
        return ethnicities;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("ethcty")) {
            isEthcty = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (isEthcty) {
            currentValue.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("ethcty")) {
            ethnicities.add(currentValue.toString().trim());
            isEthcty = false;
        }
    }
}