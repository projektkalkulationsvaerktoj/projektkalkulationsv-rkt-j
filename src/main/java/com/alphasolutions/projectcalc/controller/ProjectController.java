package com.alphasolutions.projectcalc.controller;

import com.alphasolutions.projectcalc.model.Project;
import com.alphasolutions.projectcalc.model.SubProject;
import com.alphasolutions.projectcalc.model.User;
import com.alphasolutions.projectcalc.service.ProjectService;
import com.alphasolutions.projectcalc.service.SubProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final SubProjectService subProjectService;

    public ProjectController(ProjectService projectService, SubProjectService subProjectService) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
    }

    @GetMapping
    public String listProjects(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Project> projects = projectService.getProjectsByUserId(user.getId());
        model.addAttribute("projects", projects);
        model.addAttribute("user", user);
        return "projects/list";
    }

    @GetMapping("/new")
    public String newProjectForm(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        model.addAttribute("project", new Project());
        return "projects/form";
    }

    @PostMapping("/new")
    public String createProject(@ModelAttribute Project project,
                                HttpSession session,
                                Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        try {
            project.setUserId(user.getId());
            projectService.createProject(project);
            return "redirect:/projects";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("project", project);
            return "projects/form";
        }
    }

    @GetMapping("/{id}")
    public String viewProject(@PathVariable int id,
                              HttpSession session,
                              Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        Optional<Project> projectOpt = projectService.getProjectById(id);
        if (projectOpt.isEmpty()) return "redirect:/projects";

        List<SubProject> subProjects = subProjectService.getSubProjectsByProjectId(id);
        model.addAttribute("project", projectOpt.get());
        model.addAttribute("subProjects", subProjects);
        model.addAttribute("workDays", projectService.calculateWorkDays(projectOpt.get()));
        model.addAttribute("estimatedEnd",
                projectService.calculateEstimatedEndDate(projectOpt.get(), 8.0));
        return "projects/detail";
    }

    @GetMapping("/{id}/edit")
    public String editProjectForm(@PathVariable int id,
                                  HttpSession session,
                                  Model model) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        Optional<Project> projectOpt = projectService.getProjectById(id);
        if (projectOpt.isEmpty()) return "redirect:/projects";
        model.addAttribute("project", projectOpt.get());
        return "projects/form";
    }

    @PostMapping("/{id}/edit")
    public String updateProject(@PathVariable int id,
                                @ModelAttribute Project project,
                                HttpSession session,
                                Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        try {
            project.setId(id);
            project.setUserId(user.getId());
            projectService.updateProject(project);
            return "redirect:/projects/" + id;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("project", project);
            return "projects/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}
