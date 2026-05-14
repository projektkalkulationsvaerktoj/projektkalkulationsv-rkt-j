package com.alphasolutions.projectcalc.model;

import java.time.LocalDate;

public class Project {

    private int id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate deadline;
    private int userId;
    // Calculated field (sum of all task hours)
    private double totalHours;

    public Project() {}

    public Project(int id, String name, String description, LocalDate startDate, LocalDate deadline, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.deadline = deadline;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotalHours() { return totalHours; }
    public void setTotalHours(double totalHours) { this.totalHours = totalHours; }
}
