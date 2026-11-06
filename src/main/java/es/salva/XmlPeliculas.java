package es.salva;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XmlPeliculas {
    public List<Pelicula> leerPeliculas(String ruta) {
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new File(ruta));
            NodeList nodosPelicula = doc.getElementsByTagName("pelicula");
            for (int i = 0; i < nodosPelicula.getLength(); i++) {
                Element nodoPelicula = (Element) nodosPelicula.item(i);
                String titulo = nodoPelicula.getElementsByTagName("titulo").item(0).getTextContent();
                String director = nodoPelicula.getElementsByTagName("director").item(0).getTextContent();
                String genero = nodoPelicula.getElementsByTagName("genero").item(0).getTextContent();
                String anioTexto = nodoPelicula.getElementsByTagName("anio").item(0).getTextContent();
                String duracionTexto = nodoPelicula.getElementsByTagName("duracion").item(0).getTextContent();
                String sinopsis = nodoPelicula.getElementsByTagName("sinopsis").item(0).getTextContent();
                String formato = nodoPelicula.getElementsByTagName("formato").item(0).getTextContent();
                String estado = nodoPelicula.getElementsByTagName("estado").item(0).getTextContent();
                String fechaAdquisicionTexto = nodoPelicula.getElementsByTagName("fechaAdquisicion").item(0).getTextContent();
                int anio = Integer.parseInt(anioTexto);
                int duracion = Integer.parseInt(duracionTexto);
                LocalDate fechaAdquisicion = LocalDate.parse(fechaAdquisicionTexto);
                Pelicula pelicula = new Pelicula(titulo, director, genero,
                        anio, duracion, sinopsis,
                        formato, estado, fechaAdquisicion);
                peliculas.add(pelicula);

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("No se ha podido leer el XML: " + e.getMessage());
        }
        return peliculas;
    }
}
