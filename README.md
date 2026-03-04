<div align="center">

<h1>🖥️ Text File Manipulator — GUI Version</h1>

<p>
  <strong>JavaFX-based GUI application for text file manipulation using custom-built Data Structures.</strong><br/>
  Modern Dark/Light theme — run locally on your PC!
</p>

<p>
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/GUI-JavaFX-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Theme-Dark%20%2F%20Light-purple?style=for-the-badge" />
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
| 📂 **Open File Button** | Big blue button at **top of left sidebar** — easy to find! |
| 🔄 **Change File Button** | Appears after a file is opened — switch files anytime |
| 📝 **Live Text Area** | File content updates instantly after every operation |
| 💾 **Save File Button** | Saves to current file — shows **exact saved path** in status bar |
| 💾 **Save As Dialog** | If no file is open, opens a dialog to choose save location |
| 🎨 **Color-coded Buttons** | Each operation has its own accent color |
| 📊 **Status Bar** | Real-time colored feedback messages with emoji indicators |
| 🔁 **Clipboard Display** | Shows what is copied/cut in the status bar |

---

## 🎨 UI Layout

```
┌──────────────────────────────────────────────────────────────┐
│  📝 Text File Manipulator — Khansa Bint-e-Zia   [🌙 Toggle] │  ← Top Bar
├───────────────────┬──────────────────────────────────────────┤
│  FILE             │  FILE CONTENT                            │
│  [📂 Open File]   │                                          │
│  [🔄 Change File] │  Hello World                             │
│  📄 sample.txt    │  This is a sample file                   │
│  ─────────────    │  Java is great                           │  ← Live TextArea
│  OPERATIONS       │  Data Structures                         │
│  1. Duplicates    │  Text File Manipulator                   │
│  2. Reverse       │  Learning is fun                         │
│  3. Insert Line   │  ...                                     │
│  4. Copy          │                                          │
│  5. Paste         │                                          │
│  6. Cut           │                                          │
│  7. Sort          │                                          │
│  8. Merge         │                                          │
│  ─────────────    │                                          │
│  [💾 Save File]   │                                          │
├───────────────────┴──────────────────────────────────────────┤
│  💾 Saved!  Location →  C:\Users\...\sample.txt              │  ← Status Bar
└──────────────────────────────────────────────────────────────┘
```

---

## 🌿 Branch Structure

```
gui-version/
│
├── src/
│   └── TextFileManipulatorGUI.java  # 🎨 Complete GUI (DSStack + DSLinkedList inside)
│
├── sample.txt                       # 📄 Sample text file for testing
├── .replit                          # ▶️  Replit run configuration
├── README.md                        # 📖 This file
├── .gitignore                       # 🙈 Git ignore rules
└── LICENSE                          # 📜 MIT License
```

---

## 🧱 Data Structures Inside GUI File

### 🔷 DSStack (Array-Based — Auto Growing)

| Method | Description |
|--------|-------------|
| `push(String x)` | ⬆️ Add to top — auto grows if full |
| `pop()` | ⬇️ Remove and return top |
| `peek()` | 👁️ View top without removing |
| `isEmpty()` | ❓ Check if empty |
| `clearStack()` | 🧹 Remove all elements |
| `display()` | 🖨️ Print all elements |

### 🔷 DSLinkedList (Singly Linked)

| Method | Description |
|--------|-------------|
| `addFirst(String data)` | ⏮️ Insert at head |
| `addLast(String data)` | ⏭️ Insert at tail |
| `deletefirstString()` | ✂️ Delete and return head |
| `contains(String data)` | 🔎 Search for value |
| `isEmpty()` | ❓ Check if empty |
| `display()` | 🖨️ Print all elements |

---

## ⚙️ How to Run

### ✅ Option 1 — Run Locally with JDK 8 *(Easiest — No extra setup!)*

> JavaFX is **built-in** with JDK 8 — no extra downloads needed!

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

### ✅ Option 2 — Run Locally with JDK 11 or above

> Download JavaFX SDK from **https://gluonhq.com/products/javafx/** → Windows → SDK

```bash
# Step 1: Clone and switch branch
git clone https://github.com/Khansa972/Text-File-Manipulator.git
cd Text-File-Manipulator
git checkout gui-version
cd src

# Step 2: Compile
javac --module-path "path\to\javafx-sdk\lib" ^
      --add-modules javafx.controls,javafx.fxml,javafx.graphics ^
      TextFileManipulatorGUI.java

# Step 3: Run
java --module-path "path\to\javafx-sdk\lib" ^
     --add-modules javafx.controls,javafx.fxml,javafx.graphics ^
     TextFileManipulatorGUI
```

> ⚠️ Replace `path\to\javafx-sdk\lib` with your actual JavaFX SDK path.
> Example: `F:\Java\openjfx-17.0.18_windows-x64_bin-sdk\javafx-sdk-17.0.18\lib`

---

## 🖥️ Operations Guide

| Button | Color | What It Does |
|--------|-------|-------------|
| 🚫 Eliminate Duplicates | 🩷 Pink | Removes all repeated lines |
| 🔄 Reverse Content | 🔵 Blue | Flips order of all lines |
| ➕ Insert New Line | 🟢 Green | Adds line after a keyword |
| 📋 Copy Line | 🟠 Orange | Copies line to clipboard |
| 📌 Paste Line | 🟡 Yellow | Pastes clipboard at position |
| ✂️ Cut Line | 🔴 Red | Removes line to clipboard |
| 🔤 Sort Content | 🟣 Purple | Sorts lines A→Z |
| 🔀 Merge File | 🩵 Teal | Merges another .txt file |

---

## 💡 How to Use

```
1. Click 📂 Open File  →  select your .txt file
2. File content appears in the text area
3. Click any operation button on the left
4. Click 💾 Save File  →  status bar shows exact saved path
5. Use 🔄 Change File  →  to switch to a different file anytime
6. Click 🌙 Dark / Light  →  to toggle theme
```

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
