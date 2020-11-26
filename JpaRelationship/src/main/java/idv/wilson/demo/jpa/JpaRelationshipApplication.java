package idv.wilson.demo.jpa;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import idv.wilson.demo.jpa.dao.JobDao;
import idv.wilson.demo.jpa.entity.Context;
import idv.wilson.demo.jpa.entity.Job;
import idv.wilson.demo.jpa.repository.JobRepository;

@SpringBootApplication
public class JpaRelationshipApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(JpaRelationshipApplication.class, args);
		ctx.getBean(JpaRelationshipApplication.class).run();
	}

	@Bean
	public Clock systemClock() {
		return Clock.systemDefaultZone();
	}

	@Autowired
	JobRepository jobs;
	@Autowired
	Clock clock;
	@Autowired
	JobDao dao;

	public void printRecord(long id) {

		var checking = dao.findById(1l);

		System.out.println(checking.getMaster().getFileName());
		if (checking.getOutput() != null) {
			System.out.println(checking.getOutput().getFileName());
		}

		if (checking.getReferences() != null) {
			checking.getReferences().forEach(p -> {
				System.out.println(p.getFileName());
			});
		}

	}

	public void run() {
		jobs.count();

		var job1 = Job.builder().createDate(LocalDateTime.now(clock))
				.master(Context.builder().fileName("Testing.dwg").build()).build();

		job1 = jobs.save(job1);
		printRecord(1);

		job1.setOutput(Context.builder().fileName("Testing.vsf").build());

		job1 = jobs.save(job1);
		printRecord(1);

		job1.addReference(Context.builder().fileName("ref1.dwg").build());
		job1.addReference(Context.builder().fileName("ref2.dwg").build());

		job1 = jobs.save(job1);
		printRecord(1);

	}

}
