import java.util.*;


public class Main {
    static int n;

    static void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.root);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.root);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.root);
    }

    static Scanner sc = new Scanner(System.in);

    static void init(Node next, char root, char left, char right) {
        if (next.root == root) {
            if (left != '.') {
                next.left = new Node(left);
            }
            if (right != '.') {
                next.right = new Node(right);
            }
        } else {
            if (next.left != null) init(next.left, root, left, right);
            if (next.right != null) init(next.right, root, left, right);
        }
    }

    public static void main(String[] args) {
        n = sc.nextInt();
        Node head = new Node('A');
        for (int i = 0; i < n; i++) {
            char root = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);
            init(head, root, left, right);
        }

        preorder(head);
        System.out.println();
        inorder(head);
        System.out.println();
        postorder(head);
    }

}

class Node {
    char root;
    Node left = null;
    Node right = null;

    Node(char root) {
        this.root = root;
    }
}

class Coordinate implements Comparable<Coordinate> {
    int first;
    int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return first == coordinate.first && second == coordinate.second;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public int compareTo(Coordinate o) {
        return second - o.second;
    }
}
/*
6 8
1
1 3 2
1 2 3
1 4 5
2 3 2
2 5 8
3 4 2
4 5 6
5 6 1
 */