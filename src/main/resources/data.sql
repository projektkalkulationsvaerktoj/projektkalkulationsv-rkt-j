-- ============================================
-- Test data for development
-- ============================================

INSERT INTO users (username, password, role) VALUES
  ('admin', 'admin123', 'ADMIN'),
  ('klaus', 'password', 'PROJECT_MANAGER'),
  ('user1', 'password', 'USER');

INSERT INTO projects (name, description, start_date, deadline, user_id) VALUES
  ('Webshop til Husqvarna', 'E-commerce platform med PIM integration', '2026-05-01', '2026-09-30', 2),
  ('CRM Implementation - Ahlsell', 'Salesforce CRM rollout', '2026-06-01', '2026-12-15', 2);

INSERT INTO sub_projects (name, description, deadline, project_id) VALUES
  ('Backend Infrastructure', 'Database og API udvikling', '2026-07-15', 1),
  ('Frontend Design', 'UI/UX design og implementering', '2026-08-30', 1),
  ('Salesforce Setup', 'Initial konfiguration', '2026-08-01', 2);

INSERT INTO tasks (name, description, estimated_hours, actual_hours, deadline, status, sub_project_id) VALUES
  ('Database design', 'Lav ER diagram og oprettelse', 16, 12, '2026-05-20', 'IN_PROGRESS', 1),
  ('REST API endpoints', 'CRUD endpoints for produkter', 40, 0, '2026-06-15', 'NOT_STARTED', 1),
  ('Wireframes', 'Lav wireframes for alle sider', 24, 24, '2026-06-01', 'DONE', 2),
  ('Implementering af checkout', 'Implementer checkout flow', 32, 0, '2026-08-15', 'NOT_STARTED', 2),
  ('Data migration', 'Migrer eksisterende kundedata', 24, 0, '2026-07-15', 'NOT_STARTED', 3);
