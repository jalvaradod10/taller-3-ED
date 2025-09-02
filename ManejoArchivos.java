import java.io.*;
import java.util.*;

public class ManejoArchivos {
    public static List<String> leerArchivo(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + nombreArchivo);
        }
        return lineas;
    }

    public static void escribirArchivo(String nombreArchivo, List<String> lineas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) {
                pw.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo archivo: " + nombreArchivo);
        }
    }
}
