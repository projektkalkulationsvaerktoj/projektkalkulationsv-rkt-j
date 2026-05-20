# 5. Product Backlog og User Stories

## 5.1 Product Backlog

Product Backloggen er prioriteret efter MoSCoW (**Must, Should, Could, Won't**) og knytter sig direkte til kravene fra Alpha Solutions case-beskrivelsen.

| ID | User Story | Prioritet | Sprint | Status |
|---|---|---|---|---|
| US-01 | Log ind | Must | 1 | Done |
| US-02 | Opret projekt | Must | 1 | Done |
| US-03 | Se mine projekter | Must | 1 | Done |
| US-04 | Rediger projekt | Must | 1 | Done |
| US-05 | Slet projekt | Must | 1 | Done |
| US-06 | Opret delprojekt | Must | 1 | Done |
| US-07 | Opret opgave med estimat | Must | 1 | Done |
| US-08 | Se total estimerede timer på projekt | Must | 1 | Done |
| US-09 | Se total estimerede timer på delprojekt | Must | 1 | Done |
| US-10 | Beregn forventet slutdato baseret på arbejdsdage | Should | 2 | Done |
| US-11 | Vis CO2-aftryk per delprojekt | Should | 2 | Done |
| US-12 | Status på opgaver (NOT_STARTED / IN_PROGRESS / DONE) | Should | 2 | Done |
| US-13 | Validering af bruger­input med fejlbeskeder | Should | 2 | Done |
| US-14 | CI/CD pipeline med automatisk deployment | Must | 0 | Done |
| US-15 | Gantt-diagram visualisering | Could | — | Not started (teknisk gæld) |
| US-16 | Kompetencer / ressourcetyper | Could | — | Not started |
| US-17 | Daglig belastning per ressource | Could | — | Not started |
| US-18 | Eksport til Excel | Won't | — | Bevidst skåret fra |

## 5.2 Detaljerede user stories

Hver user story følger formatet:

> Som [rolle] vil jeg [funktionalitet] således at [forretningsværdi].

Acceptkriterier er konkrete, testbare betingelser.

### US-01 Log ind

> **Som** en konsulent hos Alpha Solutions  
> **vil jeg** kunne logge ind med brugernavn og adgangskode  
> **således at** kun autoriserede personer kan se mine projekter.

**Acceptkriterier:**

- AC1: På `/login` vises et formular med brugernavn og adgangskode
- AC2: Korrekte credentials sender mig til `/projects`
- AC3: Forkerte credentials viser en fejlbesked uden at afsløre om brugernavnet findes
- AC4: Adgang til `/projects` uden login redirecter til `/login`
- AC5: `/logout` invaliderer sessionen

**Sporbarhed:** `LoginController.java`, `UserService.authenticate()`, `UserServiceTest.authenticate_*`.

---

### US-02 Opret projekt

> **Som** projektleder  
> **vil jeg** kunne oprette et nyt projekt med navn, beskrivelse, startdato og deadline  
> **således at** jeg kan begynde at nedbryde det.

**Acceptkriterier:**

- AC1: Knappen "+ Nyt Projekt" tager mig til `/projects/new`
- AC2: Formularen kræver et navn
- AC3: Hvis deadline er før startdato, vises fejl "Deadline kan ikke være før startdato"
- AC4: Ved gem oprettes projektet med mig som ejer
- AC5: Jeg redirecte­es til projektlisten med mit nye projekt synligt

**Sporbarhed:** `ProjectController.createProject()`, `ProjectService.createProject()`, `ProjectRepository.save()`, `ProjectServiceTest.createProject_*`.

---

### US-03 Se mine projekter

> **Som** projektleder  
> **vil jeg** se en liste over mine projekter med navn, deadline og totale estimerede timer  
> **således at** jeg får hurtigt overblik.

**Acceptkriterier:**

- AC1: `/projects` viser kun projekter ejet af mig
- AC2: Hver række viser navn, beskrivelse, startdato, deadline og estimerede timer
- AC3: Klik på navn åbner projekt­detaljen
- AC4: Tom liste viser "Ingen projekter endnu"

---

### US-04 Rediger projekt

> **Som** projektleder vil jeg kunne redigere et eksisterende projekt så jeg kan justere scope og datoer.

**Acceptkriterier:**

- AC1: "Rediger"-knap åbner formular med eksisterende data udfyldt
- AC2: Samme validering som ved oprettelse
- AC3: Gem opdaterer projektet uden at ændre id eller ejer

---

### US-05 Slet projekt

> **Som** projektleder vil jeg kunne slette et projekt så jeg fjerner ikke-relevante kalkulationer.

**Acceptkriterier:**

- AC1: "Slet"-knap kræver bekræftelse via dialog
- AC2: Sletning fjerner projekt samt alle del­projekter og opgaver (CASCADE)
- AC3: Efter sletning vises projektlisten uden det slettede projekt

---

### US-06 Opret delprojekt

> **Som** projektleder vil jeg kunne oprette et delprojekt under et projekt så jeg kan strukturere arbejdet.

**Acceptkriterier:**

- AC1: Fra projekt­detaljen kan jeg klikke "+ Nyt Delprojekt"
- AC2: Delprojektet er tilknyttet det rigtige projekt (ikke et andet)
- AC3: Navn er påkrævet
- AC4: Delprojekt vises på projekt­detalje­siden efter oprettelse

---

### US-07 Opret opgave med estimat

> **Som** projektleder vil jeg kunne oprette en opgave under et delprojekt med navn, estimerede timer, deadline og status.

**Acceptkriterier:**

- AC1: Estimerede timer kan ikke være negative
- AC2: Status defaulter til NOT_STARTED hvis ikke valgt
- AC3: Opgave vises på delprojekt­detalje­siden efter oprettelse
- AC4: Opgavens estimerede timer indgår automatisk i delprojektets og projektets totaler

**Sporbarhed:** `TaskController.create()`, `TaskService.createTask()`, `TaskServiceTest.createTask_*`.

---

### US-08 / US-09 Total estimerede timer

> **Som** projektleder vil jeg se total estimerede timer på et delprojekt og projekt så jeg kan kommunikere prisen til kunden.

**Acceptkriterier:**

- AC1: Total for delprojekt = sum af `estimated_hours` for alle dets opgaver
- AC2: Total for projekt = sum af `estimated_hours` på tværs af alle dets delprojekter
- AC3: Beregnes på serverside (SQL `SUM`), ikke i UI

**Sporbarhed:** `ProjectRepository.sumEstimatedHours()`, `SubProjectRepository.sumEstimatedHours()`, `ProjectRepositoryIntegrationTest.sumEstimatedHours_returnsCorrectTotal`.

---

### US-10 Beregn forventet slutdato

> **Som** projektleder vil jeg se hvornår et projekt forventeligt er færdigt baseret på antal arbejdsdage, så jeg kan vurdere om deadline er realistisk.

**Acceptkriterier:**

- AC1: Beregning bruger 8 timer per arbejdsdag (mandag-fredag)
- AC2: Weekender springes over
- AC3: Hvis startdato mangler, viser systemet "Ikke beregnet"
- AC4: Hvis projektet har 40 timer estimat og starter en mandag, er forventet slutdato næste mandag

**Sporbarhed:** `ProjectService.calculateEstimatedEndDate()`, `ProjectServiceTest.calculateEstimatedEndDate_returnsCorrectDate`.

---

### US-11 CO2-aftryk

> **Som** ESG-ansvarlig vil jeg se det estimerede CO2-aftryk for et delprojekt så jeg kan rapportere det videre.

**Acceptkriterier:**

- AC1: Beregning: 0.5 kg CO2e per time arbejde (laptop + kontoropvarmning)
- AC2: Vises på delprojekt­detalje­siden formateret med to decimaler
- AC3: Beregningen er forklaret i UI (tooltip eller hjælpetekst)

**Sporbarhed:** `TaskService.calculateCo2Footprint()`, `TaskServiceTest.calculateCo2Footprint_*`.

---

### US-12 Opgavestatus

> **Som** projektleder vil jeg kunne sætte status på en opgave til NOT_STARTED, IN_PROGRESS eller DONE så jeg kan følge fremdrift.

**Acceptkriterier:**

- AC1: Drop-down med tre valg i opgave­formularen
- AC2: Status vises som farvet badge i opgaveoversigten (grå / gul / grøn)

---

### US-13 Input-validering

> **Som** bruger vil jeg have klare fejlbeskeder så jeg ved hvad jeg har gjort forkert.

**Acceptkriterier:**

- AC1: Tomt projektnavn → "Projektnavn er påkrævet"
- AC2: Negative timer → "Estimerede timer kan ikke være negative"
- AC3: Fejl vises i rød ramme over formularen, og indtastede værdier bibeholdes

---

### US-14 CI/CD pipeline

> **Som** udvikler vil jeg have automatiseret build, test og deployment så vi ikke laver manuelle fejl.

**Acceptkriterier:**

- AC1: Push til ethver branch trigger build + tests
- AC2: Pull request mod main viser status (grøn/rød)
- AC3: Merge til main deployer automatisk til Azure
- AC4: Failing tests blokerer merge

**Sporbarhed:** `.github/workflows/ci-cd.yml`.

## 5.3 Sprint Backlogs

### Sprint 0 (infrastruktur — uge 0)

| Story | Tasks |
|---|---|
| US-14 CI/CD | Opret repo, opsæt GitHub Actions, lav "Hello Spring Boot" deploy til Azure, opret MySQL i Azure |
| Setup | Maven projekt, pakke­struktur, schema.sql |

### Sprint 1 (basis-CRUD — uge 1–2)

| Story | Tasks |
|---|---|
| US-01 Login | Login form, UserService, SessionAttribute, integration test |
| US-02 Opret projekt | Form, controller, service-validering, JdbcTemplate-INSERT |
| US-03 Se projekter | Liste-view, repository.findByUserId |
| US-04 Rediger | Form, UPDATE |
| US-05 Slet | DELETE med CASCADE |
| US-06 Delprojekt | Samme stack |
| US-07 Opgave | Samme stack |
| US-08/09 Summer | SQL SUM-query |

### Sprint 2 (beregninger og polering — uge 4–5)

| Story | Tasks |
|---|---|
| US-10 Slutdato | calculateWorkDays, calculateEstimatedEndDate, unit tests |
| US-11 CO2 | calculateCo2Footprint, UI |
| US-12 Status | Drop-down, status-badges, CSS |
| US-13 Validering | Try/catch i controllers, fejl-flash |
| Usability test | Test­plan, gennemførelse, rapport |
| Heuristisk evaluering | Workbook-skema |

## 5.4 GitHub Projects

Vi anvender et GitHub Projects-board med kolonner: **Backlog → In Sprint → In Progress → In Review → Done**. Hver user story er et issue med acceptkriterier som checklist, og hver task er enten et sub-issue eller en checkbox.
