# 4. Kommunikationsplan

Kommunikationsplanen sikrer at de rigtige budskaber når de rigtige interessenter på det rigtige tidspunkt via den rigtige kanal.

## 4.1 Kommunikationsmatrice

| Interessent­gruppe | Formål | Budskab | Kanal | Frekvens / Tidsplan | Ansvarlig | Evaluering |
|---|---|---|---|---|---|---|
| **CTO (Klaus Petersen)** | Forventnings­afstemning på højt niveau | Vision, scope, leverancer | Email + Sprint Review | Ved sprint-start og -slut | Scrum Master | Skriftlig opsummering bekræftes af CTO |
| **Product Owner (Ulrik Falktoft)** | Prioritering og afklaring af user stories | Backlog­ændringer, blokeringer, demo | Sprint Planning + Sprint Review + ad-hoc GitHub Discussions | Hvert sprint + løbende | Scrum Master | Sprint Review action items dokumenteres i GitHub Issues |
| **Projekt­gruppe (internt)** | Daglig koordination | Hvad jeg lavede i går, hvad jeg laver i dag, blokeringer | Daily Standup (15 min, online/fysisk) | Hver arbejdsdag kl. 09:15 | Roterende — Scrum Master faciliterer | Retrospective hver sprint-slut |
| **Projekt­gruppe (sprint-niveau)** | Reflektion og forbedring | Hvad gik godt, hvad gik skidt, action items | Sprint Retrospective | Sidste dag i hvert sprint | Scrum Master | Action items reviewes næste retrospective |
| **Vejleder (KEA)** | Faglig sparring | Status, faglige udfordringer, kvalitetskontrol | Onsdags check-in (online) + ad-hoc møder | Hver onsdag i projekt­perioden | Scrum Master | Vejleder konfirmerer kursen |
| **Censor** | Bedømmelses­grundlag | Færdig rapport + GitHub-link | Rapport via Wiseflow | 27. maj 2026 (aflevering) | Hele gruppen | Karakter |
| **Slutbrugere (Alpha-konsulenter)** | Brugbarhed | Funktionalitet, screen­shots, demo­video | Sprint Review demo + usability test | Sprint Review + én gang før aflevering | Den studerende der ejer feature­en | Usability test resultater |
| **Medstuderende** | Usability test­deltagere | Test­scenarier, opgaver | Mundtligt + opgaveark | Én gang i Sprint 2 | Den studerende ansvarlig for UX | Test­rapport som bilag |

## 4.2 Kommunikationskanaler

| Kanal | Anvendt til | Anvendt af |
|---|---|---|
| **GitHub Projects** | Sprint Backlog, Product Backlog, Kanban-board | Gruppe + PO |
| **GitHub Issues** | Bug-rapporter, user story-acceptkriterier, beslutninger | Gruppe |
| **GitHub Discussions** | Asynkron diskussion med PO | Gruppe + PO |
| **GitHub Actions** | CI/CD status, build/test resultater | Gruppe (automatisk) |
| **Email** | Formel kommunikation med CTO, vejleder | Scrum Master |
| **Discord / Teams** | Daglig chat, hurtige spørgsmål, screen-share | Hele gruppen |
| **Online møder (Teams/Zoom)** | Sprint Planning, Review, Retrospective | Gruppe + PO/vejleder |
| **VS Code Live Share** | Par-programmering | Gruppen internt |

## 4.3 Tidsplan (eksempel for to sprints)

| Uge | Sprint | Aktivitet | Kommunikation |
|---|---|---|---|
| 0 | Sprint 0 | Infrastruktur op (CI/CD, Azure, MySQL) | Kick-off med PO |
| 1–2 | Sprint 1 | Basis-CRUD: projekter, delprojekter, opgaver | Daily standup, midtvejs vejleder, Sprint Review med PO |
| 3 | Mellem | Retrospective, planlægning Sprint 2 | Intern Retrospective + planlægning med PO |
| 4–5 | Sprint 2 | Beregninger (arbejdsdage, CO2), usability test | Daily standup, usability test med medstuderende, Sprint Review |
| 6 | Aflevering | Rapport-skrivning, final deploy | Aflevering via Wiseflow |

## 4.4 Eskalations­plan

| Niveau | Eksempel | Hvem håndterer |
|---|---|---|
| 1 | Teknisk problem (kode kompilerer ikke) | Løses internt i gruppen |
| 2 | Uklarheder om krav | Spørg PO i Sprint Review eller via GitHub Discussions |
| 3 | Sprint scope kan ikke holdes | Scrum Master taler med PO inden Sprint Review |
| 4 | Konflikt i gruppen | Bringes op på Retrospective; ellers vejleder |
| 5 | Risiko for ikke at kunne aflevere | Vejleder kontaktes straks |

## 4.5 Evaluering

Efter hvert sprint evaluerer vi på Retrospective:

- Var Sprint Review effektiv? — Fik PO det han behøvede?
- Forstod vi hinandens daily standups?
- Var GitHub Projects opdateret nok til at andre kunne følge med?
- Manglede vi kommunikation med nogen interessent?

Justeringer dokumenteres som action items og følges op næste Retrospective.
