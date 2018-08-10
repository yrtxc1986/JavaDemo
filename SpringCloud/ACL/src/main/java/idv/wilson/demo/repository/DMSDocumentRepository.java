package idv.wilson.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import idv.wilson.demo.entity.DMSDocument;

public interface DMSDocumentRepository extends JpaRepository<DMSDocument, Integer> {

	@PostFilter("hasPermission(filterObject, 'read')")
	public List<DMSDocument> findAll();
}
