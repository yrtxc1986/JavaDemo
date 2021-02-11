package idv.wilson.demo.controller;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tasks")
@PreAuthorize("isAuthenticated()")
public class TaskController {

	@GetMapping
	public String listTasks() {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			log.info(authority.getAuthority());
		}

		return name;// "任务列表";
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public String newTasks() {
		return "创建了一个新的任务";
	}

	@PutMapping("/{taskId}")
	@PreAuthorize("hasRole('USER')")
	public String updateTasks(@PathVariable("taskId") Integer id) {
		return "更新了一下id为:" + id + "的任务";
	}

	@DeleteMapping("/{taskId}")
	@PreAuthorize("hasRole('TASK')")
	public String deleteTasks(@PathVariable("taskId") Integer id) {
		return "删除了id为:" + id + "的任务";
	}
}
