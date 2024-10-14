package io.openliberty.guides.application;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;

// tag::namedAnnotation[]
@Named("systemLoadBean")
// end::namedAnnotation[]
// tag::applicationScopedAnnotation[]
@ApplicationScoped
// end::applicationScopedAnnotation[]
// tag::systemLoadBean[]
public class SystemLoadBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SystemLoadData> systemLoads;

    private static final OperatingSystemMXBean OS =
        ManagementFactory.getOperatingSystemMXBean();

    private static final MemoryMXBean MEM =
        ManagementFactory.getMemoryMXBean();

    // tag::postConstruct[]
    @PostConstruct
    // end::postConstruct[]
    // tag::init[]
    public void init() {
        systemLoads = new ArrayList<>();
        fetchSystemLoad();
    }
    // end::init[]

    // tag::fetchSystemLoadMethod[]
    public void fetchSystemLoad() {
        double loadAverage = OS.getSystemLoadAverage();

        // Get MemoryMXBean for memory usage data
        long heapMax = MEM.getHeapMemoryUsage().getMax();
        long heapUsed = MEM.getHeapMemoryUsage().getUsed();

        // Calculate memory usage percentage
        double memoryUsage = heapUsed * 100.0 / heapMax;

        String time = Calendar.getInstance().getTime().toString();

        SystemLoadData data = new SystemLoadData(time, loadAverage, memoryUsage);

        systemLoads.add(data);
    }
    // end::fetchSystemLoadMethod[]

    // tag::getSystemLoads[]
    public List<SystemLoadData> getSystemLoads() {
        return systemLoads;
    }
    // end::getSystemLoads[]
}
// end::systemLoadBean[]
