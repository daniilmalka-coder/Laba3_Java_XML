import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public class TopNamesApp {

    public static void main(String[] args) {
        try {
            String ethnicity = "HISPANIC"; //
            int topN = 10;

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            TopNamesHandler handler = new TopNamesHandler(ethnicity, topN);
            parser.parse("Popular_Baby_Names.xml", handler);

            List<BabyName> list = handler.getNames();

            // сортування по рейтингу
            list.sort(Comparator.comparingInt(BabyName::getRank));

            // тільки TOP N
            list = list.subList(0, Math.min(topN, list.size()));

            // створення XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docFactory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("TopNames");
            doc.appendChild(root);

            for (BabyName b : list) {
                Element nameEl = doc.createElement("Baby");

                Element n = doc.createElement("Name");
                n.appendChild(doc.createTextNode(b.getName()));

                Element g = doc.createElement("Gender");
                g.appendChild(doc.createTextNode(b.getGender()));

                Element c = doc.createElement("Count");
                c.appendChild(doc.createTextNode(String.valueOf(b.getCount())));

                Element r = doc.createElement("Rank");
                r.appendChild(doc.createTextNode(String.valueOf(b.getRank())));

                nameEl.appendChild(n);
                nameEl.appendChild(g);
                nameEl.appendChild(c);
                nameEl.appendChild(r);

                root.appendChild(nameEl);
            }

            // запис у файл
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("top_names.xml"));

            transformer.transform(source, result);

            System.out.println("Файл top_names.xml створено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}