package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimulationTimer {
    private long start;
    private int dayOfSimulation;
    private GregorianCalendar gc = new GregorianCalendar();

    public SimulationTimer() {

    }

//    public SimulationTimer(int dayOfSimulation, int year, int dayOfYear) {
//        this.dayOfSimulation = dayOfSimulation;
//        this.gc.set(Calendar.YEAR, year);
//        this.gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
//        this.start = System.currentTimeMillis() - 1000 * this.dayOfSimulation;
//    }

    public void start() {
        this.start = System.currentTimeMillis();
        this.gc.set(Calendar.YEAR, 2019);
        this.gc.set(Calendar.DAY_OF_YEAR, 1);
        this.dayOfSimulation = 1;
    }

    public void updateDate() {
        this.dayOfSimulation += 1;
        if (this.getDayOfYear() == gc.getActualMaximum(Calendar.DAY_OF_YEAR)) {
            this.gc.set(Calendar.YEAR, this.getYear() + 1);
            this.gc.set(Calendar.DAY_OF_YEAR, 1);
        } else {
            this.gc.set(Calendar.DAY_OF_YEAR, this.getDayOfYear() + 1);
        }
    }

    public boolean lastDayOfMonth(){
        return (getDayOfMonth()==gc.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
    public int getMonth() {
        return gc.get(Calendar.MONTH) + 1;
    }

    public int getDayOfYear() {
        return gc.get(Calendar.DAY_OF_YEAR);
    }

    public int getYear() {
        return gc.get(Calendar.YEAR);
    }

    public int getDayOfMonth() {
        return gc.get(Calendar.DAY_OF_MONTH);
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public int getDayOfSimulation() {
        return dayOfSimulation;
    }

    public void setDayOfSimulation(int dayOfSimulation) {
        this.dayOfSimulation = dayOfSimulation;
    }

    public GregorianCalendar getGc() {
        return gc;
    }

    public void setGc(GregorianCalendar gc) {
        this.gc = gc;
    }
}
