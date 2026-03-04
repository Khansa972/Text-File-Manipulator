<div align="center">

<h1>🖥️ Text File Manipulator — GUI Version</h1>

<p>
  <strong>JavaFX-based GUI application for text file manipulation using custom-built Data Structures.</strong><br/>
  Modern Dark/Light theme — run locally or on Replit!
</p>

<p>
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/GUI-JavaFX-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Theme-Dark%20%2F%20Light-purple?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Run%20On-Replit-red?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" />
</p>

> 💻 **Console Version available** → [`main`](../../tree/main) branch

</div>

---

## 📖 Overview

This is the **GUI version** of the Text File Manipulator project, built using **JavaFX**. It provides a modern, visually appealing interface with all 8 file manipulation operations — powered by the same custom **Stack** and **LinkedList** data structures from the console version, now embedded in a single Java file.

---

## ✨ What's New in GUI Version

| Feature | Details |
|---------|---------|
| 🎨 **Modern JavaFX UI** | Clean, colorful interface with sidebar navigation |
| 🌙 **Dark / Light Theme** | Toggle between themes with one click |
| 📂 **File Chooser** | Browse and open any `.txt` file visually |
| 📝 **Live Text Area** | File content updates instantly after every operation |
| 💾 **Save Button** | Save changes directly from the UI |
| 🎨 **Color-coded Buttons** | Each operation has its own accent color |
| 📊 **Status Bar** | Real-time colored feedback messages |
| 🔁 **Clipboard Display** | Shows what is copied/cut in the status bar |

---

## 🎨 UI Layout

```
┌──────────────────────────────────────────────────────────────┐
│  📝 Text File Manipulator     [📂 Open File]  [🌙 Dark/Light]│  ← Top Bar
├───────────────────┬──────────────────────────────────────────┤
│  OPERATIONS       │  FILE CONTENT                            │
│                   │                                          │
│  1. Duplicates    │  Hello World                             │
│  2. Reverse       │  This is a sample file                   │
│  3. Insert Line   │  Java is great                           │  ← Live TextArea
│  4. Copy          │  Data Structures                         │
│  5. Paste         │  Text File Manipulator                   │
│  6. Cut           │  Learning is fun                         │
│  7. Sort          │  ...                                     │
│  8. Merge         │                                          │
│                   │                                          │
│  [💾 Save File]   │                                          │
├───────────────────┴──────────────────────────────────────────┤
│  ✅ File opened successfully!                                 │  ← Status Bar
└──────────────────────────────────────────────────────────────┘
```

---

## 🌿 Branch Structure

```
gui-version/
│
├── src/
│   └── TextFileManipulatorGUI.java  # Complete GUI (DSStack + DSLinkedList inside)
│
├── sample.txt                       # Sample text file for testing
├── .replit                          # Replit run configuration
├── README.md                        # This file
├── .gitignore                       # Git ignore rules
└── LICENSE                          # MIT License
```

---

## 🧱 Data Structures Inside GUI File

### 🔷 DSStack (Array-Based — Auto Growing)
| Method | Description |
|--------|-------------|
| `push(String x)` | Add to top — auto grows if full |
| `pop()` | Remove and return top |
| `peek()` | View top without removing |
| `isEmpty()` | Check if empty |
| `clearStack()` | Remove all elements |
| `display()` | Print all elements |

### 🔷 DSLinkedList (Singly Linked)
| Method | Description |
|--------|-------------|
| `addFirst(String data)` | Insert at head |
| `addLast(String data)` | Insert at tail |
| `deletefirstString()` | Delete and return head |
| `contains(String data)` | Search for value |
| `isEmpty()` | Check if empty |
| `display()` | Print all elements |

---

## ⚙️ How to Run

### Option 1 — Run on Replit *(Easiest)*

1. Go to **https://replit.com**
2. Click **"Create Repl"** → **"Import from GitHub"**
3. Paste: `https://github.com/Khansa972/Text-File-Manipulator`
4. Select branch → **`gui-version`**
5. Language → **Java**
6. Click **Import** then ▶️ **Run**
7. Open **VNC Viewer** tab to see the GUI window

---

### Option 2 — Run Locally (JDK 8)

> JavaFX is built-in with JDK 8 — no extra setup needed!

```bash
# Step 1: Clone and switch branch
git clone https://github.com/Khansa972/Text-File-Manipulator.git
cd Text-File-Manipulator
git checkout gui-version

# Step 2: Navigate to src
cd src

# Step 3: Compile
javac TextFileManipulatorGUI.java

# Step 4: Run
java TextFileManipulatorGUI
```

---

### Option 3 — Run Locally (JDK 11 or above)

> JavaFX must be downloaded separately from https://openjfx.io

```bash
# Compile with JavaFX module path
javac --module-path /path/to/javafx-sdk/lib \
      --add-modules javafx.controls,javafx.fxml \
      TextFileManipulatorGUI.java

# Run
java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml \
     TextFileManipulatorGUI
```

> ⚠️ Replace `/path/to/javafx-sdk/lib` with your actual JavaFX SDK path.

---

## 🖥️ Operations Guide

| Button | Color | What It Does |
|--------|-------|-------------|
| Eliminate Duplicates | 🩷 Pink | Removes all repeated lines |
| Reverse Content | 🔵 Blue | Flips order of all lines |
| Insert New Line | 🟢 Green | Adds line after a keyword |
| Copy Line | 🟠 Orange | Copies line to clipboard |
| Paste Line | 🟡 Yellow | Pastes clipboard at position |
| Cut Line | 🔴 Red | Removes line to clipboard |
| Sort Content | 🟣 Purple | Sorts lines A→Z |
| Merge File | 🩵 Teal | Merges another .txt file |

---

## 🚀 Possible Improvements

- Add line numbers in the text area
- Support `.csv` and `.log` file formats
- Undo/Redo functionality using Stack history
- Find & Replace feature
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
