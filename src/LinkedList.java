import java.io.*;

/**
 * Custom Singly LinkedList Implementation
 * Data Structure: LinkedList (Dynamic Data Structure)
 *
 * Used for:
 * - Storing file lines during manipulation
 * - Eliminating duplicate lines
 * - Merging file contents
 * - Reversing file content (with Stack)
 */
class LinkedList {

    // Inner Node class
    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    Node Head = null;

    // Add node at the beginning
    void addFirst(String data) {
        Node n = new Node(data);
        if (Head == null) {
            Head = n;
        } else {
            n.next = Head;
            Head = n;
        }
    }

    // Add node at the end
    void addLast(String data) {
        Node n = new Node(data);
        if (Head == null) {
            Head = n;
        } else {
            Node Temp = Head;
            while (Temp.next != null) {
                Temp = Temp.next;
            }
            Temp.next = n;
        }
    }

    // Delete first node
    void delFirst() {
        if (Head == null) {
            System.out.println("List is empty");
        } else {
            Node del = Head;
            Head = Head.next;
            del.next = null;
        }
    }

    // Delete first node and return its data
    String deletefirstString() {
        if (Head == null) {
            return null;
        } else {
            Node del = Head;
            Head = Head.next;
            del.next = null;
            return del.data;
        }
    }

    // Display all nodes
    void display() {
        if (Head == null) {
            System.out.println("List is empty");
        } else {
            Node Temp = Head;
            while (Temp != null) {
                System.out.println(Temp.data);
                Temp = Temp.next;
            }
        }
    }

    // Check if list is empty
    Boolean isEmpty() {
        return (Head == null);
    }

    // Get size of the list
    int Size() {
        if (Head == null) {
            return 0;
        } else {
            Node Temp = Head;
            int Count = 0;
            while (Temp != null) {
                Count++;
                Temp = Temp.next;
            }
            return Count;
        }
    }

    // Check if list contains a specific string
    boolean contains(String data) {
        Node Temp = Head;
        while (Temp != null) {
            if (Temp.data.equals(data)) {
                return true;
            }
            Temp = Temp.next;
        }
        return false;
    }

    // Reverse file content using Stack and write back to file
    void reverseAndWriteToFile(String inputPath) {
        try {
            Stack stack = new Stack(TextFileManipulator.SizeOfFile);
            BufferedReader Reader = new BufferedReader(new FileReader(inputPath));

            String Line;
            while ((Line = Reader.readLine()) != null) {
                stack.push(Line);
            }
            Reader.close();

            BufferedWriter Writer = new BufferedWriter(new FileWriter(inputPath));
            Writer.close();

            BufferedWriter ContantWriter = new BufferedWriter(new FileWriter(inputPath));
            while (!stack.isEmpty()) {
                String reversedLine = stack.pop();
                ContantWriter.write(reversedLine);
                ContantWriter.newLine();
            }
            ContantWriter.close();

            System.out.println("File modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
