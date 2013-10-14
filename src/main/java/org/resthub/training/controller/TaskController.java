package org.resthub.training.controller;

import org.resthub.training.model.Task;
import org.resthub.training.service.TaskService;
import org.resthub.web.controller.ServiceBasedRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;

@Controller
@RequestMapping(value = "/api/task")
public class TaskController extends ServiceBasedRestController<Task, Long, TaskService> {

    @Inject
    @Named("taskService")
    @Override
    public void setService(TaskService service) {
        this.service = service;
    }

    public Long getIdFromResource(Task resource) {
        return resource.getId();
    }

    @RequestMapping(value = "name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Task findByName(@PathVariable String name) {
        return this.service.findByName(name);
    }

    @RequestMapping(value = "{taskId}/user/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public Task affectTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        return this.service.affectTaskToUser(taskId, userId);
    }
}
