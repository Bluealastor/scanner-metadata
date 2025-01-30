package it.digitouch.metadati.service;

import it.digitouch.metadati.entity.MetadataEntity;
import it.digitouch.metadati.repository.MetadataRepository;
import it.digitouch.metadati.utils.FileScanner;
import it.digitouch.metadati.utils.MetadataMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class MetadataService {

    @Autowired
    private MetadataRepository repository;

    public void processFiles(String directoryPath) {
        List<File> files = FileScanner.getFilesFromFolder(directoryPath);

        if (!files.isEmpty()) {
            for (File file : files) {
                MetadataEntity metadata = new MetadataEntity();
                // Estrarre i metadati
                Map<String, String> metadati = MetadataMapping.extractMetadata(file);

                var formatoFile = (metadati.get("formatoFile") != null) ? metadati.get("formatoFile") : "Formato non valido";

                // Creare oggetto MongoDB

                metadata.setUrlOggetto(metadati.get("urlOggetto"));
                metadata.setNomeOggetto(metadati.get("nomeOggetto"));
                metadata.setDimensioneFile(metadati.get("dimensioneFile"));
                metadata.setFormatoFile(formatoFile);
                // Salvare su MongoDB
                repository.save(metadata);
                System.out.println("File " + file.getName() + " salvato su MongoDB.");
            }
        } else {
            System.out.println("Nessun file trovato nella cartella: " + directoryPath);
        }
    }
}


