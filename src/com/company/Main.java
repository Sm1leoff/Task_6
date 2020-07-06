package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String args[]) {

        String vs[] = new String[args.length];
        for (int v = 0; v < args.length; v++) {
            vs[v] = args[v];
        }
        new Interface(vs);
    }
}