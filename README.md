<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waves&color=gradient&customColorList=6,11,20&height=230&section=header&text=Hotel%20Reservation&fontSize=60&fontColor=ffffff&animation=fadeIn&fontAlignY=36&desc=Management%20System%20%7C%20Java%20%2B%20JDBC%20%2B%20MySQL&descSize=20&descAlignY=58" alt="Hotel Reservation header" width="100%"/>

<a href="https://git.io/typing-svg">
  <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=22&duration=3000&pause=900&color=00D4FF&center=true&vCenter=true&width=640&lines=Reserve+rooms+in+seconds+%F0%9F%8F%A8;View+all+reservations+%F0%9F%93%8B;Update+or+cancel+bookings+%E2%9C%8F%EF%B8%8F;Built+with+Java+%26+MySQL+%E2%98%95" alt="Typing animation" />
</a>

<br/>

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connector%2FJ-blueviolet?style=for-the-badge)
![CLI](https://img.shields.io/badge/Interface-Console-success?style=for-the-badge&logo=gnubash&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

<br/>

<img src="https://skillicons.dev/icons?i=java,mysql,git,github,idea" alt="Tech stack" />

</div>

---

## ✨ About

**Hotel Reservation Management System** is a console-based Java application that connects to a MySQL database through JDBC. It lets hotel staff create, view, search, update and delete room reservations from a simple interactive menu.

It's a great project for learning **CRUD operations**, **JDBC connectivity** and **database-driven Java applications**.

---

## 🚀 Features

| | Feature | Description |
|---|---|---|
| 🛏️ | **Reserve a Room** | Book a room with guest name, room number and contact number |
| 📋 | **View Reservations** | Display all current reservations with their date and time |
| 🔍 | **Get Room Number** | Look up a room using a reservation ID + guest name |
| ✏️ | **Update Reservation** | Change guest name, room number or contact details |
| 🗑️ | **Delete Reservation** | Cancel a reservation by ID |
| 👋 | **Graceful Exit** | Animated exit message with a clean shutdown |

---

## 🧭 Application Flow

```mermaid
flowchart TD
    A([🚀 Start]) --> B[Load MySQL JDBC Driver]
    B --> C[(Connect to hotel_db)]
    C --> D{{Main Menu}}
    D -->|1| E[Reserve Room]
    D -->|2| F[View Reservations]
    D -->|3| G[Get Room Number]
    D -->|4| H[Update Reservation]
    D -->|5| I[Delete Reservation]
    D -->|0| J([👋 Exit])
    E --> D
    F --> D
    G --> D
    H --> D
    I --> D
```

---

## 🖥️ Preview

```text
Welcome to Hotel Reservation Management System
Reserve A Room: 1
View Reservations: 2
Get Room Number: 3
Update Reservations: 4
Delete Reservations: 5
Exit: 0
Choose an option: 1

Enter Guest Name:  Aarav
Enter Room Number:  204
Enter Contact Number:  9876543210
Reservation Successful !
```

---

## 🗄️ Database Setup

Run this in MySQL to create the database and table:

```sql
CREATE DATABASE hotel_db;
USE hotel_db;

CREATE TABLE reservations (
    reservation_id   INT AUTO_INCREMENT PRIMARY KEY,
    guest_name       VARCHAR(255) NOT NULL,
    room_number      INT NOT NULL,
    contact_number   VARCHAR(20) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## ⚙️ Getting Started

### Prerequisites

- ☕ **JDK 17** or newer
- 🐬 **MySQL Server 8+**
- 📦 **MySQL Connector/J** (`mysql-connector-j` JAR)
- 🧰 Any IDE (IntelliJ IDEA, Eclipse, VS Code) or the terminal

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/YOUR_USERNAME/HotelReservation.git

# 2. Move into the project
cd HotelReservation
```

### Configure the database connection

Open `HotelReservation.java` and update these values:

```java
private static final String url      = "jdbc:mysql://localhost:3306/hotel_db";
private static final String username = "root";
private static final String password = "YOUR_PASSWORD";
```

### Run it

**Using an IDE:** add the MySQL Connector/J JAR to your project libraries and run `HotelReservation`.

**Using the terminal:**

```bash
# Compile
javac -cp ".:mysql-connector-j-8.x.x.jar" HotelReservation.java

# Run (Linux / macOS)
java -cp ".:mysql-connector-j-8.x.x.jar" HotelReservation

# Run (Windows)
java -cp ".;mysql-connector-j-8.x.x.jar" HotelReservation
```

---

## 📁 Project Structure

```text
HotelReservation/
├── 📄 HotelReservation.java   # Main application (menu + CRUD logic)
├── 📄 README.md               # You are here
└── 📦 lib/
    └── mysql-connector-j.jar  # JDBC driver
```

---

## 🛣️ Roadmap

- [ ] Switch to `PreparedStatement` to prevent SQL injection
- [ ] Move DB credentials to environment variables / a config file
- [ ] Prevent double-booking of the same room
- [ ] Add check-in / check-out dates
- [ ] Add input validation and error handling for bad input
- [ ] Add a GUI using JavaFX or Swing
- [ ] Add unit tests with JUnit

---

## 🤝 Contributing

Contributions, issues and feature requests are welcome!

1. 🍴 Fork the project
2. 🌿 Create your branch: `git checkout -b feature/AmazingFeature`
3. 💾 Commit your changes: `git commit -m "Add AmazingFeature"`
4. 📤 Push to the branch: `git push origin feature/AmazingFeature`
5. 🔃 Open a Pull Request

---

## 📜 License

Distributed under the **MIT License**. See `LICENSE` for more information.

---

<div align="center">

### 💙 If you like this project, give it a ⭐!

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=16&duration=2500&pause=1000&color=F75C7E&center=true&vCenter=true&width=500&lines=Thanks+for+visiting!+%F0%9F%99%8F;Happy+coding!+%F0%9F%9A%80" alt="Footer typing" />

<br/>

Made with ❤️ and ☕ by [YOUR_NAME](https://github.com/YOUR_USERNAME)

<img src="https://capsule-render.vercel.app/api?type=waves&color=gradient&customColorList=6,11,20&height=120&section=footer" alt="Footer wave" width="100%"/>

</div>
