# Technische Kurzdokumentation

**Teamname:** Codigo Rapido  
**Challenge:** Die perfekte Einkaufsliste

---

## Technische Informationen für die Jury

### Aktueller Stand des Sourcecodes

- Das Projekt ist auf GitHub
  verfügbar: [Codigo Rapido – Baern-Haeckt](https://github.com/Schuebi23/Baern-Haeckt-codigo-rapido)
- Der aktuelle Stand umfasst ein funktionierendes Backend (Spring Boot) und ein Angular-Frontend. Für die Datenbank
  wird PostgreSQL verwendet. Kommunikation zwischen Frontend und Backend erfolgt über eine REST-API.
- Die API ist vollständig dokumentiert (OpenAPI/Swagger) und kann direkt getestet werden.

---

### Ausgangslage

- **Fokus:** Wir haben uns auf eine klare Trennung von Backend (API, Datenpersistenz) und Frontend (User Experience,
  Business Logik) konzentriert. Ziel war eine schnelle, benutzerfreundliche Lösung mit stabiler Architektur.
- **Grundsatzentscheide:**
    - **Technologie-Stack:** Java mit Spring Boot im Backend, Angular im Frontend, PostgreSQL als Datenbank.
    - **API-Design:** Spec-First-Ansatz mit Swagger/OpenAPI → zuerst Spezifikation, dann automatische Generierung der
      Endpoints.
    - **Architektur:** Einfache CRUD-Operationen im Backend, die eigentliche Business-Logik ist primär im Frontend
      umgesetzt.

---

### Technischer Aufbau

- **Backend:**
    - Spring Boot (REST-API, Swagger UI, JPA/Hibernate für Datenbankzugriff)
    - PostgreSQL (relationale Datenhaltung, einfache Abfragen und Persistenz)
- **Frontend:**
    - Angular (UI, State-Management, Kommunikation mit API über REST-Endpunkte)
- **API:**
    - OpenAPI/Swagger für API-Spezifikation, Dokumentation und automatische Client/Server-Codegenerierung
- **Build & Deployment:**
    - Gradle (Backend), npm (Frontend)
    - Lokale Containerisierung für die DB

---

### Implementation

- **Besonderheiten:**
    - API wurde strikt Spec-First umgesetzt → dadurch konsistente Schnittstellen und einfaches Testing.
    - Der Einkaufslisten-Workflow ist so aufgebaut, eine möglichst einfach Planung eines Events mit mehreren Personen
      erreicht werden kann.
- **Cooles Feature:**
    - Die automatische Kategorisierung von Artikeln basierend auf Vorschlägen.
    - Gerechte Berechnung der Kosten pro Person je nach dem, wer was gekauft oder verlangt hat.
    - Klare Trennung von Schichten: Backend ist leichtgewichtig, Frontend flexibel erweiterbar.
    - API lässt sich leicht von anderen Clients (z. B. Mobile App) wiederverwenden.

---

### Abgrenzung / Offene Punkte

- **Login / Benutzerverwaltung:**
    - Wurde bewusst weggelassen → sehr zeitaufwändig und kein direkter Mehrwert für die Challenge.
    - Lösungsidee: Integration eines externen Auth-Providers (z. B. Keycloak, Auth0, Google, usw.) in einer späteren
      Version.
- **Nicht umgesetzt:**
    - Keine Offline-Funktionalität (Caching, PWA)
    - Keine Mehrsprachigkeit
- **Offene Punkte:**
    - Optimierung der Performance bei großen Einkaufslisten (Pagination, Lazy Loading).
    - Deployment (z.B. in einer Cloud-Umgebung wie Azure oder AWS)

---
