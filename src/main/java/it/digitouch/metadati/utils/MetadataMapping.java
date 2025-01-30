package it.digitouch.metadati.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MetadataMapping {

    public static Map<String, String> extractMetadata(File file) {
        Map<String, String> metadata = new HashMap<>();
        try {
            Path filePath = Paths.get(file.getAbsolutePath());

            metadata.put("urlOggetto", file.getAbsolutePath());
            metadata.put("nomeOggetto", file.getName());
            metadata.put("dimensioneFile", String.valueOf(file.length()) + " bytes");
            metadata.put("formatoFile", Files.probeContentType(filePath)); // Determina il MIME Type

        } catch (Exception e) {
            metadata.put("errore", "Impossibile leggere i metadati: " + e.getMessage());
        }
        return metadata;
    }
}
