import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import java.io.File;

public class ReadTopNamesDOM {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("top_names.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("Baby");

            System.out.println("=== TOP ІМЕНА ===");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;

                    String name = el.getElementsByTagName("Name").item(0).getTextContent();
                    String gender = el.getElementsByTagName("Gender").item(0).getTextContent();
                    String count = el.getElementsByTagName("Count").item(0).getTextContent();
                    String rank = el.getElementsByTagName("Rank").item(0).getTextContent();

                    System.out.println("Ім'я: " + name);
                    System.out.println("Стать: " + gender);
                    System.out.println("Кількість: " + count);
                    System.out.println("Рейтинг: " + rank);
                    System.out.println("----------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}