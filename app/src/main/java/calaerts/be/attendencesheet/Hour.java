package calaerts.be.attendencesheet;

public class Hour {
    private final int hour;
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
}
