package idv.wilson.demo.dms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import idv.wilson.demo.dms.entity.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {

}
