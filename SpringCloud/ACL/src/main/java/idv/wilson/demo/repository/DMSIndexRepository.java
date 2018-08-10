package idv.wilson.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;

import idv.wilson.demo.entity.DMSIndex;

public interface DMSIndexRepository extends JpaRepository<DMSIndex, Integer> {

	@PostFilter("hasPermission(filterObject.id, 'idv.wilson.demo.entity.DMSDocument', 'read')")
	public List<DMSIndex> findAll();
}
