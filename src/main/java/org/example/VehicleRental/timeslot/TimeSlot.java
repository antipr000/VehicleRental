package org.example.VehicleRental.timeslot;

import java.util.ArrayList;

public class TimeSlot {
    private final ArrayList<Boolean> slots;

    public TimeSlot() {
        slots = new ArrayList<>(24);
        for(int i=0; i<24; i++) slots.set(i, false);
    }

    public boolean bookSlot(int start, int end) {
        if(!checkIfAvailable(start, end)) return false;
        for(int i=start; i<=end; i++) {
            slots.set(i, false);
        }

        return true;
    }

    public boolean checkIfAvailable(int start, int end) {
        boolean isAvailable = true;
        for(int i=start; i<=end; i++) {
            if(!slots.get(i)) {
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }
}
