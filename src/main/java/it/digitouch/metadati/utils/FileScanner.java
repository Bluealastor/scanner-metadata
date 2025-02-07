package it.digitouch.metadati.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {
    public static List<File> scannerFolder(String directoryPath) {
        List<File> fileList = new ArrayList<>();

        // Se directoryPath è nullo o vuoto, imposta un valore di default
        if (directoryPath == null || directoryPath.isEmpty()) {
            directoryPath = "src/main/resources/Metadati"; // Modifica con il tuo percorso
            System.out.println("Percorso non specificato. Uso il percorso di default: " + directoryPath);
        }

        File folder = new File(directoryPath);

        if (folder.exists() && folder.isDirectory()) {
            System.out.println("Esplorazione della cartella: " + directoryPath);
            scanFiles(folder, fileList);
        } else {
            System.out.println("La cartella non esiste o non è una directory: " + directoryPath);
        }

        System.out.println("Numero totale di file trovati: " + fileList.size());
        return fileList;
    }

    private static void scanFiles(File folder, List<File> fileList) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file);
                } else if (file.isDirectory()) {
                    scanFiles(file, fileList);
                }
            }
        }
    }
}