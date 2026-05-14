package com.alphasolutions.projectcalc.model;

import java.time.LocalDate;

public class Task {

    private int id;
    private String name;
    private String description;
    private double estimatedHours;
    private double actualHours;
    private LocalDate deadline;
    private String status; // NOT_STARTED, IN_PROGRESS, DONE
    private int subProjectId;

    public Task() {}

    public Task(int id, String name, String description, double estimatedHours,
                double actualHours, LocalDate deadline, String status, int subProjectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.deadline = deadline;
        this.status = status;
        this.subProjectId = subProjectId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(double estimatedHours) { this.estimatedHours = estimatedHours; }

    public double getActualHours() { return actualHours; }
    public void setActualHours(double actualHours) { this.actualHours = actualHours; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getSubProjectId() { return subProjectId; }
    public void setSubProjectId(int subProjectId) { this.subProjectId = subProjectId; }
}
