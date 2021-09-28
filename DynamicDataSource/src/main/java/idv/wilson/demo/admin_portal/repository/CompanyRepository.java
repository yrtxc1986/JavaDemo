package idv.wilson.demo.admin_portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import idv.wilson.demo.admin_portal.entity.PARADM_COMPANY;

@Repository
public interface CompanyRepository extends CrudRepository<PARADM_COMPANY, Integer> {

}
