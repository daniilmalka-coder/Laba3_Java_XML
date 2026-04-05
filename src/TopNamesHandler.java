import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TopNamesHandler extends DefaultHandler {

    private List<BabyName> names = new ArrayList<>();

    private String targetEthnicity;
    private int limit;

    private String currentElement = "";
    private StringBuilder value = new StringBuilder();

    private String ethnicity, name, gender;
    private int count, rank;

    public TopNamesHandler(String targetEthnicity, int limit) {
        this.targetEthnicity = targetEthnicity;
        this.limit = limit;
    }

    public List<BabyName> getNames() {
        return names;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        value.setLength(0);
    }

    @Override
    public void characters(char ch[], int start, int length) {
        value.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        String val = value.toString().trim();

        switch (qName.toLowerCase()) {
            case "ethcty":
                ethnicity = val;
                break;
            case "nm":
                name = val;
                break;
            case "gndr":
                gender = val;
                break;
            case "cnt":
                count = Integer.parseInt(val);
                break;
            case "rnk":
                rank = Integer.parseInt(val);
                break;
        }

        // кінець одного запису
        if (qName.equalsIgnoreCase("row")) {
            if (ethnicity != null && ethnicity.equalsIgnoreCase(targetEthnicity)) {
                names.add(new BabyName(name, gender, count, rank));
            }
        }
    }
}
