package com.pranay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pranay.Repository.TodoRespository;
import com.pranay.model.Todo;

@Controller
public class TodoController {
	
	 @Autowired
	    private TodoRespository todoRepository;


	    @GetMapping("/")
	    public String viewTasks(Model model) {
	        model.addAttribute("tasks", todoRepository.findAll());
	        return "todo-list"; 
	    }

	    @PostMapping("/add")
	    public String addTask(@RequestParam("taskName") String taskName) {
	        if (!taskName.trim().isEmpty()) {
	            Todo newTask = new Todo();
	            newTask.setName(taskName);
	            newTask.setCompleted(false); 
	            todoRepository.save(newTask);
	        }
	        return "redirect:/";
	    }


	    @PostMapping("/toggle")
	    public String toggleTask(@RequestParam("taskId") int taskId) {
	        todoRepository.findById(taskId).ifPresent(task -> {
	            task.setCompleted(!task.isCompleted());
	            todoRepository.save(task);
	        });
	        return "redirect:/"; // Redirect to the task list page
	    }

	    @PostMapping("/delete")
	    public String deleteTask(@RequestParam("taskId") int taskId) {
	        todoRepository.deleteById(taskId);
	        return "redirect:/"; // Redirect to the task list page
	    }

	

}
