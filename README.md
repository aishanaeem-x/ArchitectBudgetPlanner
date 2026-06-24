# ArchitectBudgetPlanner

A Java Swing desktop application that generates detailed construction budget plans for clients based on their total budget and house requirements.

## Features

- Add clients with name and contact info
- Input budget, number of rooms, and construction type
- Automatically calculates budget allocation across 9 construction categories
- Recommends material tier (Basic / Standard / Premium) based on budget
- Checks room feasibility against budget tier
- Saves all clients and plans persistently using Java serialization
- View full client history with past plans

## Budget Categories

| Category | Allocation |
|---|---|
| Foundation | 15% |
| Structure & Walls | 25% |
| Roofing | 10% |
| Flooring | 10% |
| Electrical | 8% |
| Plumbing | 8% |
| Finishing & Paint | 7% |
| Doors & Windows | 9% |
| Contingency Fund | 8% |

## Material Tiers

| Tier | Budget Range |
|---|---|
| Basic | Below Rs. 50,00,000 |
| Standard | Rs. 50,00,000 – Rs. 1,50,00,000 |
| Premium | Above Rs. 1,50,00,000 |

## Project Structure 
ArchitectBudgetPlanner/

├── data/                        # Serialized client and plan data (auto-generated)

├── filestorage/

│   └── fileStorage.java         # File read/write using Java serialization

├── model/

│   ├── Client.java

│   ├── plan.java

│   └── BudgetAllocation.java

├── service/

│   ├── ClientService.java

│   ├── PlanService.java

│   └── BudgetCalculator.java

├── ui/

│   ├── Mainframe.java

│   ├── HomePanel.java

│   ├── NewClientPanel.java

│   ├── BudgetInputPanel.java

│   ├── PlanResultPanel.java

│   └── ClientHistoryPanel.java

└── main/

└── Main.java
## How to Run

1. Clone the repository
2. Open in any Java IDE (VS Code, IntelliJ, Eclipse)
3. Compile all `.java` files
4. Run `main/Main.java`

> The `data/` folder is created automatically on first run in your home directory.

## Tech Stack

- Java 17+
- Java Swing (GUI)
- Java Serialization (file persistence)
- Layered architecture: Model → Storage → Service → UI

## Author

Aisha Naeem — First Year SE Student, SEECS NUST
