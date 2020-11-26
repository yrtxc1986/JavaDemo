package idv.wilson.demo.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idv.wilson.demo.jpa.entity.Job;
import idv.wilson.demo.jpa.repository.JobRepository;

@Service
public class JobDao {

	@Autowired
	JobRepository jobs;

	@Transactional
	public Job findById(Long id) {

		var ret = jobs.findById(id).get();
		if (ret.getReferences() != null) {
			System.out.println(ret.getReferences().size());
		}

		return ret;
	}
}
