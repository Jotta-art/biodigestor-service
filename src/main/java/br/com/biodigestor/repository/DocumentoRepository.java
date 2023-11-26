package br.com.biodigestor.repository;

import br.com.biodigestor.model.Documento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends MongoRepository<Documento, String> {
}
