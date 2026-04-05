import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXExample {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);

            SAXParser saxParser = factory.newSAXParser();

            MyHandler handler = new MyHandler();

            saxParser.parse("Popular_Baby_Names.xml", handler);

            System.out.println("\n--- Список тегів ---");
            for (String tag : handler.getTags()) {
                System.out.println(tag);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}