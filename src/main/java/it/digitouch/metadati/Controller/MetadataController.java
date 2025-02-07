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
        public ResponseEntity<String> processFiles(@RequestParam(required = false) String directory) {
            return metadataService.processFiles(directory);
        }
}


