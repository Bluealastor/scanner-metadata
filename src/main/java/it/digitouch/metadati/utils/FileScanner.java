package it.digitouch.metadati.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {

    public static List<File> getFilesFromFolder(String directoryPath) {
        List<File> fileList = new ArrayList<>();
        File folder = new File(directoryPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) { // Ignora eventuali sottocartelle
                        fileList.add(file);
                    }
                }
            }
        } else {
            System.out.println("La cartella non esiste o non è una directory: " + directoryPath);
        }

        return fileList;
    }
}

