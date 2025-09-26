```markdown
# Zuke Task Manager

```
$$$$$$$$\ $$\   $$\ $$\   $$\ $$$$$$$$\ 
\____$$  |$$ |  $$ |$$ | $$  |$$  _____|
    $$  / $$ |  $$ |$$ |$$  / $$ |      
   $$  /  $$ |  $$ |$$$$$  /  $$$$$\    
  $$  /   $$ |  $$ |$$  $$<   $$  __|   
 $$  /    $$ |  $$ |$$ |\$$\  $$ |      
$$$$$$$$\ \$$$$$$  |$$ | \$$\ $$$$$$$$\ 
\________| \______/ \__|  \__|\________|
```

> A fast, simple command-line task manager for organizing your todos, deadlines, and events.

---

## Quick Start

1. Ensure you have **Java 11 or above** installed on your computer
2. Download the latest `Zuke.jar` from releases
3. Navigate to the folder containing the jar file
4. Run the following command:
   ```bash
   java -jar Zuke.jar
   ```
5. Start managing your tasks by typing commands

---

## Features

> **Command Format Notes:**
> - Words in `UPPER_CASE` are parameters you supply (e.g., `DESCRIPTION` could be `buy groceries`)
> - Items in `[square brackets]` are optional (e.g., `[TIME]` in deadline commands)

---

### Adding a Todo Task

Adds a simple task without any date/time.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo buy groceries
```

**Output:**
```
Got it. I've added this task:
[T][ ] buy groceries
Now you have 1 tasks in the list.
```

---

### Adding a Deadline

Adds a task with a specific deadline.

**Format:** `deadline DESCRIPTION /by DATE [TIME]`

**Supported Date Formats:**
- `yyyy-MM-dd` (e.g., `2025-10-15`)
- `d/M/yyyy` (e.g., `15/10/2025`)

**Supported Time Formats (Optional):**
- `HHmm` (e.g., `1800`)
- `HH:mm` (e.g., `18:00`)

**Example:**
```
deadline submit report /by 2025-10-15 1800
```

**Output:**
```
Got it. I've added this task:
[D][ ] submit report (by: Oct 15 2025, 6:00PM)
Now you have 2 tasks in the list.
```

---

### Adding an Event

Adds an event with start and end times.

**Format:** `event DESCRIPTION /from DATE [TIME] /to DATE [TIME]`

**Example:**
```
event team meeting /from 2025-10-20 0900 /to 2025-10-20 1100
```

**Output:**
```
Got it. I've added this task:
[E][ ] team meeting (from: Oct 20 2025, 9:00AM to: Oct 20 2025, 11:00AM)
Now you have 3 tasks in the list.
```

---

### Listing All Tasks

Shows all tasks in your list.

**Format:** `list`

**Output:**
```
Here are the tasks in your list:
1. [T][ ] buy groceries
2. [D][ ] submit report (by: Oct 15 2025, 6:00PM)
3. [E][ ] team meeting (from: Oct 20 2025, 9:00AM to: Oct 20 2025, 11:00AM)
```

---

### Marking a Task as Done

Marks a task as completed.

**Format:** `mark INDEX`

**Example:** `mark 1`

**Output:**
```
Nice! I've marked this task as done:
[T][X] buy groceries
```

---

### Unmarking a Task

Marks a completed task as not done.

**Format:** `unmark INDEX`

**Example:** `unmark 1`

**Output:**
```
OK, I've marked this task as not done yet:
[T][ ] buy groceries
```

---

### Deleting a Task

Removes a task from your list.

**Format:** `delete INDEX`

**Example:** `delete 2`

**Output:**
```
Got it. I've deleted this task:
[D][ ] submit report (by: Oct 15 2025, 6:00PM)
Now you have 2 tasks in the list.
```

---

### Finding Tasks by Keyword

Finds all tasks containing a specific keyword (case-insensitive).

**Format:** `find KEYWORD`

**Example:** `find meeting`

**Output:**
```
Here are the matching tasks in your list:
1. [E][ ] team meeting (from: Oct 20 2025, 9:00AM to: Oct 20 2025, 11:00AM)
```

---

### Finding Tasks by Date

Finds tasks occurring on a specific date.
- **Deadlines:** Shows tasks due on that exact date
- **Events:** Shows tasks where the date falls within the event period

**Format:** `date DATE`

**Example:** `date 2025-10-15`

**Output:**
```
Here are the matching tasks in your list:
1. [D][ ] submit report (by: Oct 15 2025, 6:00PM)
```

---

### Exiting the Program

Exits Zuke and saves all tasks automatically.

**Format:** `bye`

**Output:**
```
Bye. Hope to see you again soon!
```

---

## Task Status Symbols

| Symbol | Meaning |
|--------|---------|
| `[T]` | Todo task |
| `[D]` | Deadline task |
| `[E]` | Event task |
| `[X]` | Task completed |
| `[ ]` | Task not completed |

---

## Data Storage

- Tasks are **automatically saved** to `data/zuke.text` when you exit
- Data is **automatically loaded** when you start Zuke
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
| **List Tasks** | `list` | `list` |
| **Mark Done** | `mark INDEX` | `mark 2` |
| **Unmark** | `unmark INDEX` | `unmark 2` |
| **Delete** | `delete INDEX` | `delete 3` |
| **Find by Keyword** | `find KEYWORD` | `find book` |
| **Find by Date** | `date DATE` | `date 2025-10-15` |
| **Exit** | `bye` | `bye` |
```

---

**For Jekyll Theme Setup:**

Create a `_config.yml` file in your repository root with:

```yaml
theme: jekyll-theme-cayman
title: Zuke Task Manager
description: A fast, simple command-line task manager
show_downloads: true
```

Or for a cleaner minimal theme:

```yaml
theme: jekyll-theme-minimal
title: Zuke
description: Command-line task manager
logo: /assets/logo.png
show_downloads: true
```

Then enable GitHub Pages in your repository settings:
1. Go to Settings > Pages
2. Select Source: `master` branch and `/docs` folder (or root if README is in root)
3. Save****
