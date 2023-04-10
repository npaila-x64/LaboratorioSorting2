package org.app;

import org.app.Sort.QuickSort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Programa que ordena registros relacionados a la calidad del aire en australia
en base a la variable 'PM10 1 h', o material particulado de 10 micrómetros promediado
sobre una hora.
Enlace a la fuente de datos:
https://www.data.act.gov.au/Environment/Air-Quality-Monitoring-Data/94a5-zqnn
 */

public class Main {
    public static void main(String[] args) {
        List<Registro> registros = leerCSV();

        QuickSort qs = new QuickSort(registros);
        qs.quickSort();

        List<Registro> registrosOrdenados = qs.toArrayList();
        guardarCSV(registrosOrdenados);

        Registro menor = registrosOrdenados.get(0);
        Registro mayor = registrosOrdenados.get(registrosOrdenados.size() - 1);

        System.out.println("El registro de calidad de aire cuyo PM10 1 h es MENOR es: " + menor);
        System.out.println("El registro de calidad de aire cuyo PM10 1 h es MAYOR es: " + mayor);
    }

    public static void guardarCSV(List<Registro> registros){
        try {
            escribirCSV(registros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirCSV(List<Registro> registros) throws IOException{
        File csvFile = new File("sorted_data.csv");
        FileWriter fileWriter = new FileWriter(csvFile);

        StringBuilder line;
        line = new StringBuilder();
        line.append("Name,GPS,PM10 1 hr,PM10\n");
        fileWriter.write(line.toString());

        for (Registro registro : registros) {
            line = new StringBuilder();
            line.append(registro.getName());
            line.append(',');
            line.append(registro.getGps());
            line.append(',');
            line.append(registro.getPm101h());
            line.append(',');
            line.append(registro.getPm10());
            line.append("\n");
            fileWriter.write(line.toString());
        }

        fileWriter.close();
    }

    public static List<Registro> leerCSV(){
        String archivoCSV = "data.csv";
        String csvSplitBy = ",";
        String line;

        List<Registro> registros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columnas = line.split(csvSplitBy);

                Registro registro = new Registro();

                registro.setName(columnas[0]);
                registro.setGps(columnas[1]);
                registro.setPm101h(Double.parseDouble(columnas[8]));
                registro.setPm10(columnas[10]);

                registros.add(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  registros;
    }
}
