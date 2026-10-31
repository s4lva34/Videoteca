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
                String titulo = pelicula.getElementsByTagName("titulo").item(0).getTextContent();
                String director = pelicula.getElementsByTagName("director").item(0).getTextContent();
                String genero = pelicula.getElementsByTagName("genero").item(0).getTextContent();
                String anioTexto = pelicula.getElementsByTagName("anio").item(0).getTextContent();
                String duracionTexto = pelicula.getElementsByTagName("duracion").item(0).getTextContent();
                String sinopsis = pelicula.getElementsByTagName("sinopsis").item(0).getTextContent();
                String formato = pelicula.getElementsByTagName("formato").item(0).getTextContent();
                String estado = pelicula.getElementsByTagName("estado").item(0).getTextContent();
                String fechaAdquisicionTexto = pelicula.getElementsByTagName("fechaAdquisicion").item(0).getTextContent();
                int anio = Integer.parseInt(anioTexto);
                int duracion = Integer.parseInt(duracionTexto);
                LocalDate fechaAdquisicion = LocalDate.parse(fechaAdquisicionTexto);
                Pelicula peliculaLeida = new Pelicula(titulo, director, genero,
                        anio, duracion, sinopsis,
                        formato, estado, fechaAdquisicion);
                System.out.println(peliculaLeida.getTitulo() + ", " + peliculaLeida.getDuracion() + " min.");

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("No se ha podido leer el XML: " + e.getMessage());
        }
    }
}
