package com.duncangb.islands.time;

public class GameClock {
    private long year;
    private int day;
    private int hour;
    private int minute;

    public GameClock() {
        year = 0;
        day = 0;
        hour = 0;
        minute = 0;
    }

    public void tick() {
        if(minute == 59) {
            minute = 0;
            hour++;
        }

        if(hour == 23) {
            hour = 0;
            day++;
        }

        if(day == 364) {
            day = 0;
            year++;
        }

        minute++;
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

    public boolean isNight() {
        return this.hour > 20 || this.hour < 6;
    }

    @Override
    public String toString() {
        return String.format("%d:%d, %d, %d", minute, hour, day, year);
    }
}
