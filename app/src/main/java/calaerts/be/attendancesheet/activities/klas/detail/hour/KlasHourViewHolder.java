package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.view.View;
import android.widget.CheckBox;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.Hour;

public class KlasHourViewHolder extends HourViewHolder {
    private final CheckBox checkBox;

    public KlasHourViewHolder(View view) {
        super(view);
        checkBox = view.findViewById(R.id.checkBox);
    }


    @Override
    public void setHour(final Hour hour, final OnHourListInteraction onHourListInteraction) {
        this.checkBox.setText(Integer.toString(hour.getHour()));
        this.checkBox.setChecked(hour.isSelected());

        this.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onHourListInteraction) {
                    onHourListInteraction.onListFragmentInteraction(hour);
                }
            }
        });
    }

    @Override
    public String toString() {
        return super.toString() + " '" + checkBox.getText() + "'";
    }
}
