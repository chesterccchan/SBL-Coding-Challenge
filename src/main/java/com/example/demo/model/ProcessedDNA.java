package com.example.demo.model;

public class ProcessedDNA {

    private String sequence;
    private long[] CharCount;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public long[] getCharCount() {
        return CharCount;
    }

    public void setCharCount(long[] charCount) {
        CharCount = charCount;
    }
}
