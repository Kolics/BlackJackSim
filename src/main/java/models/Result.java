package models;

import java.util.Date;

public class Result {
    private String sumOf;
    private int answer;
    private long time;
    long seconds;
    long milliseconds;

    public Result(String sumOf, int answer, long time) {
        this.sumOf = sumOf;
        this.answer = answer;
        this.time = time;
        this.milliseconds = time % 1000;
        this.seconds = (time - milliseconds) / 1000;
    }

    public String getSumOf() {
        return sumOf;
    }

    public int getAnswer() {
        return answer;
    }

    public long getTime() {
        return time;
    }

    public long getSeconds() {
        return seconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    @Override
    public String toString() {
        return sumOf + " = " + answer + "   " + seconds + "." + milliseconds + "s";
    }
}
