package com.epam.mentoring.sequence;

import java.util.stream.IntStream;

public class Result implements Comparable<Result> {

    private int fromIndex;
    private int toIndex;

    public Result() {
    }

    public Result(int fromIndex, int toIndex) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public int compareTo(Result o) {
        return getLength() - o.getLength();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Result result = (Result) o;

        return getLength() == result.getLength();
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public int getLength() {
        if (toIndex == 0) {
            return 0;
        }

        return (int) IntStream.range(fromIndex, toIndex + 1).count();
    }

    @Override
    public String toString() {
        return "{fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", length: " + getLength() + "}";
    }


}
