package com.example.demo.service;

import com.example.demo.model.ProcessedDNA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DNACheckingService {

    public List<List<String>> Check(String[] sequences, int threshold) {
        List<String> unique = new ArrayList<>();
        List<String> duplicate = new ArrayList<>();

        List<ProcessedDNA> dnaList = Arrays.stream(sequences).map(seq -> this.Process(seq))
                .collect(Collectors.toList());
        dnaList.forEach(dna -> {
            List<ProcessedDNA> checkList = new ArrayList<>(dnaList);
            checkList.remove(dna);
            checkList = checkList.stream().filter(row -> this.Compare(dna.getCharCount(), row.getCharCount()) <= threshold)
                    .collect(Collectors.toList());
            final Integer[] duplicateCount = { 0 };
            checkList.forEach(row -> {
                if (CheckDuplicate(dna.getSequence(), row.getSequence(), threshold)) {
                    AddToResult(duplicate, dna.getSequence());
                    AddToResult(duplicate, row.getSequence());
                    duplicateCount[0]++;
                }
            });
            if (duplicateCount[0] == 0) {
                AddToResult(unique, dna.getSequence());
            }
        });
        return List.of(unique, duplicate);
    }

    private ProcessedDNA Process(String seq) {
        long[] count = {seq.chars().filter(ch -> ch == 'A').count(),
                        seq.chars().filter(ch -> ch == 'T').count(),
                        seq.chars().filter(ch -> ch == 'C').count(),
                        seq.chars().filter(ch -> ch == 'G').count()};
        ProcessedDNA result = new ProcessedDNA();
        result.setSequence(seq);
        result.setCharCount(count);
        return result;
    }

    private long Compare(long[] a, long[] b) {
        long ADiff = Math.abs(a[0] - b[0]);
        long TDiff = Math.abs(a[1] - b[1]);
        long CDiff = Math.abs(a[2] - b[2]);
        long GDiff = Math.abs(a[3] - b[3]);
        return (ADiff + TDiff + CDiff + GDiff) / 2;
    }

    private boolean CheckDuplicate(String a, String b, int threshold) {
        int length = a.length();
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int diffCount = 0;
        for (int i = 0; length > i; i++) {
            if (aChars[i] != bChars[i])
                diffCount += 1;
        }
        return diffCount <= threshold;
    }

    private List<String> AddToResult(List<String> list, String seq) {
        if (!list.contains(seq)) {
            list.add(seq);
        }
        return list;
    }
}
