<p align="center">
  <img src="Zuke_Logo_Dark.svg" alt="ZUKE Logo" width="1000"/>
</p>



# Zuke User Guide

Zuke is a **fast, simple command-line task manager** for organizing your todos, deadlines, and events. It helps you keep track of what needs to be done through an easy-to-use text interface.

## Contents
* TOC
{:toc}


## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `Zuke.jar` from the releases.
3. Copy the file to the folder you want to use as the home folder for Zuke.
4. Open a command terminal, navigate to the folder with the jar file, and run:
   ```bash
   java -jar Zuke.jar
   ```
5. If everything goes right, you should see the following on your CLI:
   ```text
   $$$$$$$$\ $$\   $$\ $$\   $$\ $$$$$$$$\ 
    \____$$  |$$ |  $$ |$$ | $$  |$$  ____|
        $$  / $$ |  $$ |$$ |$$  / $$ |      
       $$  /  $$ |  $$ |$$$$$  /  $$$$$\    
      $$  /   $$ |  $$ |$$  $$<   $$  __|   
     $$  /    $$ |  $$ |$$ |\$$\  $$ |      
    $$$$$$$$\ \$$$$$$  |$$ | \$$\ $$$$$$$$\ 
    \________| \______/ \__|  \__|\________|
    
    ____________________________________________________________
    Hello! I'm Zuke
    What can I do for you?
    ____________________________________________________________
    Loading data...
    No previous data available, start adding your task now

   ```
6. Type commands and press Enter to execute them.

    ```text
    ____________________________________________________________
    Hello! I'm Zuke
    What can I do for you?
    ____________________________________________________________
    Loading data...
    No previous data available, start adding your task now
    ```


## Features

> **Note about command format:**
> - Words in `UPPER_CASE` are parameters you supply  
>   e.g., in `todo DESCRIPTION`, `DESCRIPTION` can be `buy groceries`
> - Items in square brackets are optional  
>   e.g., `deadline DESCRIPTION /by DATE [TIME]` can be used as `deadline return book /by 2025-10-15` or `deadline return book /by 2025-10-15 1800`

### Adding a Todo Task: `todo`

Adds a simple task without any date/time.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo buy groceries
```

**Expected output:**
```
Got it. I've added this task:
[T][ ] buy groceries
Now you have 1 tasks in the list.
```

---

### Adding a Deadline: `deadline`

Adds a task with a deadline.

**Format:** `deadline DESCRIPTION /by DATE [TIME]`

**Date formats:**
- `yyyy-MM-dd` (e.g., 2025-10-15)
- `d/M/yyyy` (e.g., 15/10/2025)

**Time formats (optional):**
- `HHmm` (e.g., 1800)
- `HH:mm` (e.g., 18:00)

**Example:**
```
deadline submit report /by 2025-10-15 1800
```

**Expected output:**
```
Got it. I've added this task:
[D][ ] submit report (by: Oct 15 2025, 6:00PM)
Now you have 2 tasks in the list.
```

---

### Adding an Event: `event`

Adds an event with start and end times.

**Format:** `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]`

**Example:**
```
event team meeting /from 2025-10-20 0900 /to 2025-10-20 1100
```

**Expected output:**
```
Got it. I've added this task:
[E][ ] team meeting (from: Oct 20 2025, 9:00AM to: Oct 20 2025, 11:00AM)
Now you have 3 tasks in the list.
```

---

### Listing All Tasks: `list`

Shows all tasks in your list.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
Here are the tasks in your list:
1. [T][ ] buy groceries
2. [D][ ] submit report (by: Oct 15 2025, 6:00PM)
3. [E][ ] team meeting (from: Oct 20 2025, 9:00AM to: Oct 20 2025, 11:00AM)
```

---

### Marking a Task as Done: `mark`

Marks a task as completed.

**Format:** `mark INDEX`

**Example:**
```
mark 1
```

**Expected output:**
```
Nice! I've marked this task as done:
[T][X] buy groceries
```

---

### Unmarking a Task: `unmark`

Marks a completed task as not done.

**Format:** `unmark INDEX`

**Example:**
```
unmark 1
```

**Expected output:**
```
OK, I've marked this task as not done yet:
[T][ ] buy groceries
```

---

### Deleting a Task: `delete`

Removes a task from your list.

**Format:** `delete INDEX`

**Example:**
```
delete 2
```

**Expected output:**
```
Got it. I've deleted this task:
[D][ ] submit report (by: Oct 15 2025, 6:00PM)
Now you have 2 tasks in the list.
```

---

### Finding Tasks by Keyword: `find`

Finds all tasks containing a specific keyword (case-insensitive).

**Format:** `find KEYWORD`

**Example:**
```
find meeting
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [E][ ] team meeting (from: Oct 20 2025, 9:00AM to: Oct 20 2025, 11:00AM)
```

---

### Finding Tasks by Date: `date`

Finds all tasks occurring on a specific date.
- For deadlines: shows tasks due on that exact date
- For events: shows tasks where the date falls within the event period

**Format:** `date DATE`

**Example:**
```
date 2025-10-15
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [D][ ] submit report (by: Oct 15 2025, 6:00PM)
```

---

### Exiting the Program: `bye`

Exits Zuke and saves all tasks.

**Format:** `bye`

**Example:**
```
bye
```

**Expected output:**
```
Bye. Hope to see you again soon!
```

---

## Task Symbols

- `[T]` - Todo task
- `[D]` - Deadline task
- `[E]` - Event task
- `[X]` - Completed (marked as done)
- `[ ]` - Not completed

---

## Data Storage

- Tasks are automatically saved to `data/zuke.text` when you exit
- Data is automatically loaded when you start Zuke
- If no previous data exists, Zuke starts with an empty task list

---

## FAQ

**Q: How do I transfer my data to another computer?**  
A: Copy the `data` folder from your current Zuke home folder to the new computer's Zuke home folder.

**Q: What happens if I enter an invalid date?**  
A: Zuke will show an error message explaining the correct date format. Your task list remains unchanged.

**Q: Can I edit a task after adding it?**  
A: Currently, you need to delete the task and add it again with the correct information.

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| **Add Todo** | `todo DESCRIPTION` | `todo buy milk` |
| **Add Deadline** | `deadline DESCRIPTION /by DATE [TIME]` | `deadline assignment /by 2025-10-15 2359` |
| **Add Event** | `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]` | `event conference /from 2025-11-01 /to 2025-11-03` |
| **List** | `list` | `list` |
| **Mark** | `mark INDEX` | `mark 2` |
| **Unmark** | `unmark INDEX` | `unmark 2` |
| **Delete** | `delete INDEX` | `delete 3` |
| **Find by keyword** | `find KEYWORD` | `find book` |
| **Find by date** | `date DATE` | `date 2025-10-15` |
| **Exit** | `bye` | `bye` |

---
