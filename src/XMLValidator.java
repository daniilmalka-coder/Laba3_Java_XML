import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.File;

public class XMLValidator {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("Popular_Baby_Names.xml");
            File xsdFile = new File("schema.xsd");

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));

            System.out.println("XML валідний");

        } catch (Exception e) {
            System.out.println("XML НЕ валідний");
            e.printStackTrace();
        }
    }
}