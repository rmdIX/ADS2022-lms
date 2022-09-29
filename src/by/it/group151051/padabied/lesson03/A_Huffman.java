package by.it.group151051.padabied.lesson03;

import java.io.*;
import java.util.*;

public class A_Huffman {

    public ArrayList<Node> nodeList = new ArrayList<>();

    public class Node implements Comparable<Node> {
        int freq;
        char letter;
        Node left;
        Node right;
        String str;


        @Override
        public int compareTo(Node o) {
            if (this.freq != o.freq) return Integer.compare(freq, o.freq);
            else return Character.compare(o.letter, this.letter);
        }

        public Node(char x, int freq) {
            this.freq = freq;
            this.letter = x;
        }

        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.freq = left.freq + right.freq;
            this.str = String.valueOf(left.letter) + String.valueOf(right.letter);
        }
    }

    public int getFreq(String str, char x) {
        int cnt = 0;
        for (char y : str.toCharArray()) {
            if (y == x) cnt++;
        }
        return cnt;
    }

    public String encode(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String str = reader.readLine();
        for (char x : str.toCharArray()) {
            boolean free = true;
            for (Node node : nodeList) {
                if (node.letter == x) {
                    free = false;
                    break;
                }
            }
            if (free) {
                nodeList.add(new Node(x, getFreq(str, x)));
            }
        }
        Collections.sort(nodeList);
        LinkedList<Node> linked = new LinkedList<>(nodeList);
        Node root = null;

        while (linked.peek() != null) {
            Node first = linked.poll();
            Node second = linked.poll();
            if (second == null && linked.peek() != null) {
                linked.offer(first);
            } else if (second == null && linked.peek() == null) {
                root = first;
                break;
            } else {
                assert second != null;
                Node newNode = new Node(second, first);
                linked.offer(newNode);
            }
        }
        Map<Character, String> code = new HashMap<>();

        for (int i = 0; i < nodeList.size(); i++) {
            char curr = nodeList.get(i).letter;
            Node currNode = root;
            StringBuilder resultString = new StringBuilder();

            while (true) {
                if (currNode.left.letter == curr) {
                    resultString.append("0");
                    code.put(curr, resultString.toString());
                    break;
                } else if (currNode.right.letter == curr) {
                    resultString.append("1");
                    code.put(curr, resultString.toString());
                    break;
                }

                if (currNode.left.str.contains(String.valueOf(curr))) {
                    resultString.append("0");
                    currNode = currNode.left;
                } else if (currNode.right.str.contains(String.valueOf(curr))) {
                    resultString.append("1");
                    currNode = currNode.right;
                }
            }
        }

        for (Map.Entry<Character, String> x : code.entrySet()) {
            if (!x.getValue().contains(String.valueOf("1"))) code.put(x.getKey(), "0");
        }

        StringBuilder result = new StringBuilder();
        for (char x : str.toCharArray()) {
            result.append(code.get(x));
        }

        System.out.println(nodeList.size() + " " +  result.toString().length() + "\n");

        for (Map.Entry<Character, String> x : code.entrySet()) {
            System.out.println(x.getKey() + ": " + x.getValue());
        }

        System.out.println("\n" + result.toString());
        return result.toString();
    }
}
