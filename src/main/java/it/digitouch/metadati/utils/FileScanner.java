package it.digitouch.metadati.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {
    public static List<File> scannerFolder(String directoryPath) {
        List<File> fileList = new ArrayList<>();

//        System.out.println("Verifica percorso: " + directoryPath);
        File folder = new File(directoryPath);

//        System.out.println("Esiste? " + folder.exists());
//        System.out.println("È una directory? " + folder.isDirectory());

        if (folder.exists() && folder.isDirectory()) {
            System.out.println("Esplorazione della cartella: " + directoryPath);
            ScannerFile(folder, fileList);
        } else {
            System.out.println("La cartella non esiste o non è una directory: " + directoryPath);
        }

        System.out.println("Numero totale di file trovati: " + fileList.size());
        return fileList;
    }

    private static void ScannerFile(File folder, List<File> fileList) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
//                    System.out.println("File trovato: " + file.getAbsolutePath());
                    fileList.add(file);
                } else if (file.isDirectory()) {
//                    System.out.println("Entrando nella cartella: " + file.getAbsolutePath());
                    ScannerFile(file, fileList);
                }
            }
        }
    }

}

