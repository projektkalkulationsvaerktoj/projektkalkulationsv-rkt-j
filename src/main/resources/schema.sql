-- ============================================
-- Projektkalkulationsvaerktoej - Database Schema
-- Alpha Solutions Case
-- ============================================

DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS sub_projects;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    deadline DATE,
    user_id INT NOT NULL,
    CONSTRAINT fk_project_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE sub_projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    deadline DATE,
    project_id INT NOT NULL,
    CONSTRAINT fk_subproject_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);

CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    estimated_hours DOUBLE NOT NULL DEFAULT 0,
    actual_hours DOUBLE NOT NULL DEFAULT 0,
    deadline DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'NOT_STARTED',
    sub_project_id INT NOT NULL,
    CONSTRAINT fk_task_subproject FOREIGN KEY (sub_project_id) REFERENCES sub_projects(id) ON DELETE CASCADE
);
