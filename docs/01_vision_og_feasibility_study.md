# 1. Vision og Feasibility Study

## 1.1 Vision

Alpha Solutions er et dansk partnerdrevet udviklingshus med 80+ konsulenter fordelt på tre lande, der leverer enterprise-løsninger til kunder som Husqvarna, Novo Nordisk Pharmatech, Ahlsell, Georg Jensen m.fl. Med så mange konsulenter og 75+ enterprise-projekter er præcis projektkalkulation afgørende både for kommerciel succes og for kundernes tillid.

**Visionen for Projektkalkulationsværktøjet er:**

> *"Et fælles digitalt værktøj, der gør det muligt for Alpha Solutions at nedbryde projekter struktureret, estimere tidsforbrug troværdigt og kommunikere prisestimater gennemsigtigt til kunden — på tværs af alle kontorer fra København til Los Angeles."*

Værdien for Alpha Solutions er:

- **Reduceret friktion** mellem konsulent og kunde — alle arbejder ud fra samme kalkulation.
- **Bedre estimater** — historiske data fra tidligere projekter kan bruges som reference.
- **Hurtigere tilbudsgivning** — nedbrydningen genbruges og tilpasses fra projekt til projekt.
- **ESG-rapportering** — systemet beregner CO2-aftryk per projekt og styrker Alpha Solutions' ESG-profil over for kunder.

## 1.2 Strategianalyse

| Strategisk faktor | Status hos Alpha Solutions | Konsekvens for systemet |
|---|---|---|
| Markedssegment | Enterprise B2B, e-commerce, PIM/CRM | Værktøjet skal kunne håndtere komplekse, langvarige projekter |
| Geografi | Tre lande (DK, NO, USA) | Webbaseret, ikke afhængigt af lokal installation |
| Vækststrategi | Vækst i ESG-relaterede services | CO2-modul giver konkurrencefordel |
| Forretningsmodel | Konsulent-timer, faste pris-kontrakter | Estimering er kerneforretning, ikke en bi-aktivitet |

**Konklusion:** Systemet understøtter direkte Alpha Solutions' kerneforretning — at sælge konsulent-timer baseret på troværdige estimater.

## 1.3 Organisationsanalyse

Alpha Solutions er **partnerdrevet** med en **CTO** (Klaus Petersen) som teknisk leder. Organisationen er præget af:

- Erfarne udviklere med høj autonomi
- Tværgående teams på tværs af kontorer
- Behov for fælles processer og artefakter (kalkulationer, dokumentation)
- Lav tolerance for tunge bureaukratiske systemer — værktøjet skal være letvægt

**Implikation:** Brugergrænsefladen skal være enkel og hurtig. Et tungt enterprise-system med 50 felter pr. opgave ville blive boykottet af konsulenterne.

## 1.4 Styring (Governance)

| Område | Beslutning |
|---|---|
| Datasikkerhed | Login-baseret, role-based access (ADMIN, PROJECT_MANAGER, USER) |
| Versionsstyring | GitHub med branch protection på main |
| Deployment | Kun via CI/CD pipeline til Azure — ingen manuelle deploys |
| Backup | Azure Database for MySQL har automatisk backup (point-in-time restore) |
| Compliance | GDPR — vi gemmer kun forretningsdata, ikke personfølsom info |

## 1.5 Fokus / Competing Values Framework

Competing Values Framework (Cameron & Quinn) kortlægger en organisations kultur og fokus i fire dimensioner:

```
              FLEXIBILITY
                  |
       CLAN  -----+-----  ADHOCRACY
        |         |         |
INTERNAL ---------+--------- EXTERNAL
        |         |         |
   HIERARCHY ----+----- MARKET
                  |
              STABILITY
```

**Alpha Solutions placering:** Primært i **Adhocracy** (innovation, ekstern fokus, fleksibilitet) med element af **Market** (kunde-orienteret, resultatfokus).

**Hvad betyder det for systemet:**

- **Værktøjet skal være fleksibelt** — projekter er sjældent ens, og nedbrydningen skal kunne tilpasses
- **Hurtig levering frem for perfekt** — MVP først, så iterativ forbedring
- **Kundefokus** — kunden skal kunne se sin egen kalkulation (fremtidig feature)

## 1.6 SWOT-analyse

| | **Hjælpsomme (positive)** | **Skadelige (negative)** |
|---|---|---|
| **Interne** | **Styrker (S)**<br>• Bygges af studerende i tæt dialog med Alpha Solutions<br>• Moderne tech-stack (Spring Boot, MySQL)<br>• CI/CD fra dag ét — høj kvalitet | **Svagheder (W)**<br>• Studenter-projekt — begrænsede ressourcer<br>• Begrænset domæneerfaring<br>• Ingen direkte integration med eksisterende Alpha Solutions-systemer |
| **Eksterne** | **Muligheder (O)**<br>• Stigende efterspørgsel efter ESG-rapportering<br>• Alpha Solutions kan bruge værktøjet som differentiator<br>• Mulig udvidelse til kundefacing portal | **Trusler (T)**<br>• Konkurrence fra Smartsheet, projectmanager.com<br>• Risiko for at Alpha Solutions allerede har et internt værktøj<br>• Teknisk gæld hvis MVP ikke videreudvikles |

**Strategiske tiltag:**

- **S+O:** Brug det moderne tech-fundament til at levere ESG-features tidligt
- **W+T:** Fokusér på kerne-funktionalitet (nedbrydning + estimering) frem for at konkurrere bredt med Smartsheet

## 1.7 Vurdering: Desirability, Viability, Feasibility, Sustainability

| Dimension | Vurdering | Begrundelse |
|---|---|---|
| **Desirability** (Ønsker brugerne det?) | **Høj** | CTO har eksplicit bedt om det, og 80+ konsulenter har brug for fælles estimerings-grundlag |
| **Viability** (Er det forretningsmæssigt levedygtigt?) | **Middel-Høj** | Direkte tilknyttet kerneforretningen, men kun internt værktøj — ingen direkte omsætning |
| **Feasibility** (Kan vi bygge det?) | **Høj** | Tech-stack er velkendt, scope er afgrænset i casebeskrivelsen |
| **Sustainability** (Kan det vedligeholdes?) | **Middel** | CI/CD og tests gør det vedligeholdbart, men kræver løbende ejerskab efter studieprojektet |

**Konklusion:** Projektet er **gennemførligt** og **værdiskabende** for Alpha Solutions. Den primære risiko er sustainability efter projektaflevering — det adresseres med grundig dokumentation (README, CONTRIBUTING) og tests.
