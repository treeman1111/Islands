package com.duncangb.islands.time;

public class GameClock {
    private long year;
    private int day;
    private int hour;

    public GameClock() {
        year = 0;
        day = 0;
        hour = 0;
    }

    public void tick() {
        while (true) {
            if (hour == 23) {
                hour = 0;
                day++;
            }

            if (day == 364) {
                day = 0;
                year++;
            }

            hour++;
        }
    }

    public long getYear() {
        return this.year;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }
}
