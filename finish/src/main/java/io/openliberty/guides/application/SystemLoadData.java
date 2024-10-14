package io.openliberty.guides.application;

public class SystemLoadData {
    private String time;
    private Double loadAverage;
    private Double memoryUsage;

    public SystemLoadData(String time, Double loadAverage, Double memoryUsage) {
        this.time = time;
        this.loadAverage = loadAverage;
        this.memoryUsage = memoryUsage;
    }

    public String getTime() {
        return time;
    }

    public Double getLoadAverage() {
        return loadAverage;
    }

    public Double getMemoryUsage() {
        return memoryUsage;
    }
}
