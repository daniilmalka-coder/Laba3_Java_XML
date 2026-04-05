import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class EthnicityMain {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);

            SAXParser saxParser = factory.newSAXParser();

            EthnicityHandler handler = new EthnicityHandler();

            saxParser.parse("Popular_Baby_Names.xml", handler);

            System.out.println("Етнічні групи:");
            for (String e : handler.getEthnicities()) {
                System.out.println(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}