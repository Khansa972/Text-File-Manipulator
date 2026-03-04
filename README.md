<div align="center">

<h1>📝 Text File Manipulator</h1>

<p>
  <strong>A Java-based console application for text file manipulation using custom-built Data Structures.</strong><br/>
  Built entirely from scratch — no Java Collections Framework used.
</p>

<p>
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Type-DSA%20Project-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge" />
  <img src="https://img.shields.io/badge/GUI%20Version-Available-purple?style=for-the-badge" />
</p>

> 🖥️ **GUI Version available** → [`gui-version`](../../tree/gui-version) branch — Built with JavaFX, Dark/Light theme, run on Replit!

</div>

---

## 📖 Overview

**Text File Manipulator** is a Data Structures & Algorithms (DSA) project developed in Java. It provides a menu-driven console interface to perform a wide range of operations on `.txt` files — all powered by custom implementations of **Stack** and **Singly LinkedList**, written entirely from scratch without using Java's built-in Collections Framework.

This project demonstrates how fundamental data structures can be applied to solve real-world file manipulation problems.

---

## 🌿 Branches

| Branch | Description | Run On |
|--------|-------------|--------|
| [`main`](../../tree/main) | Console-based version using Scanner & File I/O | Terminal / CMD |
| [`gui-version`](../../tree/gui-version) | JavaFX GUI with Dark/Light theme toggle | Replit / Local |

---

## ✨ Features

| # | Operation | Description | Data Structure Used |
|---|-----------|-------------|-------------------|
| 1 | **Eliminate Repeated Lines** | Removes all duplicate lines from the file | LinkedList (`contains()`) |
| 2 | **Reverse File Content** | Reverses the order of all lines in the file | Stack (LIFO) |
| 3 | **Insert New Line** | Inserts a new line after a target keyword | Stack |
| 4 | **Copy Line** | Copies a specific line to a clipboard | Stack (Clipboard) |
| 5 | **Paste Line** | Pastes clipboard content at a specified position | Stack + LinkedList |
| 6 | **Cut Line** | Removes a line and stores it in clipboard | Stack (Clipboard) + LinkedList |
| 7 | **Sort File Content** | Sorts lines alphabetically using Bubble Sort | Array + Bubble Sort |
| 8 | **Merge Files** | Appends content of one file into another | LinkedList |
| 9 | **Exit** | Exits the program gracefully | — |

---

## 📁 Project Structure

```
Text-File-Manipulator/
│
├── 🌿 main  ──────────────────────────── Console Version
│   ├── src/
│   │   ├── TextFileManipulator.java   # Main class — entry point & all operations
│   │   ├── LinkedList.java            # Custom Singly LinkedList implementation
│   │   └── Stack.java                 # Custom Stack (array-based) implementation
│   ├── sample.txt                     # Sample text file for testing
│   ├── README.md                      # Project documentation
│   ├── .gitignore                     # Git ignore rules
│   └── LICENSE                        # MIT License
│
└── 🌿 gui-version ─────────────────────── JavaFX GUI Version
    ├── src/
    │   └── TextFileManipulatorGUI.java  # Complete GUI — single file (DSA included)
    ├── sample.txt                       # Sample text file for testing
    ├── .replit                          # Replit run configuration
    ├── README.md                        # GUI version documentation
    ├── .gitignore                       # Git ignore rules
    └── LICENSE                          # MIT License
```

---

## 🧱 Data Structures Implemented

### 🔷 Stack (Array-Based)
A custom **Last-In-First-Out (LIFO)** data structure implemented using an array.

| Method | Description |
|--------|-------------|
| `push(String x)` | Add element to top |
| `pop()` | Remove and return top element |
| `peek()` | View top element without removing |
| `peekindex(int index)` | Access element at a specific position |
| `stacksize()` | Get current number of elements |
| `isEmpty()` | Check if stack is empty |
| `clearstack()` | Remove all elements |
| `display()` | Print all elements |

**Used for:** Clipboard (copy/cut/paste), file content reversal, line insertion

---

### 🔷 Singly LinkedList
A custom **dynamic linear data structure** using Node-based links.

| Method | Description |
|--------|-------------|
| `addFirst(String data)` | Insert at head |
| `addLast(String data)` | Insert at tail |
| `delFirst()` | Delete head node |
| `deletefirstString()` | Delete and return head data |
| `contains(String data)` | Search for a value |
| `isEmpty()` | Check if list is empty |
| `Size()` | Return total node count |
| `display()` | Print all elements |
| `reverseAndWriteToFile(String path)` | Reverse file using Stack |

**Used for:** Storing file lines, duplicate removal, file merging, paste/cut operations

---

## 📚 Topics Covered

**Data Structures:**
- Singly Linked List (insertion, deletion, traversal, searching)
- Stack using Array (push, pop, overflow & underflow handling)
- Array (used in sorting)

**Algorithms:**
- Bubble Sort (on String array using `compareTo()`)
- Linear Search (for duplicate detection via `contains()`)
- LIFO-based reversal

**Java Concepts:**
- File I/O — `BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`, `RandomAccessFile`
- Object-Oriented Programming — Classes, Objects, Inner Classes (`Node`)
- Recursion — Used in `checkname()` for file validation
- Exception Handling — `try-catch`, `IOException`, `NoSuchElementException`
- String manipulation — `contains()`, `compareTo()`, `equalsIgnoreCase()`

---

## ⚙️ How to Run — Console Version

### Prerequisites
- Java JDK 8 or above
- Terminal / Command Prompt

### Steps

```bash
# Step 1: Clone the repository
git clone https://github.com/Khansa972/Text-File-Manipulator.git

# Step 2: Navigate to src folder
cd Text-File-Manipulator/src

# Step 3: Compile all Java files
javac Stack.java LinkedList.java TextFileManipulator.java

# Step 4: Run the program
java TextFileManipulator
```

### Test with Sample File

When the program asks for a file name, enter:
```
sample
```

> ⚠️ Make sure `sample.txt` is in the same folder where you run `java TextFileManipulator`.

---

## 🖥️ How to Run — GUI Version

> Switch to the [`gui-version`](../../tree/gui-version) branch for full instructions.

```bash
# Switch to GUI branch
git checkout gui-version

# Navigate to src
cd src

# Compile
javac TextFileManipulatorGUI.java

# Run
java TextFileManipulatorGUI
```

Or run directly on **Replit** — see the `gui-version` branch README for steps.

---

## 🖥️ Program Demo — Console

```
---------------------------------------------------------------------------------
               T E X T   F I L E   M A N I P U L A T O R
---------------------------------------------------------------------------------
Enter File Name in which you want changes :
> sample

Length of File : 120

------------------------------------------------------------------
 PRESS :
(1). Eliminate repeated Lines from the file
(2). Reverse the content of file
(3). Insert new Line
(4). Copy text
(5). Paste text Line
(6). Cut the Line
(7). Sort the content of the file
(8). Write one file content to another file
(9). Exit
```

---

## 🚀 Possible Improvements

- ✅ **GUI Version** already available → [`gui-version`](../../tree/gui-version) branch
- Replace static array-based Stack with a dynamic/auto-growing implementation
- Support for multiple file formats (`.csv`, `.log`, etc.)
- Undo/Redo functionality using Stack history
- Unit testing with JUnit

---

## 👤 Author

**Khansa Bint-e-Zia**
- 🔗 GitHub: [@Khansa972](https://github.com/Khansa972)
- 📧 Email: khansazia627@gmail.com

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<div align="center">
  <sub>Built with ❤️ as a Data Structures & Algorithms project in Java</sub>
</div>
