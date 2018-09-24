package cracking.moderate;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XMLEncoding {

    Map<String, Integer> tagsMap = new HashMap<>();

    XMLEncoding () {
        tagsMap.put("family", 1);
        tagsMap.put("lastName", 2);
        tagsMap.put("person", 3);
        tagsMap.put("firstName", 4);
    }

    String encode(Element root) {
        StringBuilder sb = new StringBuilder();
        helper(sb, root);
        return sb.toString();
    }

    void helper(StringBuilder sb, Element root) {
        encode(tagsMap.get(root.getTagName()), sb);
        NamedNodeMap attrs = root.getAttributes();
        if (attrs.getLength() > 0) {
            for(int i = 0; i < attrs.getLength(); i++) {
                Node attrNode = attrs.item(i);
                encode(tagsMap.get(attrNode.getNodeName()), sb);
                encode(attrNode.getNodeValue(), sb);
            }
            encode("0", sb);
        }
        NodeList child = root.getChildNodes();
        if (child.getLength() > 0) {
            for (int i = 0; i < child.getLength(); i++) {
                Node node = child.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    helper(sb, (Element) node);
                }
                else if (node.getNodeType() == Node.TEXT_NODE) {
                    encode(node.getNodeValue(), sb);
                }
            }
        }
        encode("0", sb);
    }

    void encode(int val, StringBuilder sb) {
        sb.append(val).append(" ");
    }

    void encode(String val, StringBuilder sb) {
        sb.append(val).append(" ");
    }

    public static void main(String[] args) {
        XMLEncoding obj = new XMLEncoding();
        String xmlAsString =
                "<family lastName=\"Gee\">" +
                "<person firstName=\"Marty\">Some message</person>" +
                "</family>";
        try {
            Element node =  DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xmlAsString.getBytes()))
                    .getDocumentElement();

            System.out.println(obj.encode(node));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
