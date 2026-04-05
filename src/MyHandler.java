import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class MyHandler extends DefaultHandler {

    private Set<String> tags = new HashSet<>();
    private int elementCount = 0;
    private final int LIMIT = 20; // скільки елементів вивести

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        tags.add(qName);

        if (elementCount < LIMIT) {
            System.out.println("Start: " + qName);
            elementCount++;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (elementCount < LIMIT) {
            String value = new String(ch, start, length).trim();
            if (!value.isEmpty()) {
                System.out.println("Value: " + value);
            }
        }
    }
}