package com.balu.komalexclusivenews.android_concepts.RXJava;

import java.util.Comparator;

public class Task implements Comparable<Task> {

    private String description;
    private boolean isComplete;
    private int priority;

    public Task(String description, boolean isComplete, int priority) {
        this.description = description;
        this.isComplete = isComplete;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        return this.description .compareTo( o.description);


    }
    // getter and setters ...

}