package it.digitouch.metadati.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "dati")
public class MetadataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String urlOggetto;
    private String nomeOggetto;
    private String dimensioneFile;
    private String formatoFile;
    private String codiceCantiere;
    private String codiceLotto;
    private String codicePacchetto;


}
