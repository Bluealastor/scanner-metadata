package it.digitouch.metadati.repository;

import it.digitouch.metadati.entity.MetadataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetadataRepository extends MongoRepository<MetadataEntity, String> {
}
