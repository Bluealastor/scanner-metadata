package it.digitouch.metadati.Controller;

import it.digitouch.metadati.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CA01CN01/LDIG002")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @PostMapping("/PK0000004")
    public ResponseEntity<String> processFiles(@RequestParam(value = "directory") String directoryPath) {
        // Chiamare il servizio per processare i file
        String result = metadataService.processFiles(directoryPath);

        // Se il risultato contiene 'Errore', rispondi con un errore 500
        if (result.startsWith("Errore")) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Altrimenti, rispondi con un messaggio di successo 200
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


