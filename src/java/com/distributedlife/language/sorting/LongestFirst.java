package com.distributedlife.language.sorting;

import java.util.Comparator;

public class LongestFirst implements Comparator<String> {
    @Override
    public int compare(String s, String s2) {
        Integer l1 = s.length();
        Integer l2 = s2.length();

        return l2.compareTo(l1);
    }
}