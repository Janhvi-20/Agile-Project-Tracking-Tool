# Agile Project Tracking Tool

A backend system for managing agile software projects — create projects, invite and assign team members, track tasks and issues, and communicate through project-scoped messaging areas.

Built with **Java (Spring Boot)** and **MySQL**, with a **React** frontend and containerized deployment on the roadmap.

---

## ✨ Features (Current)

- **User Authentication** — Secure sign-up/login for team members
- **Project Management** — Create and manage projects
- **Invitations & Assignment** — Invite users to a project and assign them to specific areas/roles
- **Task & Issue Tracking** — Create and manage tasks and issues within a project
- **Area Assignment** — Organize work into areas and assign responsible members
- **Messaging** — Project-scoped messaging area for team communication

## 🧱 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot |
| Build Tool | Maven (`mvnw`) |
| Database | MySQL (see `The SQL diagram ER model.mwb`) |
| Frontend | React *(planned)* |
| Deployment | Docker / GitHub Actions CI-CD *(planned)* |

## 📂 Project Structure

```
Agile-Project-Tracking-Tool/
├── src/                          # Application source code
├── .mvn/wrapper/                 # Maven wrapper files
├── The SQL diagram ER model.mwb  # MySQL Workbench ER diagram
├── pom.xml                       # Maven build configuration
├── mvnw / mvnw.cmd                # Maven wrapper scripts
└── README.md
```

## 🏗️ Architecture

The system follows a **layered, client-server architecture** with a Spring Boot backend exposing REST APIs, a MySQL relational database, and (planned) a React single-page frontend consuming those APIs.

```
┌─────────────────────────────┐
│        React Frontend        │   (planned)
│  Dashboards, Boards, Chat UI │
└───────────────┬──────────────┘
                 │ REST API (JSON / HTTPS)
┌───────────────▼──────────────┐
│        Spring Boot App        │
│  ┌─────────────────────────┐  │
│  │     Controller Layer     │  │  ← REST endpoints (Auth, Projects, Tasks, Messaging)
│  ├─────────────────────────┤  │
│  │      Service Layer       │  │  ← Business logic (invitations, assignment, task rules)
│  ├─────────────────────────┤  │
│  │    Repository Layer      │  │  ← Spring Data JPA repositories
│  └─────────────────────────┘  │
└───────────────┬──────────────┘
                 │ JDBC
┌───────────────▼──────────────┐
│           MySQL DB            │
│  Users, Projects, Tasks,      │
│  Issues, Areas, Messages,     │
│  Invitations                  │
└───────────────────────────────┘
```

### Core Modules

| Module | Responsibility |
|---|---|
| **Auth** | User registration, login, session/token handling |
| **Projects** | Create/manage projects, invite members |
| **Assignment** | Assign users to projects and areas |
| **Tasks & Issues** | CRUD for tasks and issues within a project |
| **Areas** | Group work into areas, assign owners |
| **Messaging** | Project-scoped communication between members |

### Planned Deployment Architecture

```
GitHub Repo
   │  (push/PR)
   ▼
GitHub Actions (CI/CD)
   │  build → test → containerize
   ▼
Docker Images (backend + frontend)
   │
   ▼
Cloud Host (target TBD)
   │
   ├── Backend container  ──► MySQL (managed or containerized)
   └── Frontend container ──► serves React build
```

## 🚀 Getting Started

### Prerequisites
- Java 17+ (adjust to match your `pom.xml`)
- Maven (or use the included `./mvnw` wrapper)
- MySQL instance running locally or remotely

### Setup

```bash
# Clone the repository
git clone https://github.com/Janhvi-20/Agile-Project-Tracking-Tool.git
cd Agile-Project-Tracking-Tool

# Configure your database connection
# Update src/main/resources/application.properties (or .yml) with your MySQL credentials

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The database schema can be visualized/edited using the included `The SQL diagram ER model.mwb` file in MySQL Workbench.

## 🗺️ Roadmap

- [ ] **Frontend (React)** — Build a React client to consume the existing backend APIs (dashboards, project boards, task views, messaging UI)
- [ ] **API Documentation** — Add Swagger/OpenAPI docs for all endpoints
- [ ] **Containerization** — Dockerize the backend (and eventually the frontend) with a `Dockerfile` + `docker-compose.yml` for local dev (app + MySQL)
- [ ] **CI/CD Pipeline** — Set up GitHub Actions to automate build, test, and deployment on push/PR
- [ ] **Deployment** — Ship to a cloud environment (target TBD — e.g., Render/AWS) via the CI/CD pipeline
- [ ] **Testing** — Expand unit/integration test coverage
- [ ] **Real-time messaging** — Consider WebSocket support for live updates in the messaging area

## 🤝 Contributing

This project is under active development. Issues and pull requests are welcome once the initial feature set stabilizes.

