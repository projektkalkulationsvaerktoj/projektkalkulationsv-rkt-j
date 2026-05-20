# 7. Domænemodel og Database

## 7.1 Domænemodel (problemdomænet)

Vi modellerer de centrale begreber fra Alpha Solutions case-beskrivelsen: **projekter, delprojekter, opgaver, tidsforbrug og deadlines** — plus brugere som ejer projekterne.

```
+----------------+         1   *  +------------------+
|     User       |-----------------|     Project      |
|----------------|                 |------------------|
| id             |                 | id               |
| username       |                 | name             |
| password       |                 | description      |
| role           |                 | startDate        |
+----------------+                 | deadline         |
                                   | userId (FK)      |
                                   +------------------+
                                          | 1
                                          |
                                          | *
                                   +------------------+
                                   |   SubProject     |
                                   |------------------|
                                   | id               |
                                   | name             |
                                   | description      |
                                   | deadline         |
                                   | projectId (FK)   |
                                   +------------------+
                                          | 1
                                          |
                                          | *
                                   +------------------+
                                   |      Task        |
                                   |------------------|
                                   | id               |
                                   | name             |
                                   | description      |
                                   | estimatedHours   |
                                   | actualHours      |
                                   | deadline         |
                                   | status           |
                                   | subProjectId (FK)|
                                   +------------------+
```

**Forretningsregler:**

- En **bruger** kan eje 0..n **projekter**
- Et **projekt** kan have 0..n **delprojekter**
- Et **delprojekt** kan have 0..n **opgaver**
- Sletning af et projekt sletter alle dets delprojekter og opgaver (kaskade)
- En opgaves estimerede timer summeres op til delprojekt-niveau og videre til projekt-niveau

## 7.2 ER-diagram (databasen)

```
users (PK: id)
   |
   | 1
   |
   | * (user_id FK, ON DELETE CASCADE)
   v
projects (PK: id)
   |
   | 1
   |
   | * (project_id FK, ON DELETE CASCADE)
   v
sub_projects (PK: id)
   |
   | 1
   |
   | * (sub_project_id FK, ON DELETE CASCADE)
   v
tasks (PK: id)
```

### Tabel-detaljer

#### users

| Kolonne | Type | Constraint |
|---|---|---|
| id | INT | PK, AUTO_INCREMENT |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| password | VARCHAR(100) | NOT NULL |
| role | VARCHAR(20) | NOT NULL, DEFAULT 'USER' |

#### projects

| Kolonne | Type | Constraint |
|---|---|---|
| id | INT | PK, AUTO_INCREMENT |
| name | VARCHAR(100) | NOT NULL |
| description | TEXT | |
| start_date | DATE | |
| deadline | DATE | |
| user_id | INT | NOT NULL, FK users(id), ON DELETE CASCADE |

#### sub_projects

| Kolonne | Type | Constraint |
|---|---|---|
| id | INT | PK, AUTO_INCREMENT |
| name | VARCHAR(100) | NOT NULL |
| description | TEXT | |
| deadline | DATE | |
| project_id | INT | NOT NULL, FK projects(id), ON DELETE CASCADE |

#### tasks

| Kolonne | Type | Constraint |
|---|---|---|
| id | INT | PK, AUTO_INCREMENT |
| name | VARCHAR(100) | NOT NULL |
| description | TEXT | |
| estimated_hours | DOUBLE | NOT NULL, DEFAULT 0 |
| actual_hours | DOUBLE | NOT NULL, DEFAULT 0 |
| deadline | DATE | |
| status | VARCHAR(20) | NOT NULL, DEFAULT 'NOT_STARTED' |
| sub_project_id | INT | NOT NULL, FK sub_projects(id), ON DELETE CASCADE |

## 7.3 Normalisering

Alle tabeller er på **3. normalform**:

- **1NF** — Alle attributter er atomare (ingen lister/JSON i felter)
- **2NF** — Alle ikke-nøgle-attributter er fuldt afhængige af PK (PK er enkelt-attribut, så automatisk OK)
- **3NF** — Ingen transitive afhængigheder (fx er `start_date` afhængig direkte af `projects.id`, ikke via en anden ikke-nøgle-kolonne)

**Bevidste designvalg:**

- `status` er gemt som VARCHAR i stedet for separat tabel. Dette er teknisk en denormalisering, men:
  - Antal statusser er fastlagt og lille (3)
  - Statusser er en del af applikationens forretningslogik (`TaskService.STATUS_*` konstanter)
  - En separat tabel ville give ekstra JOIN ved ofte forespurgte queries uden tilsvarende gevinst
- `role` på `users` er ligeledes VARCHAR — samme begrundelse

## 7.4 Designovervejelser

### Valg af fremmednøgler og constraints

- **ON DELETE CASCADE** på alle FK'er — sikrer at sletning af et projekt fjerner alle dets data. Alternativet (ON DELETE RESTRICT) ville kræve manuel oprydning. CASCADE er valgt fordi del-projekter og opgaver giver ingen mening uden deres parent.
- **NOT NULL** på FK'er — vi tillader ikke "forældreløse" rækker.
- **UNIQUE** på `username` — to brugere kan ikke have samme login.

### Surrogatnøgler vs naturlige nøgler

Vi bruger **surrogatnøgler** (auto-genereret `id`) overalt. Begrundelse:

- Forretnings­data ændrer sig (projektnavn kan ændres), men id'et er stabilt
- Naturlige nøgler (fx projektnavn) ville være ineffektive som FK
- Surrogatnøgler er rene heltal — hurtige i indeks

### Indekser

MySQL opretter automatisk indekser på PK og FK. For nuværende performance er det tilstrækkeligt — `EXPLAIN` på de hyppige queries (`SELECT * FROM projects WHERE user_id = ?`) viser at FK-indekset bruges.

## 7.5 SQL-scripts

Scripts ligger i `src/main/resources/`:

- **`schema.sql`** — opretter alle fire tabeller med constraints
- **`data.sql`** — indsætter testdata (3 brugere, 2 projekter, 3 delprojekter, 5 opgaver)

Disse køres automatisk af Spring Boot ved opstart i dev-profil (`spring.sql.init.mode=always`). I prod-profil er init slået fra (`spring.sql.init.mode=never`), så vi ikke overskriver produktionsdata.

## 7.6 Sporbarhed

Hver tabel mapper til en domæneklasse, en repository-klasse og en service-klasse:

| Tabel | Java-klasse | Repository | Service |
|---|---|---|---|
| users | `User` | `UserRepository` | `UserService` |
| projects | `Project` | `ProjectRepository` | `ProjectService` |
| sub_projects | `SubProject` | `SubProjectRepository` | `SubProjectService` |
| tasks | `Task` | `TaskRepository` | `TaskService` |
