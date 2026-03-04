import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ============================================================
 *   Text File Manipulator — JavaFX GUI Version
 *   Author  : Khansa Bint-e-Zia
 *   GitHub  : https://github.com/Khansa972
 *   Branch  : gui-version
 * ============================================================
 *
 *  Fixes Applied:
 *   FIX 1 — Open File button moved to TOP of LEFT sidebar (easy to find)
 *   FIX 2 — After save, shows exact full path where file is saved
 *   FIX 3 — "Change File" button appears after file is opened
 *
 *  All 8 Operations:
 *   1. Eliminate Repeated Lines  (LinkedList)
 *   2. Reverse File Content      (Stack - LIFO)
 *   3. Insert New Line           (Stack)
 *   4. Copy Line                 (Stack Clipboard)
 *   5. Paste Line                (Stack + LinkedList)
 *   6. Cut Line                  (Stack Clipboard)
 *   7. Sort Content              (Array + Bubble Sort)
 *   8. Merge Files               (LinkedList)
 * ============================================================
 */
public class TextFileManipulatorGUI extends Application {

    // ─────────────────────────────────────────
    //   THEME COLORS
    // ─────────────────────────────────────────
    private static final String D_BG      = "#0d1117";
    private static final String D_SIDEBAR = "#161b22";
    private static final String D_CARD    = "#21262d";
    private static final String D_TEXT    = "#e6edf3";
    private static final String D_SUBTEXT = "#8b949e";
    private static final String D_AREA    = "#0d1117";

    private static final String L_BG      = "#f0f4f8";
    private static final String L_SIDEBAR = "#ffffff";
    private static final String L_CARD    = "#e2eaf4";
    private static final String L_TEXT    = "#1a1a2e";
    private static final String L_SUBTEXT = "#556677";
    private static final String L_AREA    = "#ffffff";

    private static final String C_BLUE   = "#4cc9f0";
    private static final String C_PINK   = "#f72585";
    private static final String C_GREEN  = "#4ade80";
    private static final String C_ORANGE = "#fb923c";
    private static final String C_YELLOW = "#fbbf24";
    private static final String C_RED    = "#f43f5e";
    private static final String C_PURPLE = "#a855f7";
    private static final String C_TEAL   = "#2dd4bf";

    // ─────────────────────────────────────────
    //   STATE
    // ─────────────────────────────────────────
    private String  currentFilePath = null;
    private boolean isDark          = true;
    private int     fileSize        = 2048;

    private DSLinkedList contentList = new DSLinkedList();
    private DSStack      clipBoard   = new DSStack(fileSize);

    // UI nodes
    private TextArea   fileArea;
    private Label      statusLabel;
    private Label      fileNameLabel;
    private VBox       sidebar;
    private BorderPane root;
    private Scene      scene;
    private Button     changeFileBtn;  // FIX 3

    // ─────────────────────────────────────────
    //   MAIN
    // ─────────────────────────────────────────
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Text File Manipulator  —  Khansa Bint-e-Zia");
        root  = buildLayout(stage);
        scene = new Scene(root, 1100, 700);
        applyTheme();
        stage.setScene(scene);
        stage.setMinWidth(860);
        stage.setMinHeight(580);
        stage.show();
        setStatus("\uD83D\uDCC2  Click  'Open File'  on the LEFT sidebar to get started.", C_BLUE);
    }

    // =========================================================
    //   BUILD LAYOUT
    // =========================================================
    private BorderPane buildLayout(Stage stage) {
        BorderPane bp = new BorderPane();
        bp.setTop(buildTopBar(stage));
        bp.setLeft(buildSidebar(stage));
        bp.setCenter(buildCenter());
        bp.setBottom(buildStatusBar());
        return bp;
    }

    // ── Top Bar ── (FIX 1: Open File removed from here)
    private HBox buildTopBar(Stage stage) {
        HBox bar = new HBox(12);
        bar.setPadding(new Insets(13, 20, 13, 20));
        bar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("\uD83D\uDCDD  Text File Manipulator");
        title.setFont(Font.font("Monospace", FontWeight.BOLD, 18));
        title.setTextFill(Color.web(C_BLUE));

        Label author = new Label("— Khansa Bint-e-Zia");
        author.setFont(Font.font("Monospace", 12));
        author.setTextFill(Color.web(D_SUBTEXT));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button themeBtn = topButton("\uD83C\uDF19 Dark / \u2600 Light", C_PURPLE);
        themeBtn.setOnAction(e -> { isDark = !isDark; applyTheme(); });

        bar.getChildren().addAll(title, author, spacer, themeBtn);
        return bar;
    }

    // ── Sidebar ──
    // FIX 1: Open File at TOP — big solid blue button, impossible to miss
    // FIX 3: Change File button appears after a file is loaded
    private VBox buildSidebar(Stage stage) {
        sidebar = new VBox(8);
        sidebar.setPadding(new Insets(14, 12, 18, 12));
        sidebar.setPrefWidth(235);

        // ── FILE SECTION ──
        Label fileSection = new Label("  FILE");
        fileSection.setFont(Font.font("Monospace", FontWeight.BOLD, 10));
        fileSection.setTextFill(Color.web(D_SUBTEXT));

        // BIG Open File button — solid blue, always visible at top
        Button openBtn = new Button("\uD83D\uDCC2  Open File");
        openBtn.setMaxWidth(Double.MAX_VALUE);
        openBtn.setFont(Font.font("Monospace", FontWeight.BOLD, 14));
        openBtn.setPadding(new Insets(12, 12, 12, 12));
        openBtn.setStyle(
            "-fx-background-color:" + C_BLUE + ";" +
            "-fx-text-fill:#0d1117;" +
            "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
        );
        openBtn.setOnMouseEntered(e -> openBtn.setStyle(
            "-fx-background-color:#7dd8f5;" +
            "-fx-text-fill:#0d1117;" +
            "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
        ));
        openBtn.setOnMouseExited(e -> openBtn.setStyle(
            "-fx-background-color:" + C_BLUE + ";" +
            "-fx-text-fill:#0d1117;" +
            "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
        ));
        openBtn.setOnAction(e -> openFile(stage));

        // FIX 3: Change File button — hidden until first file is opened
        changeFileBtn = new Button("\uD83D\uDD04  Change File");
        changeFileBtn.setMaxWidth(Double.MAX_VALUE);
        changeFileBtn.setFont(Font.font("Monospace", FontWeight.BOLD, 12));
        changeFileBtn.setPadding(new Insets(9, 12, 9, 12));
        applySideBtnStyle(changeFileBtn, C_ORANGE, false);
        changeFileBtn.setOnMouseEntered(e -> applySideBtnStyle(changeFileBtn, C_ORANGE, true));
        changeFileBtn.setOnMouseExited(e  -> applySideBtnStyle(changeFileBtn, C_ORANGE, false));
        changeFileBtn.setOnAction(e -> openFile(stage));
        changeFileBtn.setVisible(false);
        changeFileBtn.setManaged(false);

        // Current file name display
        fileNameLabel = new Label("No file opened");
        fileNameLabel.setFont(Font.font("Monospace", 11));
        fileNameLabel.setTextFill(Color.web(C_YELLOW));
        fileNameLabel.setPadding(new Insets(2, 4, 4, 4));
        fileNameLabel.setWrapText(true);
        fileNameLabel.setMaxWidth(215);

        Separator sep1 = new Separator();
        sep1.setPadding(new Insets(4, 0, 4, 0));

        // ── OPERATIONS SECTION ──
        Label opsSection = new Label("  OPERATIONS");
        opsSection.setFont(Font.font("Monospace", FontWeight.BOLD, 10));
        opsSection.setTextFill(Color.web(D_SUBTEXT));
        opsSection.setPadding(new Insets(2, 0, 2, 0));

        Button b1 = sideBtn("1.  Eliminate Duplicates",  C_PINK,   e -> opEliminateDuplicates());
        Button b2 = sideBtn("2.  Reverse Content",        C_BLUE,   e -> opReverse());
        Button b3 = sideBtn("3.  Insert New Line",        C_GREEN,  e -> opInsertLine());
        Button b4 = sideBtn("4.  Copy Line",              C_ORANGE, e -> opCopy());
        Button b5 = sideBtn("5.  Paste Line",             C_YELLOW, e -> opPaste());
        Button b6 = sideBtn("6.  Cut Line",               C_RED,    e -> opCut());
        Button b7 = sideBtn("7.  Sort Content",           C_PURPLE, e -> opSort());
        Button b8 = sideBtn("8.  Merge File",             C_TEAL,   e -> opMerge(stage));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Separator sep2 = new Separator();
        sep2.setPadding(new Insets(4, 0, 4, 0));

        Button saveBtn = new Button("\uD83D\uDCBE  Save File");
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        saveBtn.setFont(Font.font("Monospace", FontWeight.BOLD, 13));
        saveBtn.setPadding(new Insets(10));
        applySideBtnStyle(saveBtn, C_GREEN, false);
        saveBtn.setOnMouseEntered(e -> applySideBtnStyle(saveBtn, C_GREEN, true));
        saveBtn.setOnMouseExited(e  -> applySideBtnStyle(saveBtn, C_GREEN, false));
        saveBtn.setOnAction(e -> saveFile(stage));

        sidebar.getChildren().addAll(
            fileSection, openBtn, changeFileBtn, fileNameLabel,
            sep1, opsSection,
            b1, b2, b3, b4, b5, b6, b7, b8,
            spacer, sep2, saveBtn
        );
        return sidebar;
    }

    // ── Center ────────────────────────────────
    private VBox buildCenter() {
        VBox centre = new VBox(10);
        centre.setPadding(new Insets(20));

        Label lbl = new Label("FILE CONTENT");
        lbl.setFont(Font.font("Monospace", FontWeight.BOLD, 11));
        lbl.setTextFill(Color.web(C_BLUE));

        fileArea = new TextArea();
        fileArea.setFont(Font.font("Monospace", 14));
        fileArea.setWrapText(false);
        fileArea.setEditable(true);
        fileArea.setPromptText(
            "  Click '\uD83D\uDCC2 Open File' on the LEFT sidebar to open a .txt file\n\n" +
            "  Or start typing here and use the operations on the left!"
        );
        VBox.setVgrow(fileArea, Priority.ALWAYS);

        centre.getChildren().addAll(lbl, fileArea);
        return centre;
    }

    // ── Status Bar ────────────────────────────
    private HBox buildStatusBar() {
        HBox bar = new HBox(8);
        bar.setPadding(new Insets(8, 16, 8, 16));
        bar.setAlignment(Pos.CENTER_LEFT);

        statusLabel = new Label("Ready  —  Open a file from the LEFT sidebar to begin");
        statusLabel.setFont(Font.font("Monospace", 12));
        statusLabel.setTextFill(Color.web(C_BLUE));

        bar.getChildren().add(statusLabel);
        return bar;
    }

    // =========================================================
    //   THEME
    // =========================================================
    private void applyTheme() {
        String bg   = isDark ? D_BG      : L_BG;
        String sb   = isDark ? D_SIDEBAR : L_SIDEBAR;
        String card = isDark ? D_CARD    : L_CARD;
        String text = isDark ? D_TEXT    : L_TEXT;
        String area = isDark ? D_AREA    : L_AREA;

        root.setStyle("-fx-background-color:" + bg + ";");
        root.getTop().setStyle(
            "-fx-background-color:" + sb + ";" +
            "-fx-border-color:" + card + "; -fx-border-width:0 0 1 0;"
        );
        sidebar.setStyle(
            "-fx-background-color:" + sb + ";" +
            "-fx-border-color:" + card + "; -fx-border-width:0 1 0 0;"
        );
        root.getCenter().setStyle("-fx-background-color:" + bg + ";");
        root.getBottom().setStyle(
            "-fx-background-color:" + sb + ";" +
            "-fx-border-color:" + card + "; -fx-border-width:1 0 0 0;"
        );
        fileArea.setStyle(
            "-fx-control-inner-background:" + area + ";" +
            "-fx-text-fill:" + text + ";" +
            "-fx-font-family:Monospace; -fx-font-size:14px;" +
            "-fx-border-color:" + card + "; -fx-border-radius:8; -fx-background-radius:8;"
        );
        fileNameLabel.setTextFill(Color.web(C_YELLOW));
    }

    // =========================================================
    //   BUTTON HELPERS
    // =========================================================
    private Button sideBtn(String text, String color,
                           javafx.event.EventHandler<javafx.event.ActionEvent> h) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setFont(Font.font("Monospace", FontWeight.BOLD, 12));
        btn.setPadding(new Insets(10, 12, 10, 12));
        applySideBtnStyle(btn, color, false);
        btn.setOnMouseEntered(e -> applySideBtnStyle(btn, color, true));
        btn.setOnMouseExited(e  -> applySideBtnStyle(btn, color, false));
        btn.setOnAction(h);
        return btn;
    }

    private void applySideBtnStyle(Button btn, String color, boolean hover) {
        if (hover) {
            btn.setStyle(
                "-fx-background-color:" + color + "22;" +
                "-fx-text-fill:" + color + ";" +
                "-fx-border-color:" + color + ";" +
                "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
            );
        } else {
            btn.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + color + ";" +
                "-fx-border-color:" + color + "33;" +
                "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
            );
        }
    }

    private Button topButton(String text, String color) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Monospace", FontWeight.BOLD, 12));
        btn.setPadding(new Insets(8, 16, 8, 16));
        btn.setStyle(
            "-fx-background-color:" + color + "22;" +
            "-fx-text-fill:" + color + ";" +
            "-fx-border-color:" + color + ";" +
            "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color:" + color + ";" +
            "-fx-text-fill:#0d1117;" +
            "-fx-border-color:" + color + ";" +
            "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color:" + color + "22;" +
            "-fx-text-fill:" + color + ";" +
            "-fx-border-color:" + color + ";" +
            "-fx-border-radius:6; -fx-background-radius:6; -fx-cursor:hand;"
        ));
        return btn;
    }

    // =========================================================
    //   STATUS
    // =========================================================
    private void setStatus(String msg, String color) {
        Platform.runLater(() -> {
            statusLabel.setText(msg);
            statusLabel.setTextFill(Color.web(color));
        });
    }

    // =========================================================
    //   FILE OPEN / SAVE / REFRESH
    // =========================================================
    private void openFile(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Text File");
        fc.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt")
        );
        File f = fc.showOpenDialog(stage);
        if (f == null) return;

        currentFilePath = f.getAbsolutePath();
        fileSize        = (int) Math.max(f.length(), 512);
        clipBoard       = new DSStack(fileSize);
        contentList     = new DSLinkedList();

        // Update sidebar file name
        fileNameLabel.setText("\uD83D\uDCC4 " + f.getName());

        // FIX 3: Show Change File button now that a file is open
        changeFileBtn.setVisible(true);
        changeFileBtn.setManaged(true);

        refreshTextArea();
        setStatus("\uD83D\uDCC2  Opened:  " + f.getAbsolutePath(), C_GREEN);
    }

    private void refreshTextArea() {
        if (currentFilePath == null) return;
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(currentFilePath));
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
            br.close();
            fileArea.setText(sb.toString());
        } catch (IOException e) {
            setStatus("Error reading file: " + e.getMessage(), C_RED);
        }
    }

    private List<String> readLines() throws IOException {
        List<String> list = new ArrayList<>();
        if (currentFilePath == null) {
            for (String l : fileArea.getText().split("\n")) list.add(l);
            return list;
        }
        BufferedReader br = new BufferedReader(new FileReader(currentFilePath));
        String line;
        while ((line = br.readLine()) != null) list.add(line);
        br.close();
        return list;
    }

    private void writeLines(List<String> lines) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String l : lines) sb.append(l).append("\n");
        fileArea.setText(sb.toString());
        if (currentFilePath != null) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(currentFilePath));
            for (String l : lines) { bw.write(l); bw.newLine(); }
            bw.close();
        }
    }

    // FIX 2: Save shows FULL PATH — uses FileChooser if no file is open
    private void saveFile(Stage stage) {
        try {
            // If no file is open, open a FileChooser to let user pick save location
            if (currentFilePath == null) {
                FileChooser fc = new FileChooser();
                fc.setTitle("Save File As");
                fc.setInitialFileName("output.txt");
                fc.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt")
                );
                File saveFile = fc.showSaveDialog(stage);
                if (saveFile == null) return; // user cancelled
                currentFilePath = saveFile.getAbsolutePath();
                fileNameLabel.setText("\uD83D\uDCC4 " + saveFile.getName());
                // Show Change File button
                changeFileBtn.setVisible(true);
                changeFileBtn.setManaged(true);
            }
            // Write content to file
            BufferedWriter bw = new BufferedWriter(new FileWriter(currentFilePath));
            bw.write(fileArea.getText());
            bw.close();
            setStatus("\uD83D\uDCBE  Saved!   Location \u2794  " + currentFilePath, C_GREEN);
        } catch (IOException e) {
            setStatus("\u274C  Error saving: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   DIALOG HELPER
    // =========================================================
    private Optional<String> askInput(String title, String header, String prompt) {
        TextInputDialog d = new TextInputDialog();
        d.setTitle(title);
        d.setHeaderText(header);
        d.setContentText(prompt);
        if (isDark) {
            d.getDialogPane().setStyle(
                "-fx-background-color:#161b22; -fx-text-fill:#e6edf3;"
            );
        }
        return d.showAndWait();
    }

    private boolean hasContent() {
        if (fileArea.getText().trim().isEmpty()) {
            setStatus("\u274C  No content!  Click '\uD83D\uDCC2 Open File' on the left sidebar first.", C_RED);
            return false;
        }
        return true;
    }

    // =========================================================
    //   OPERATION 1 — Eliminate Duplicates
    // =========================================================
    private void opEliminateDuplicates() {
        if (!hasContent()) return;
        try {
            List<String> lines = readLines();
            DSLinkedList seen  = new DSLinkedList();
            for (String l : lines) {
                if (!seen.contains(l)) seen.addLast(l);
            }
            List<String> result = new ArrayList<>();
            String s;
            while ((s = seen.deletefirstString()) != null) result.add(s);
            int removed = lines.size() - result.size();
            writeLines(result);
            setStatus("\u2705  Removed " + removed + " duplicate line(s) successfully!", C_GREEN);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 2 — Reverse Content
    // =========================================================
    private void opReverse() {
        if (!hasContent()) return;
        try {
            List<String> lines = readLines();
            DSStack stack = new DSStack(lines.size() + 1);
            for (String l : lines) stack.push(l);
            List<String> result = new ArrayList<>();
            while (!stack.isEmpty()) result.add(stack.pop());
            writeLines(result);
            setStatus("\u2705  File content reversed successfully!", C_BLUE);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 3 — Insert New Line
    // =========================================================
    private void opInsertLine() {
        if (!hasContent()) return;
        Optional<String> kw = askInput("Insert Line",
            "Enter target keyword — new line inserts after it:", "Keyword");
        if (kw.isEmpty() || kw.get().isBlank()) return;

        Optional<String> nl = askInput("Insert Line",
            "Enter the new line to insert:", "New line text");
        if (nl.isEmpty()) return;

        try {
            List<String> lines = readLines();
            DSStack original = new DSStack(lines.size() + 1);
            DSStack modified = new DSStack(lines.size() * 2 + 2);
            for (String l : lines) original.push(l);
            while (!original.isEmpty()) {
                String cur = original.pop();
                if (cur.contains(kw.get())) modified.push(nl.get());
                modified.push(cur);
            }
            List<String> result = new ArrayList<>();
            while (!modified.isEmpty()) result.add(modified.pop());
            writeLines(result);
            setStatus("\u2705  New line inserted after keyword \"" + kw.get() + "\"!", C_GREEN);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 4 — Copy Line
    // =========================================================
    private void opCopy() {
        if (!hasContent()) return;
        Optional<String> input = askInput("Copy Line",
            "Enter line number to copy:", "e.g. 1");
        if (input.isEmpty()) return;
        try {
            int no = Integer.parseInt(input.get().trim());
            List<String> lines = readLines();
            if (no < 1 || no > lines.size()) {
                setStatus("\u274C  Invalid! Enter a number between 1 and " + lines.size(), C_RED); return;
            }
            clipBoard.clearStack();
            clipBoard.push(lines.get(no - 1));
            setStatus("\uD83D\uDCCB  Line " + no + " copied:  \"" + lines.get(no - 1) + "\"", C_ORANGE);
        } catch (NumberFormatException e) {
            setStatus("\u274C  Please enter a valid number!", C_RED);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 5 — Paste Line
    // =========================================================
    private void opPaste() {
        if (!hasContent()) return;
        if (clipBoard.peek() == null) {
            setStatus("\u274C  Clipboard is empty!  Copy or Cut a line first.", C_RED); return;
        }
        Optional<String> input = askInput("Paste Line",
            "Paste BEFORE which line number?", "e.g. 2");
        if (input.isEmpty()) return;
        try {
            int no = Integer.parseInt(input.get().trim());
            List<String> lines = readLines();
            if (no < 1 || no > lines.size() + 1) {
                setStatus("\u274C  Invalid! Enter a number between 1 and " + (lines.size()+1), C_RED); return;
            }
            lines.add(no - 1, clipBoard.peek());
            writeLines(lines);
            setStatus("\uD83D\uDCCC  Pasted at line " + no + ":  \"" + clipBoard.peek() + "\"", C_YELLOW);
        } catch (NumberFormatException e) {
            setStatus("\u274C  Please enter a valid number!", C_RED);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 6 — Cut Line
    // =========================================================
    private void opCut() {
        if (!hasContent()) return;
        Optional<String> input = askInput("Cut Line",
            "Enter line number to cut:", "e.g. 3");
        if (input.isEmpty()) return;
        try {
            int no = Integer.parseInt(input.get().trim());
            List<String> lines = readLines();
            if (no < 1 || no > lines.size()) {
                setStatus("\u274C  Invalid! Enter a number between 1 and " + lines.size(), C_RED); return;
            }
            String cut = lines.remove(no - 1);
            clipBoard.clearStack();
            clipBoard.push(cut);
            writeLines(lines);
            setStatus("\u2702\uFE0F  Line " + no + " cut to clipboard:  \"" + cut + "\"", C_RED);
        } catch (NumberFormatException e) {
            setStatus("\u274C  Please enter a valid number!", C_RED);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 7 — Sort Content
    // =========================================================
    private void opSort() {
        if (!hasContent()) return;
        try {
            List<String> lines = readLines();
            String[] arr = lines.toArray(new String[0]);
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].compareTo(arr[j]) > 0) {
                        String tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                    }
                }
            }
            writeLines(Arrays.asList(arr));
            setStatus("\u2705  Content sorted alphabetically using Bubble Sort!", C_PURPLE);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }

    // =========================================================
    //   OPERATION 8 — Merge File
    // =========================================================
    private void opMerge(Stage stage) {
        if (!hasContent()) return;
        FileChooser fc = new FileChooser();
        fc.setTitle("Select File to Merge Into Current File");
        fc.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt")
        );
        File second = fc.showOpenDialog(stage);
        if (second == null) return;
        try {
            List<String> original = readLines();
            DSLinkedList list = new DSLinkedList();
            for (String l : original) list.addLast(l);

            BufferedReader br = new BufferedReader(new FileReader(second));
            String line;
            while ((line = br.readLine()) != null) list.addLast(line);
            br.close();

            List<String> result = new ArrayList<>();
            String s;
            while ((s = list.deletefirstString()) != null) result.add(s);
            writeLines(result);
            setStatus("\u2705  Merged \"" + second.getName() + "\" into current file!", C_TEAL);
        } catch (IOException e) {
            setStatus("\u274C  Error: " + e.getMessage(), C_RED);
        }
    }
}


// =============================================================
//   CUSTOM DATA STRUCTURE — Stack (Array-Based)
// =============================================================
class DSStack {
    private String[] arr;
    private int      top;

    DSStack(int size) {
        arr = new String[Math.max(size, 16)];
        top = -1;
    }

    void push(String x) {
        if (top >= arr.length - 1) {
            arr = java.util.Arrays.copyOf(arr, arr.length * 2);
        }
        arr[++top] = x;
    }

    String pop() {
        if (top == -1) return null;
        return arr[top--];
    }

    String peek() {
        return (top == -1) ? null : arr[top];
    }

    boolean isEmpty() { return top == -1; }

    int size() { return top + 1; }

    void clearStack() {
        while (top != -1) pop();
    }

    void display() {
        if (top == -1) { System.out.println("Stack is empty"); return; }
        for (int i = top; i >= 0; i--) System.out.println(arr[i]);
    }
}


// =============================================================
//   CUSTOM DATA STRUCTURE — Singly LinkedList
// =============================================================
class DSLinkedList {

    private class Node {
        String data;
        Node   next;
        Node(String data) { this.data = data; this.next = null; }
    }

    private Node head = null;

    void addFirst(String data) {
        Node n = new Node(data);
        if (head == null) { head = n; }
        else { n.next = head; head = n; }
    }

    void addLast(String data) {
        Node n = new Node(data);
        if (head == null) { head = n; return; }
        Node t = head;
        while (t.next != null) t = t.next;
        t.next = n;
    }

    String deletefirstString() {
        if (head == null) return null;
        Node del = head;
        head     = head.next;
        del.next = null;
        return del.data;
    }

    boolean contains(String data) {
        Node t = head;
        while (t != null) {
            if (t.data.equals(data)) return true;
            t = t.next;
        }
        return false;
    }

    boolean isEmpty() { return head == null; }

    int size() {
        int c = 0;
        Node t = head;
        while (t != null) { c++; t = t.next; }
        return c;
    }

    void display() {
        if (head == null) { System.out.println("List is empty"); return; }
        Node t = head;
        while (t != null) { System.out.println(t.data); t = t.next; }
    }
}