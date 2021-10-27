package com.example.practice;

import java.util.Set;

public abstract class AbstractHandler {
    private AbstractHandler next;
    private static boolean status;
    private static Set<String> oldWindowsSet;

    /**
     * returns the old tab options
     */
    public static Set<String> getOldWindowsSet() {
        return oldWindowsSet;
    }

    /**
     *
     */
    public static void setOldWindowsSet(Set<String> oldWindowsSet) {
        AbstractHandler.oldWindowsSet = oldWindowsSet;
    }

    /**
     *
     */
    public AbstractHandler link(AbstractHandler next){
        this.next = next;
        return next;
    }

    /**
     * checking for the presence of the following command
     */
    protected boolean checkNext() {
        if(next == null){
            return true;
        }
        return next.testStep();
    }

    /**
     *
     */
    public abstract boolean testStep();

    /**
     * returns the login status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * change login status
     */
    public void setStatus(boolean status) {
        AbstractHandler.status = status;
    }
}
