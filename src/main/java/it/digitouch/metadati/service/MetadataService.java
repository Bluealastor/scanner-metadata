package it.digitouch.metadati.service;

import it.digitouch.metadati.entity.MetadataEntity;
import it.digitouch.metadati.repository.MetadataRepository;
import it.digitouch.metadati.utils.FileScanner;
import it.digitouch.metadati.utils.MetadataMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Service
public class MetadataService {

    @Autowired
    private MetadataRepository repository;

    public ResponseEntity<String> processFiles(String directoryPath) {
        // Se directoryPath è null o vuoto, usa il percorso di default (cartella "metadati" in resources in Docker)
        String finalPath = (directoryPath != null && !directoryPath.isEmpty()) ? directoryPath : "/app/resources/metadati";


        File folder = new File(finalPath);

        // Verifica se la cartella esiste, altrimenti restituisci un errore
        if (!folder.exists() || !folder.isDirectory()) {
            return ResponseEntity.badRequest().body("Errore: La cartella non esiste o non è valida: " + finalPath);
        }

        List<File> files = FileScanner.scannerFolder(finalPath);

        if (files.isEmpty()) {
            return ResponseEntity.ok("Nessun file trovato nella cartella: " + finalPath);
        }

        for (File file : files) {
            long startTime = System.currentTimeMillis();

            MetadataEntity metadata = new MetadataEntity();
            Map<String, String> metadati = MetadataMapping.extractMetadata(file);

            metadata.setUrlOggetto(metadati.get("urlOggetto"));
            metadata.setNomeOggetto(metadati.get("nomeOggetto"));
            metadata.setDimensioneFile(metadati.get("dimensioneFile"));
            metadata.setFormatoFile(metadati.getOrDefault("formatoFile", "Formato non valido"));

            repository.save(metadata);

            long endTime = System.currentTimeMillis();
            double elapsedTime = (endTime - startTime) / 1000.0;

            System.out.println("File " + file.getName() + " salvato su MongoDB in " + elapsedTime + " secondi.");
        }

        return ResponseEntity.ok("Tutti i file sono stati elaborati con successo.");
    }




}
