import java.util.*;
import java.io.*;

/**
 * =========================================================
 *         TEXT FILE MANIPULATOR
 *         Data Structures Project — Java
 * =========================================================
 *
 * Features:
 *  1. Eliminate repeated lines
 *  2. Reverse file content
 *  3. Insert new line
 *  4. Copy a line
 *  5. Paste a line
 *  6. Cut a line
 *  7. Sort file content
 *  8. Merge two files
 *
 * Data Structures Used:
 *  - Custom Stack  (clipboard, reverse)
 *  - Custom LinkedList (line storage, manipulation)
 *  - Array         (sorting)
 *  - RandomAccessFile (initial file reading)
 */
class TextFileManipulator {
    static Scanner sc = new Scanner(System.in);
    static int SizeOfFile;
    static File files;
    static LinkedList ContantOfList;
    static String Lines[];
    static int indexno = 0;
    static Stack ClipBoard;
    static boolean Answer = false;

    public static void main(String[] args) throws Exception {
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(
                "                                           T E X T   F I L E   M A N I P U L A T O R                                                    ");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Enter File Name in which you want changes : ");
        String Name = sc.next();
        String Path = Name + ".txt";
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------------------------");

        files = new File(Path);
        checkname(Name);
        if (Answer) {
            System.exit(0);
        }

        RandomAccessFile Randomfile = new RandomAccessFile(files, "rw");
        int Index = Randomfile.read();
        while (Index != -1) {
            System.out.print((char) Index);
            Index = Randomfile.read();
        }
        System.out.println();
        System.out.println("Length of File : " + Randomfile.length());

        SizeOfFile = (int) Randomfile.length();
        Randomfile.close();

        Lines = new String[SizeOfFile];
        ContantOfList = new LinkedList();
        ClipBoard = new Stack(SizeOfFile);

        int x;
        do {
            try {
                System.out.println(
                        "----------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(" PRESS :\r \n" +
                        "(1). Eliminate repeated Lines from the file \r\n" +
                        "(2). Reverse the content of file \r\n" +
                        "(3). Insert new Line \r\n" +
                        "(4). Copy text \r\n" +
                        "(5). Paste text Line\r\n" +
                        "(6). Cut the Line \r\n" +
                        "(7). Sort the content of the file\r\n" +
                        "(8). Write one file content to another file \r\n" +
                        "(9). Exit");

                x = sc.nextInt();
                System.out.println("Selected option: " + x);

                switch (x) {
                    case 1: eliminateRepeatedLine(Path); break;
                    case 2: reverseContent(Path); break;
                    case 3: addNewLine(Path); break;
                    case 4: copytext(Path); break;
                    case 5: pastetext(Path); break;
                    case 6: cuttext(Path); break;
                    case 7: sortcontant(Path); break;
                    case 8: mergeContent(Path); break;
                    case 9:
                        System.out.println("---------------------------------------------------------------------------------------------------");
                        System.out.println("Thank You !!..");
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Exiting due to Ctrl+C");
                break;
            }
        } while (x != 9);
    }

    // -------------------------------------------------------
    // Check if file exists
    // -------------------------------------------------------
    private static void checkname(String Name) {
        String Path = Name + ".txt";
        File file = new File(Path);
        if (!file.exists()) {
            System.out.println("File does not exist : " + Path);
            System.out.println("Do you want to re-enter name : yes/no");
            String Choice = sc.next();
            if (Choice.equalsIgnoreCase("NO")) {
                Answer = true;
                return;
            } else if (Choice.equalsIgnoreCase("YES")) {
                System.out.println("Enter name :");
                String name = sc.next();
                checkname(name);
            } else {
                System.out.println("Invalid Input :");
                Answer = true;
            }
        }
    }

    // -------------------------------------------------------
    // 1. Eliminate Repeated Lines
    //    DS Used: LinkedList (contains() for duplicate check)
    // -------------------------------------------------------
    private static void eliminateRepeatedLine(String Path) throws Exception {
        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist : " + Path);
            return;
        }
        BufferedReader Reader = new BufferedReader(new FileReader(Path));
        String Line;
        while ((Line = Reader.readLine()) != null) {
            if (!ContantOfList.contains(Line)) {
                ContantOfList.addLast(Line);
            }
        }
        Reader.close();

        BufferedWriter Writer = new BufferedWriter(new FileWriter(Path));
        Writer.close();

        File Size = new File(Path);
        System.out.println("Length of file: " + Size.length());

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
        while (!ContantOfList.isEmpty()) {
            ContantWriter.write(ContantOfList.deletefirstString());
            ContantWriter.newLine();
        }
        ContantWriter.flush();
        ContantWriter.close();
        System.out.println("File Modified Successfully.");
    }

    // -------------------------------------------------------
    // 2. Reverse Content
    //    DS Used: Stack (LIFO reversal), LinkedList
    // -------------------------------------------------------
    private static void reverseContent(String Path) {
        ContantOfList.reverseAndWriteToFile(Path);
    }

    // -------------------------------------------------------
    // 3. Add New Line
    //    DS Used: Stack (to insert line after target word)
    // -------------------------------------------------------
    private static void addNewLine(String Path) throws Exception {
        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        long Size = OriginalFile.length();
        Stack OriginalLines = new Stack((int) Size);
        Stack ModifiedLines = new Stack((int) Size);

        System.out.println("Enter the target word where you want to add Lines:");
        sc.nextLine();
        String TargetWord = sc.nextLine();

        System.out.println("Enter the new Line to replace with:");
        String NewLineToReplace = sc.nextLine();

        BufferedReader Reader = new BufferedReader(new FileReader(OriginalFile));
        String Line;
        while ((Line = Reader.readLine()) != null) {
            OriginalLines.push(Line);
        }
        Reader.close();

        while (!OriginalLines.isEmpty()) {
            String CurrentLine = OriginalLines.pop();
            if (CurrentLine.contains(TargetWord)) {
                ModifiedLines.push(NewLineToReplace);
            }
            ModifiedLines.push(CurrentLine);
        }

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(OriginalFile));
        while (!ModifiedLines.isEmpty()) {
            ContantWriter.write(ModifiedLines.pop());
            ContantWriter.newLine();
        }
        ContantWriter.flush();
        ContantWriter.close();
        System.out.println("File modified successfully.");
    }

    // -------------------------------------------------------
    // 4. Copy Text
    //    DS Used: Stack (clipboard), peekindex()
    // -------------------------------------------------------
    private static void copytext(String Path) throws Exception {
        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        long Size = OriginalFile.length();
        Stack OriginalLines = new Stack((int) Size);
        ClipBoard.clearstack();
        ClipBoard.display();

        BufferedReader SourceReader = new BufferedReader(new FileReader(Path));
        String Line;
        while ((Line = SourceReader.readLine()) != null) {
            OriginalLines.push(Line);
        }
        SourceReader.close();

        System.out.println("Enter line number you want to copy:");
        int CopyLineNumber = sc.nextInt();
        ClipBoard.push(OriginalLines.peekindex(OriginalLines.stacksize() - (CopyLineNumber - 1)));

        System.out.println("Stack:");
        ClipBoard.display();
        System.out.println("Copied Successfully!!");
    }

    // -------------------------------------------------------
    // 5. Paste Text
    //    DS Used: LinkedList (line insertion), Stack (clipboard)
    // -------------------------------------------------------
    private static void pastetext(String Path) throws Exception {
        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        System.out.println("Enter Line number where do you want to paste Text:");
        int PasteLineNo = sc.nextInt();
        if (PasteLineNo <= 0) {
            System.out.println("Invalid Number");
            return;
        }

        LinkedList CopyFile = new LinkedList();
        BufferedReader SourceReader = new BufferedReader(new FileReader(Path));
        String Line;
        int Count = 1;
        while ((Line = SourceReader.readLine()) != null) {
            if (Count == PasteLineNo) {
                CopyFile.addLast(ClipBoard.peek());
            }
            CopyFile.addLast(Line);
            Count++;
        }
        SourceReader.close();

        BufferedWriter DemoWriter = new BufferedWriter(new FileWriter(Path));
        DemoWriter.close();

        System.out.println("Linked List Before:");
        CopyFile.display();

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
        String AddLines = CopyFile.deletefirstString();
        while (AddLines != null) {
            ContantWriter.write(AddLines);
            ContantWriter.newLine();
            AddLines = CopyFile.deletefirstString();
        }
        ContantWriter.close();

        System.out.println("\nLinked List After:");
        CopyFile.display();
        System.out.println("Pasted Successfully!!");
    }

    // -------------------------------------------------------
    // 6. Cut Text
    //    DS Used: Stack (clipboard), LinkedList (remaining lines)
    // -------------------------------------------------------
    private static void cuttext(String Path) throws Exception {
        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }
        System.out.println("Line no:");
        int CutLineNo = sc.nextInt();
        if (CutLineNo <= 0) {
            System.out.println("Invalid no");
            return;
        }

        ClipBoard.clearstack();
        ClipBoard.display();

        LinkedList List = new LinkedList();
        BufferedReader Reader = new BufferedReader(new FileReader(Path));
        String Line;
        int Count = 1;
        while ((Line = Reader.readLine()) != null) {
            if (Count != CutLineNo) {
                List.addLast(Line);
            } else {
                ClipBoard.push(Line);
            }
            Count++;
        }
        Reader.close();

        BufferedWriter DemoWriter = new BufferedWriter(new FileWriter(Path));
        DemoWriter.close();

        System.out.println("Linked List Before:");
        List.display();

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
        String AddLines = List.deletefirstString();
        while (AddLines != null) {
            ContantWriter.write(AddLines);
            ContantWriter.newLine();
            AddLines = List.deletefirstString();
        }
        ContantWriter.close();

        System.out.println("\nLinked List After:");
        List.display();
        System.out.println("\nLine Cutted Successfully!!");
    }

    // -------------------------------------------------------
    // 7. Sort Content
    //    DS Used: Array + Bubble Sort (String compareTo)
    // -------------------------------------------------------
    private static void sortcontant(String Path) throws Exception {
        File OriginalFile = new File(Path);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path);
            return;
        }

        BufferedReader SourceReader = new BufferedReader(new FileReader(Path));
        String Line;
        String Name[] = new String[SizeOfFile];
        int Count = 0;
        while ((Line = SourceReader.readLine()) != null) {
            Name[Count] = Line;
            Count++;
        }
        SourceReader.close();

        System.out.println("Old array:");
        for (int i = 0; i < Count; i++) System.out.println(Name[i]);

        // Bubble Sort
        String Temp;
        for (int i = 0; i < Count; i++) {
            for (int j = i + 1; j < Count; j++) {
                if (Name[i].compareTo(Name[j]) > 0) {
                    Temp = Name[i];
                    Name[i] = Name[j];
                    Name[j] = Temp;
                }
            }
        }

        System.out.println("\nNew array (sorted):");
        for (int i = 0; i < Count; i++) System.out.println(Name[i]);

        BufferedWriter Demowriter = new BufferedWriter(new FileWriter(Path));
        Demowriter.close();

        BufferedWriter Writer = new BufferedWriter(new FileWriter(Path));
        for (int i = 0; i < Count; i++) {
            Writer.write(Name[i]);
            Writer.newLine();
        }
        Writer.close();
        System.out.println("File modified successfully!!");
    }

    // -------------------------------------------------------
    // 8. Merge File Content
    //    DS Used: LinkedList (appending two files)
    // -------------------------------------------------------
    private static void mergeContent(String Path) throws Exception {
        sc.nextLine();
        System.out.println("Enter source file name (which file data you want to add): ");
        String Path1 = sc.nextLine();
        String Path2 = Path1 + ".txt";
        File OriginalFile = new File(Path2);
        if (!OriginalFile.exists()) {
            System.out.println("File does not exist: " + Path2);
            return;
        }

        LinkedList List = new LinkedList();
        BufferedReader Reader = new BufferedReader(new FileReader(Path));
        String OriginalLine;
        while ((OriginalLine = Reader.readLine()) != null) {
            List.addLast(OriginalLine);
        }
        Reader.close();

        BufferedReader SourceReader = new BufferedReader(new FileReader(Path2));
        String Line;
        while ((Line = SourceReader.readLine()) != null) {
            List.addLast(Line);
        }
        SourceReader.close();

        BufferedWriter Demowriter = new BufferedWriter(new FileWriter(Path));
        Demowriter.close();

        System.out.println("Linked List Before:");
        List.display();

        BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(Path));
        String AddLines = List.deletefirstString();
        while (AddLines != null) {
            ContantWriter.write(AddLines);
            ContantWriter.newLine();
            AddLines = List.deletefirstString();
        }
        ContantWriter.close();

        System.out.println("\nLinked List After:");
        List.display();
    }
}
