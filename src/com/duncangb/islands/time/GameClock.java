package com.duncangb.islands.time;

public class GameClock implements Runnable {
    private long year;
    private int day;
    private int hour;

    public GameClock(long init_year, int init_day, int init_hour) {
        this.year = init_year;
        this.day = init_day;
        this.hour = init_hour;
    }

    public GameClock() {
        this(0, 0, 0);
    }

    public void run() {
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

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("The troll is strong with this one.");
            }
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

    public boolean isNight() {
        return getHour() <= 6 || getHour() >= 21;
    }
}
