# 2. Proaktiv Risikoanalyse

En proaktiv risikoanalyse består af tre dele: **identifikation** af risici, **vurdering** (sandsynlighed × konsekvens), og en **aktionsplan** med præventive tiltag samt en plan for håndtering hvis risikoen alligevel indtræffer.

## 2.1 Risikomatrice

Risici scores på en skala 1–5 for både sandsynlighed (S) og konsekvens (K). Risikoscore = S × K.

| Risikoscore | Niveau | Håndtering |
|---|---|---|
| 1–4 | Lav | Acceptér og overvåg |
| 5–14 | Medium | Mitigér aktivt |
| 15–25 | Høj | Kræver præventiv handling + plan B |

## 2.2 Identificerede risici

| ID | Risiko | Sandsynlighed | Konsekvens | Score | Niveau |
|---|---|---|---|---|---|
| R1 | Sygdom/fravær i lille gruppe | 4 | 4 | 16 | Høj |
| R2 | Forsinket Azure-deployment | 3 | 4 | 12 | Medium |
| R3 | MySQL-database mister data | 1 | 5 | 5 | Medium |
| R4 | Tests dækker ikke kritisk logik | 3 | 4 | 12 | Medium |
| R5 | User stories misforstået ift. PO | 3 | 3 | 9 | Medium |
| R6 | Scope-skred (for ambitiøse features før basis er færdig) | 4 | 4 | 16 | Høj |
| R7 | CI/CD pipeline går i stykker tæt på deadline | 2 | 4 | 8 | Medium |
| R8 | Sikkerhedsfejl (fx SQL injection via JDBC string-konkatenation) | 2 | 5 | 10 | Medium |
| R9 | Konflikter i Git (merge conflicts) | 4 | 2 | 8 | Medium |
| R10 | Manglende styling / dårlig usability ved aflevering | 3 | 3 | 9 | Medium |

## 2.3 Aktionsplan

For hver risiko defineres **præventive tiltag** (reducer sandsynlighed/konsekvens) og **kontingensplan** (hvad gør vi hvis det sker?).

### R1 — Sygdom/fravær (Høj)

- **Præventivt:** Par-programmering for vidensdeling, dokumentation af alle features i Git, ingen "knowledge silos"
- **Kontingens:** Reducér sprint-scope hvis nogen falder fra; opdatér PO i Sprint Review

### R2 — Forsinket Azure-deployment (Medium)

- **Præventivt:** Deploy "Hello World" til Azure i Sprint 0 før reel funktionalitet er bygget
- **Kontingens:** Hvis Azure svigter — fallback til lokal demo med MySQL i Docker

### R3 — Database mister data (Medium)

- **Præventivt:** Azure MySQL har automatisk daglig backup; tests kører kun mod H2 in-memory
- **Kontingens:** Re-seed fra `data.sql`

### R4 — Tests dækker ikke kritisk logik (Medium)

- **Præventivt:** Krav om mindst én test per service-metode; integration-tests for alle repository-CRUD-operationer
- **Kontingens:** Tilføj tests retroaktivt i et "test-katchup" sprint

### R5 — User stories misforstået (Medium)

- **Præventivt:** Skriv acceptkriterier sammen med PO; vis prototyper i Sprint Review
- **Kontingens:** Refactor user story og estimer den igen

### R6 — Scope-skred (Høj)

- **Præventivt:** Strikt prioritering: **alle grundlæggende krav skal være færdige før et eneste "For de ambitiøse"-krav startes**. Daily standup spørger eksplicit: "Arbejder du på basis-krav?"
- **Kontingens:** Skær ambitiøse features fra og dokumentér som "ikke nået" i implementeringsstatus

### R7 — CI/CD pipeline går i stykker (Medium)

- **Præventivt:** Pipeline opbygges trinvist i Sprint 0; vi pusher tit, så vi opdager fejl tidligt
- **Kontingens:** Manuel build + Azure-deployment via VS Code Azure-extension

### R8 — SQL injection / sikkerhed (Medium)

- **Præventivt:** ALDRIG string-konkatenation i SQL — kun parameteriserede queries via `JdbcTemplate`. Code review tjekker dette
- **Kontingens:** Hurtig hotfix-branch direkte til main

### R9 — Git merge conflicts (Medium)

- **Præventivt:** Små, hyppige pull requests; klar opdeling af pakker så folk ikke arbejder i samme filer
- **Kontingens:** Konflikt-løsning gennemføres i par for at sikre korrekt merge

### R10 — Dårlig usability (Medium)

- **Præventivt:** Heuristisk evaluering + usability test med eksterne brugere i god tid før aflevering
- **Kontingens:** Fokus på CSS i sidste sprint hvis nødvendigt

## 2.4 Risikomatrice (visuel)

```
Konsekvens
   5 |         | R3      | R8      |         |
   4 |         |         | R2,R4,R7| R1,R6   |
   3 |         |         | R5,R10  |         |
   2 |         | R9      |         |         |
   1 |         |         |         |         |
     +---------+---------+---------+---------+
        1         2         3         4        5  Sandsynlighed
```

R1 og R6 er de prioriterede risici — fravær og scope-skred. Disse adresseres aktivt fra Sprint 0.
