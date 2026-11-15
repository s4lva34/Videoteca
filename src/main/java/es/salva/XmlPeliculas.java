package es.salva;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee y escribe archivos XML de películas usando DOM.
 */
public class XmlPeliculas {
    /**
     * Lee un archivo XML y convierte cada etiqueta pelicula en un objeto Pelicula.
     *
     * @param ruta ruta del archivo XML que se va a leer
     * @return lista con las películas leídas.
     */
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
                int anio = Integer.parseInt(anioTexto);
                int duracion = Integer.parseInt(duracionTexto);
                Pelicula pelicula = new Pelicula(titulo, director, genero,
                        anio, duracion, sinopsis,
                        formato, estado, null);
                peliculas.add(pelicula);

            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("No se ha podido leer el XML: " + e.getMessage());
        }
        return peliculas;

    }
    /**
     * Escribe una lista de películas en un archivo XML con la estructura del enunciado.
     *
     * @param peliculas películas que se van a guardar
     * @param ruta ruta del archivo XML que se va a crear
     * @return número de películas grabadas en el archivo.
     */
    public int escribirPeliculas(List<Pelicula> peliculas, String ruta) {
        int grabadas = 0;
        try {
            Document doc = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().newDocument();
            Element raiz = doc.createElement("peliculas");
            doc.appendChild(raiz);
            for (int i = 0; i < peliculas.size(); i++) {
                Pelicula pelicula = peliculas.get(i);
                Element nodoPelicula = doc.createElement("pelicula");
                agregarEtiqueta(doc, nodoPelicula, "titulo", pelicula.getTitulo());
                agregarEtiqueta(doc, nodoPelicula, "director", pelicula.getDirector());
                agregarEtiqueta(doc, nodoPelicula, "genero", pelicula.getGenero());
                agregarEtiqueta(doc, nodoPelicula, "anio", String.valueOf(pelicula.getAnio()));
                agregarEtiqueta(doc, nodoPelicula, "duracion", String.valueOf(pelicula.getDuracion()));
                agregarEtiqueta(doc, nodoPelicula, "formato", pelicula.getFormato());
                agregarEtiqueta(doc, nodoPelicula, "estado", pelicula.getEstado());
                agregarEtiqueta(doc, nodoPelicula, "sinopsis", pelicula.getSinopsis());
                raiz.appendChild(nodoPelicula);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
            grabadas = peliculas.size();
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("No se ha podido escribir el XML: " + e.getMessage());
        }
        return grabadas;
    }
    /**
     * Crea una etiqueta con su texto y la mete dentro de otra.
     *
     * @param doc documento XML donde se crea la etiqueta
     * @param padre etiqueta dentro de la que va la nueva
     * @param nombre nombre de la etiqueta, por ejemplo titulo
     * @param valor texto que va dentro de la etiqueta
     */
    private void agregarEtiqueta(Document doc, Element padre, String nombre, String valor) {
        Element etiqueta = doc.createElement(nombre);
        etiqueta.setTextContent(valor);
        padre.appendChild(etiqueta);
    }


}

