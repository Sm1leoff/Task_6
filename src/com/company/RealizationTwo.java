package com.company;

import java.util.*;

public class RealizationTwo {
    public static ArrayList<Integer> listResult (int [] arr) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        int max = -1;
        int maxValue = -1;
        for (int i = 0; i <arr.length ; i++) {
            if (map.containsKey(arr[i])) {
                ArrayList<Integer> help = lol.get(arr[i]);
                help.add(i);
                if (help.size() > max) {
                    max = help.size();
                }
                map.put(arr[i], help);
            } else {
                ArrayList<Integer> help2 = new ArrayList<>();
                help2.add(i);
                if (help2.size() > max) {
                    max = help2.size();
                }
                map.put(arr[i], help2);
            }
        }
        Set<Map.Entry<Integer, ArrayList<Integer>>> set = map.entrySet();
        ArrayList<Integer> sol = null;
        for (Map.Entry<Integer, ArrayList<Integer>> s: map.entrySet()){
            if (s.getKey() > maxValue && map.get(s.getKey()).size()==max){
                sol = s.getValue();
            }
        }
        return sol;
    }
}
