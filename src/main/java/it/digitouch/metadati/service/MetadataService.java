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
import java.util.Optional;



@Service
public class MetadataService {

    @Autowired
    private MetadataRepository repository;

    public String processFiles(String directoryPath) {
        List<File> files = FileScanner.scannerFolder(directoryPath);
        long totalProcessingTime = 0;

        if (!files.isEmpty()) {
            for (File file : files) {
                MetadataEntity metadata = new MetadataEntity();

                // Iniziamo la misurazione del tempo
                long startTime = System.nanoTime();

                // Estrai i metadati
                Map<String, String> metadati = MetadataMapping.extractMetadata(file);

                var formatoFile = (metadati.get("formatoFile") != null) ? metadati.get("formatoFile") : "Formato non valido";

                // Mappa i metadati sull'entità
                metadata.setUrlOggetto(metadati.get("urlOggetto"));
                metadata.setNomeOggetto(metadati.get("nomeOggetto"));
                metadata.setDimensioneFile(metadati.get("dimensioneFile"));
                metadata.setFormatoFile(formatoFile);

                // Salva nel database
                repository.save(metadata);

                // Calcola il tempo impiegato per il salvataggio in nanosecondi
                long endTime = System.nanoTime();
                long processingTime = endTime - startTime;
                totalProcessingTime += processingTime;

                // Calcola il tempo in millisecondi
                long processingTimeInMillis = processingTime / 1_000_000;

                // Log del tempo impiegato per il singolo file in millisecondi
                System.out.println("File " + file.getName() + " mappato e salvato in " + processingTimeInMillis + " millisecondi.");
            }

            // Log del tempo totale per tutti i file
            System.out.println("Tempo totale di elaborazione per tutti i file: " + totalProcessingTime + " nanosecondi.");

            return "Elaborazione completata con successo!";
        } else {
            System.out.println("Nessun file trovato nella cartella: " + directoryPath);
            return "Nessun file trovato nella cartella.";
        }
    }
}
