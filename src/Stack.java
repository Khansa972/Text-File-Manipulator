/**
 * Custom Stack Implementation using Array
 * Data Structure: Stack (LIFO - Last In First Out)
 * 
 * Used for:
 * - Clipboard operations (copy, cut, paste)
 * - Reversing file content
 */
class Stack {
    String[] a;
    int top;

    Stack(int Size) {
        this.a = new String[Size];
        top = -1;
    }

    // Push element onto stack
    void push(String x) {
        if (top >= a.length - 1)
            System.out.println("overflow");
        else
            a[++top] = x;
    }

    // Pop element from stack
    String pop() {
        if (top == -1) {
            System.out.println("underflow");
            return null;
        } else {
            return a[top--];
        }
    }

    // Display all elements
    void display() {
        if (top == -1) {
            System.out.println("Stack is empty!!");
        } else {
            for (int Index = top; Index >= 0; Index--) {
                System.out.println(a[Index] + " ");
            }
        }
    }

    // Returns size of stack
    int stacksize() {
        if (top == -1) {
            return 0;
        } else {
            int Count = 0;
            for (int Index = top; Index >= 0; Index--) {
                Count++;
            }
            return Count;
        }
    }

    // Check if stack is empty
    boolean isEmpty() {
        return (top == -1);
    }

    // Peek top element
    String peek() {
        if (top == -1)
            return null;
        else
            return (a[top]);
    }

    // Peek at a specific index
    String peekindex(int Index) {
        if (top - Index + 1 <= -1) {
            return null;
        } else {
            return (a[top - Index + 1]);
        }
    }

    // Clear the stack
    boolean clearstack() {
        if (top == -1) {
            return true;
        } else {
            while (top != -1) {
                pop();
            }
            return true;
        }
    }
}
