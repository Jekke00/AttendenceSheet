package calaerts.be.attendancesheet.model;

import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

public class Hour implements Comparable<Hour> {
    private final int hour;
    @Ignore
    private boolean selected;

    public Hour(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hour hour1 = (Hour) o;

        return hour == hour1.hour;

    }

    @Override
    public int hashCode() {
        return hour;
    }


    @Override
    public int compareTo(@NonNull Hour hour) {
        return Integer.compare(getHour(), hour.getHour());
    }
}
