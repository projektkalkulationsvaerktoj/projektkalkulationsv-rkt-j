# 6. UX/UI Design

Vi følger faserne i Design Thinking: **Empathize, Define, Ideate, Prototype, Test**. Empathize- og Define-faserne er kort, fordi behov og scope allerede er afdækket i casebeskrivelsen fra Alpha Solutions.

## 6.1 Empathize (kort)

Slutbrugerne er konsulenter og projektledere hos Alpha Solutions, der dagligt skal estimere projekter til kunder. Fra casen ved vi at:

- De har 80+ kollegaer på tværs af tre lande
- De arbejder kommercielt — kunden vil kende prisen
- Estimering er "kvalificeret gætværk" — værktøjet skal støtte, ikke automatisere væk
- De er erfarne udviklere — værktøjet må ikke være langsommere end at skrive i et regneark

## 6.2 Define (kort)

**Problemstatement:** Alpha Solutions-konsulenter mangler et fælles, hurtigt værktøj til at nedbryde projekter og kommunikere realistiske estimater til kunder.

## 6.3 Ideate

### Brainstorm

Vi gennemførte en 30-minutters brainstorm-session med "How Might We"-spørgsmål:

- HMW hjælpe konsulenten med at huske at alle delopgaver er medtaget?
- HMW vise sammenhængen mellem opgave-timer og projekt-deadline?
- HMW gøre CO2-rapportering så enkelt at konsulenten faktisk gør det?

Ideerne blev klynge-sorteret (affinity diagram) og prioriteret efter:

1. **Værdi for slutbrugeren** — løser det et reelt problem?
2. **Implementationsomkostning** — kan vi nå det i to sprints?
3. **Sporbarhed til krav** — dækker det et basis-krav i casen?

De prioriterede ideer blev til de user stories i Product Backloggen (se afsnit 5).

### Sketch / Crazy 8s

Hver gruppemedlem tegnede 8 hurtige UI-skitser på 8 minutter for hovedscreen (projekt­detalje). Vi konvergerede til en design med:

- Top-info-card med projektets nøgletal
- Underliggende tabel over delprojekter
- Klart "+ Nyt Delprojekt"-CTA øverst i tabel-sektionen

### Brug af værktøjer

- **Miro** til affinity diagram og Crazy 8s
- **Figma** til medium-fidelity wireframes
- **GitHub Issues** til at omsætte ideer til user stories

## 6.4 Prototype (Wireframes)

Vi lavede medium-fidelity wireframes for hovedscreens:

1. **Login** — minimal form med branding
2. **Projekt­liste** — tabel-baseret oversigt
3. **Projekt­form** — oprettelse/redigering
4. **Projekt­detalje** — info-card + delprojekt-tabel
5. **Delprojekt­detalje** — info-card med CO2 + opgave-tabel
6. **Opgave­form** — felter med inline-validering

### Wireframe-skitse (tekstrepræsentation)

```
+-----------------------------------------------------+
| Alpha Solutions - Projekter         [Log ud]        |
+-----------------------------------------------------+
| [+ Nyt Projekt]                                     |
|                                                     |
| Navn      | Beskrivelse | Start  | Deadline | Timer |
|-----------|-------------|--------|----------|-------|
| Webshop   | E-commerce  | 01-05  | 30-09    | 112   |
| CRM Roll. | Salesforce  | 01-06  | 15-12    | 80    |
+-----------------------------------------------------+
```

Den faktiske implementering matcher denne struktur 1:1 — se `templates/projects/list.html`.

## 6.5 Test (Usability Test)

### Testplan

| Element | Beskrivelse |
|---|---|
| **Deltagere** | 3 medstuderende (ikke fra projektgruppen) |
| **Format** | Moderet, think-aloud, 20 min per deltager |
| **Setup** | Live applikation kørende lokalt, observatør tager noter |
| **Måling** | Task completion rate, antal fejl, subjektivt SUS-score |

### Test-scenarier (opgaver)

1. **Log ind** med "admin" / "admin123"
2. **Opret et projekt** kaldet "Test Projekt" med startdato i dag og deadline om 30 dage
3. **Tilføj et delprojekt** under projektet, navngivet "Backend"
4. **Tilføj to opgaver** under delprojektet med 8 og 16 estimerede timer
5. **Find** den totale estimerede tid for projektet
6. **Find** CO2-aftrykket for delprojektet

### Resultater (eksempel — uddybes når test gennemføres)

| Scenarie | Completion rate | Gennemsnits­tid | Bemærkninger |
|---|---|---|---|
| 1 Log ind | 3/3 | 8s | Ingen problemer |
| 2 Opret projekt | 3/3 | 45s | 1 deltager kiggede efter "Gem"-knap nederst, fandt den hurtigt |
| 3 Delprojekt | 2/3 | 60s | 1 deltager forsøgte at klikke på projektnavnet, ikke "+ Nyt Delprojekt" |
| 4 Opgaver | 3/3 | 90s | Forvirring om "estimerede" vs "brugte" timer |
| 5 Find total | 3/3 | 15s | Tydeligt placeret |
| 6 Find CO2 | 1/3 | 45s | 2 deltagere ledte på projekt-niveau først |

### Identificerede forbedringer

- T1: "+ Nyt Delprojekt"-knappen kunne være større/grønnere
- T2: Tooltip eller hjælpetekst ved "Estimerede" og "Brugte" timer
- T3: Overvej at vise CO2-aftryk også på projekt­niveau (aggregeret)

Disse rettelser noteres som user stories til næste iteration (ikke en del af MVP).

## 6.6 Heuristisk evaluering (Nielsens 10 heuristikker)

| # | Heuristik | Vurdering | Bemærkninger |
|---|---|---|---|
| 1 | Visibility of system status | OK | Login-fejl viser tydelig besked; status-badges på opgaver |
| 2 | Match between system and the real world | OK | Dansk sprog, kendte begreber ("Projekt", "Deadline") |
| 3 | User control and freedom | DELVIS | Mangler "Undo" — sletning er permanent (kun bekræftelses-dialog) |
| 4 | Consistency and standards | OK | Samme knapstil, samme tabel-layout overalt |
| 5 | Error prevention | OK | Server-side validering kaster forklarende fejl; bekræftelse før sletning |
| 6 | Recognition rather than recall | OK | Brugeren ser projekter på listen — behøver ikke huske navne |
| 7 | Flexibility and efficiency of use | DELVIS | Ingen keyboard shortcuts (kan tilføjes i v2) |
| 8 | Aesthetic and minimalist design | OK | Rent layout uden visuel støj |
| 9 | Help users recognize, diagnose, recover from errors | OK | Fejlbeskeder er konkrete ("Deadline kan ikke være før startdato") |
| 10 | Help and documentation | DELVIS | Mangler i UI; dokumentation findes i README.md |

## 6.7 Shneidermans Eight Golden Rules

| # | Regel | Anvendelse i vores UI |
|---|---|---|
| 1 | Strive for consistency | Samme navigations­mønster, knap-farver, tabel-layout |
| 2 | Seek universal usability | Dansk sprog; kontrast-forhold over WCAG AA |
| 3 | Offer informative feedback | Fejlbeskeder, succes-redirect, status-badges |
| 4 | Design dialogs to yield closure | "Gem" + redirect bekræfter at handlingen er udført |
| 5 | Prevent errors | Required-felter, datovalidering, bekræftelses-dialog ved slet |
| 6 | Permit easy reversal of actions | DELVIS — sletning er ikke reversibel (forbedringspunkt) |
| 7 | Keep users in control | Bruger kan altid annullere via "Tilbage"-link |
| 8 | Reduce short-term memory load | Lister og oversigter forhindrer at brugeren skal huske projekter |

## 6.8 Bilag

- **Bilag A — Usability test rapport** (placeres i `/docs/bilag/usability_test.md` efter testen er gennemført)
- **Bilag B — Wireframes** (eksporteres fra Figma til `/docs/bilag/wireframes/`)
- **Bilag C — Heuristic evaluation workbook** (`/docs/bilag/heuristic_workbook.md`)
