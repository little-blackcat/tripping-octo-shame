import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	int[][] XMLParsing()
	{
		int second;
		int[][] myCosts = new int[16][16];
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("ulysses16.xml");
			NodeList vertexList = doc.getElementsByTagName("vertex");
			for(int i=0; i<vertexList.getLength(); i++) {
				Node p = vertexList.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element vertex = (Element)p;
					//String cost = vertex.getAttribute("cost");
					NodeList edgeList = vertex.getChildNodes();
					for(int j=0; j<edgeList.getLength(); j++) {
						Node n = edgeList.item(j);
						if(n.getNodeType() == Node.ELEMENT_NODE){
							Element cost = (Element)n;
							double costElement = Double.parseDouble(cost.getAttribute("cost"));
							int costElementInt =(int)(costElement);
							//System.out.println(cost.getTextContent() + " " + costElement);
							second = Integer.parseInt(cost.getTextContent());
							myCosts[i][second] = costElementInt;
						}
					}
				}
			myCosts[i][i] = -1;
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myCosts;
	}
}
