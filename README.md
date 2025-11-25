# **Project Documentation**

## **1. Introduction**

### **Team Members**

* *Jameel Ali - 816040972*
* *Matthew Moodoo -*
* *Nathan Baptiste - 816039236*

### **Project Scope**
This project involves designing and developing a Multi-Player Jeopardy Game in Java. The application supports 1–4 players in a turn-based trivia format. All game questions and categories are loaded from a CSV file.

Players select categories and point values, answer questions, and earn or lose points based on correctness. The game ends when all questions are exhausted or the session is manually stopped. At completion, the system generates two outputs:

* A summary report, in a TXT file, containing final scores and a full breakdown of every turn.

* A Process Mining event log capturing every user interaction in chronological, CSV-formatted form.

This project applies Object-Oriented Design, SOLID principles, and three design patterns (Observer, Builder and Composite), excluding Singleton, while implementing reliable file parsing, gameplay mechanics, scoring rules, report generation, and automated logging. It also includes JUnit testing and Maven build management.

---

## **Design**

### **Design Patterns Used**

* **Observer Pattern**

  ***Why it was chosen:***

  The Jeopardy Game contains multiple UI components and game systems that must react to events such as score updates, question selection, button locking, and logging. Using the Observer pattern prevents tight coupling by separating event producers (subjects) from event consumers (observers). This supports the project requirement for clean design, scalable UI updates, and real-time Process Mining logging.
   
  ***How it is applied:***

  The project defines generic Subject and Observer interfaces, along with specialized variants for different responsibilities (e.g., ScoreObserver, LogObserver, ButtonLockObserver, ScoreUIObserver).
  UI components and game objects receive updates without depending on concrete classes.


* **Builder Pattern**

  ***Why it was chosen:***

  Some objects in the system, such as event log entries, contain many fields. Creating these using long constructors is error-prone and difficult to read. The Builder pattern enables clean construction of structured objects, especially those written to the process mining CSV log.

   ***How it is applied:***

  The LogEvent class uses an internal Builder class to fluently assemble complete event records before they are passed to the logger. This ensures that each logged interaction conforms to the mandatory fields required by the assignment (Case_ID, Player_ID, Activity, Timestamp, etc.).

* **Composite Pattern**

   ***Why it was chosen:***
   
   Game data is naturally hierarchical:
   * A Category contains multiple Questions.
   * A Question contains multiple Options.
     
   This structure benefits from a composition-based model where larger components are built from smaller ones. It simplifies traversal, status checks, and UI representation of the Jeopardy board.
   
   ***How it is applied:***
   
   The Category class maintains a list of Question objects and provides operations such as addQuestion(...) and allAnswered(). Each Question stores a list of answer options.
   It models the problem domain with nested, composable objects, making it easy to manage question availability and UI rendering.

---

### **SOLID Principles**

* **S – Single Responsibility Principle**
  *How classes were kept focused on a single purpose.*

* **O – Open/Closed Principle**
  *Where the system allows extension without modifying existing code.*

* **L – Liskov Substitution Principle**
  *How inheritance or interfaces were structured.*

* **I – Interface Segregation Principle**
  *How interfaces were kept small and specific.*

* **D – Dependency Inversion Principle**
  *How high-level modules depend on abstractions instead of concrete classes.*

*Add details here…*

---

## **Class Diagram**

Describe the class diagram here.

### **UML Class Diagram**

*Place your class diagram here…*
![](/ClassDiagram.png)

---

## **Implementation**

### **How to Run the Application**

**Prerequisites**
  * Java JDK installed (latest version recommended).
  * An IDE (IntelliJ IDEA, Eclipse, VS Code).
  * Maven installed.

**Step-by-Step Instructions**
1) Clone the repository
2) Run Main.java
   * Open the project in your IDE.
   * Locate the Main.java file.
   * Run the file using the IDE run configuration.
3) When the app runs:
   * An application window will appear to prompt you to select the CSV file containing the questions. Use the file chooser to navigate to and select your CSV file.
   * Ensure your CSV has the columns required by the app. The column names and format are documented in the "File Format Details" section below.
   * After selecting the CSV, the app will ask you to select the number of players.
   * Input the names of each player when prompted; provide one name per player.
   * Confirm the setup; the game will start with the loaded questions and entered players.
     
---

### **File Format Details**

* **Input CSV file format:**

    Category | Value | Question | OptionA | OptionB | OptionC | OptionD | CorrectAnswer

    *Example:* Variables & Data Types | 100 | Which of the following declares an integer variable in C++? | int num; | float num; | num int; | integer num; | A

* **Output CSV file format (game_event_log.csv):**

    Case_ID | Player_ID | Activity | Timestamp | Category | Question_Value | Answer_Given | Result | Score_After_Play

* **Report Details (jeopardyGameReport.txt):**

  Contains:
    * Final scores
    * Full turn-by-turn breakdown
    * Player answers and correctness
    * Score after each turn
      
---

## **Test Summary**

### **Test Suite**

Describe the tests you wrote:

* Unit tests
* Integration tests
* Edge case handling
* UI tests (if applicable)

*Add information here…*

### **Test Results**

Summarize findings:

* Pass/fail statistics
* Known issues
* Screenshots/logs (if necessary)

*Add results here…*

---

## **Demo Video**
**Demo Video:**
*Insert Link Here*
