package idv.wilson.demo.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import idv.wilson.demo.jpa.entity.Job;

public interface JobRepository extends CrudRepository<Job, Long> {

}
