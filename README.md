# Astronaut Schedule & Design Patterns Assignment

## Assignment 1: Astronaut Schedule Organizer

A Java console application to manage astronaut daily tasks using OOP and Observer pattern.

### Features
- Add, remove, edit, and mark tasks as completed
- Task conflict detection (overlapping times)
- View tasks by priority or sorted by time
- Console and file logging (see `application.log`)
- Observer pattern for notifications

### Structure
- [`Main.java`](Assignment%201/Main.java): Entry point, menu-driven UI
- [`Task.java`](Assignment%201/Task.java): Task model
- [`TaskFactory.java`](Assignment%201/TaskFactory.java): Task creation & validation
- [`ScheduleManager.java`](Assignment%201/ScheduleManager.java): Singleton manager, handles tasks and observers
- [`ScheduleObserver.java`](Assignment%201/ScheduleObserver.java): Observer interface
- [`ConsoleObserver.java`](Assignment%201/ConsoleObserver.java): Console notifications
- [`Logger.java`](Assignment%201/Logger.java): File logger

### How to Run
Compile all `.java` files in `Assignment 1/` and run `Main`:
```sh
javac *.java
java Main
```

---

## Assignment 2: Design Pattern Use Cases

A collection of Java demos for Creational, Structural, and Behavioural design patterns.

### Structure

#### Behavioural Patterns
- **Payment Selection In Online Shopping**  
  [`Main.java`](Assignment%202/Behavioural%20Design%20Pattern%20Use%20Case/Payment%20Selection%20In%20Online%20Shopping/Main.java): Strategy pattern for payment methods.
- **Smart Home Notification System**  
  [`Main.java`](Assignment%202/Behavioural%20Design%20Pattern%20Use%20Case/Smart%20Home%20Notification%20System/Main.java): Observer pattern for smart devices.

#### Creational Patterns
- **Pizza Ordering System**  
  [`Main.java`](Assignment%202/Creational%20Design%20Pattern%20Use%20Cases/Pizza%20Ordering%20System/Main.java): Factory pattern for pizza creation.
- **Logger System in Robotics Project**  
  [`Main.java`](Assignment%202/Creational%20Design%20Pattern%20Use%20Cases/Logger%20System%20in%20Robotics%20Project/Main.java): Singleton pattern for logging.

#### Structural Patterns
- **Coffee Shop Order Customization**  
  [`Main.java`](Assignment%202/Structural%20Design%20Pattern%20Use%20Cases/Coffee%20Shop%20Order%20Customization/Main.java): Decorator pattern for coffee extras.
- **Temperature Sensor With Dashboard**  
  [`Main.java`](Assignment%202/Structural%20Design%20Pattern%20Use%20Cases/Tempraturer%20Sensor%20With%20Dashboard/Main.java): Adapter pattern for sensor integration.

### How to Run
Navigate to the relevant folder, compile all `.java` files, and run `Main`. Example:
```sh
cd "Assignment 2/Creational Design Pattern Use Cases/Pizza Ordering System"
javac *.java
java Main
```
Repeat for other demos.

---

## Requirements

- Java 8 or higher
- Console access

## Author

Prepared for PDEU Placement Education Innovation Exercise.
