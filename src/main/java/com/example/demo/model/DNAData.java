package com.example.demo.model;

public class DNAData {

    private String sequences;
    private int threshold;

    public DNAData(String sequences, int threshold) {
        this.sequences = sequences;
        this.threshold = threshold;
    }

    public String getSequences() {
        return sequences;
    }

    public void setSequences(String sequences) {
        this.sequences = sequences;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "DNAData{" +
                "sequences='" + sequences + '\'' +
                ", threshold=" + threshold +
                '}';
    }
}
