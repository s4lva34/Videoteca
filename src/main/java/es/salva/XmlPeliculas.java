package es.salva;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlPeliculas {
    public void leerPeliculas(String ruta){
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new File(ruta));
            NodeList peliculas = doc.getElementsByTagName("pelicula");
            System.out.println(peliculas.getLength());
            for (int i = 0; i < peliculas.getLength() ; i++) {
                Element pelicula = (Element) peliculas.item(i);
                System.out.println(pelicula.getElementsByTagName("titulo").item(0).getTextContent());

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("No se ha podido leer el XML: " + e.getMessage());
        }
    }
}
