# 3. Interessentanalyse og Interessentmatrice

## 3.1 Identificerede interessenter

Vi kortlægger alle, der har interesse i eller indflydelse på projektet.

| ID | Interessent | Rolle | Interesse | Indflydelse |
|---|---|---|---|---|
| I1 | Klaus Petersen, CTO Alpha Solutions | Sponsor / kravstiller | Høj | Høj |
| I2 | Ulrik Falktoft, Product Owner | PO | Høj | Høj |
| I3 | Projektgruppen (studerende) | Udviklere | Høj | Høj |
| I4 | Vejleder (KEA) | Faglig sparring + censor | Middel | Høj |
| I5 | Censor | Bedømmer | Lav | Høj |
| I6 | Konsulenter hos Alpha Solutions | Slutbrugere | Høj | Lav |
| I7 | Alpha Solutions' kunder | Indirekte brugere (modtager estimater) | Middel | Lav |
| I8 | Alpha Solutions' partnere (ejere) | Beslutter om systemet bruges videre | Middel | Middel |
| I9 | Microsoft Azure / GitHub | Infrastruktur-leverandører | Lav | Middel |
| I10 | KEA medstuderende | Reviewere/usability test | Lav | Lav |

## 3.2 Interessentmatrice (Power/Interest grid)

Klassisk Mendelow-matrice — vi placerer interessenter ud fra deres indflydelse (power) og interesse, og bestemmer derefter håndteringsstrategi.

```
Indflydelse
    Høj  | KEEP SATISFIED      | MANAGE CLOSELY
         | I4 (Vejleder)       | I1 (CTO)
         | I5 (Censor)         | I2 (PO)
         | I9 (Azure/GitHub)   | I3 (Gruppe)
         |---------------------+---------------------
         | MONITOR             | KEEP INFORMED
         | I10 (Medstud.)      | I6 (Konsulenter)
         |                     | I7 (Kunder)
         |                     | I8 (Partnere)
    Lav  +---------------------+---------------------
                Lav                   Høj
                         Interesse
```

## 3.3 Detaljeret vurdering pr. interessent

### I1 — Klaus Petersen, CTO (MANAGE CLOSELY)

| | |
|---|---|
| **Fordele** | Er sponsor og kender visionen; har 30+ års erfaring i IT-projekter |
| **Ulemper** | Travl — kan være svær at få fat på til afklarende spørgsmål |
| **Tiltag** | Stiller alle væsentlige krav-spørgsmål til Sprint Review hvor han kan deltage; opsummerer aftaler skriftligt i GitHub Discussions |

### I2 — Ulrik Falktoft, Product Owner (MANAGE CLOSELY)

| | |
|---|---|
| **Fordele** | Er den daglige sparringspartner; prioriterer backloggen |
| **Ulemper** | Kan udskyde svar hvis flere studentgrupper trækker på ham samtidigt |
| **Tiltag** | Forberedt agenda til hvert Sprint Planning og Review; skriftlige spørgsmål mellem møder |

### I3 — Projektgruppen (MANAGE CLOSELY)

| | |
|---|---|
| **Fordele** | Fuld kontrol over tid og kvalitet |
| **Ulemper** | Begrænset erfaring; risiko for indre konflikter omkring scope |
| **Tiltag** | Sprint Retrospective for at fange spændinger tidligt; klare rolle-aftaler (Scrum Master skifter mellem sprints) |

### I4 — Vejleder (KEEP SATISFIED)

| | |
|---|---|
| **Fordele** | Faglig sparring og kan korrigere kurs tidligt |
| **Ulemper** | Vejledning er begrænset til faste tidspunkter |
| **Tiltag** | Forbered konkrete spørgsmål til hver vejledning; vis fremdrift via GitHub Projects |

### I5 — Censor (KEEP SATISFIED)

| | |
|---|---|
| **Fordele** | Vurderer slutprodukt fagligt |
| **Ulemper** | Kender ikke projektets historik — skal kunne læse rapporten "kold" |
| **Tiltag** | Sporbarhed i rapport: user stories → UML → kode → tests; klare diagrammer |

### I6 — Konsulenter hos Alpha Solutions (KEEP INFORMED)

| | |
|---|---|
| **Fordele** | De er de reelle slutbrugere — feedback giver realistiske krav |
| **Ulemper** | Vi har sjældent direkte adgang til dem |
| **Tiltag** | Brug PO som proxy; usability test simulerer deres scenarie |

### I7 — Alpha Solutions' kunder (KEEP INFORMED)

| | |
|---|---|
| **Fordele** | Værdiskabelse for kunderne legitimerer projektet |
| **Ulemper** | Ingen direkte kontakt |
| **Tiltag** | Tag højde for transparens-krav i UI (klare estimater, tydelige nedbrydninger) |

### I8 — Partnere i Alpha Solutions (KEEP INFORMED)

| | |
|---|---|
| **Fordele** | Beslutter om systemet bruges videre |
| **Ulemper** | Lavt engagement i studenter-projekt |
| **Tiltag** | Sikre at koden er produktionsklar (dokumentation, tests, CI/CD) — så de kan tage det videre |

### I9 — Azure / GitHub (KEEP SATISFIED)

| | |
|---|---|
| **Fordele** | Stabile platforme |
| **Ulemper** | Quota-begrænsninger i student-konti; tjenestenedbrud uden vores kontrol |
| **Tiltag** | Tidlig opsætning, alternative deployment-stier kendt |

### I10 — KEA medstuderende (MONITOR)

| | |
|---|---|
| **Fordele** | Kan udføre usability test gratis |
| **Ulemper** | Ikke domænekendere — feedback kan være generisk |
| **Tiltag** | Brug dem til usability test af konkrete user stories |

## 3.4 Sammenfatning

Den prioriterede gruppe er **I1, I2 og I3** — sponsor, PO og udviklere. Disse skal mødes ofte, og deres feedback skal omsættes til konkrete ændringer i backloggen. **I4 og I5** håndteres med høj kvalitet i rapport og slutprodukt. Resten holdes informeret eller monitoreres passivt.
