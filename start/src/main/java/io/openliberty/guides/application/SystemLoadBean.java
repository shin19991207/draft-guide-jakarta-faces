package io.openliberty.guides.application;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.MemoryUsage;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("systemLoadBean")
@ApplicationScoped
public class SystemLoadBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SystemLoadData> systemLoads;

    private static final OperatingSystemMXBean OS =
        ManagementFactory.getOperatingSystemMXBean();

    private static final MemoryMXBean MEM =
        ManagementFactory.getMemoryMXBean();

    @PostConstruct
    public void init() {
        systemLoads = new ArrayList<>();
        fetchSystemLoad(); // Initial load
    }

    // Method to fetch system load data, triggered by the refresh button
    public void fetchSystemLoad() {
        double loadAverage = OS.getSystemLoadAverage();

        // Get MemoryMXBean for memory usage data
        long heapMax = MEM.getHeapMemoryUsage().getMax();
        long heapUsed = MEM.getHeapMemoryUsage().getUsed();

        // Calculate memory usage percentage
        double memoryUsage = heapUsed * 100.0 / heapMax;
        
        String time = Calendar.getInstance().getTime().toString();

        SystemLoadData data = new SystemLoadData(time, loadAverage, memoryUsage);

        // Keep only the latest 10 entries
        if (systemLoads.size() >= 10) {
            systemLoads.remove(0);
        }

        systemLoads.add(data);
    }

    // Getter for the system load data
    public List<SystemLoadData> getSystemLoads() {
        return systemLoads;
    }
}
