package calaerts.be.attendencesheet.activities.klas.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.activities.klas.newKlas.NewKlasActivity;
import calaerts.be.attendencesheet.activities.klas.list.ClassListActivity;

public class ClassDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ClassDetailFragment.KLAS_ID,
                    getIntent().getStringExtra(ClassDetailFragment.KLAS_ID));
            ClassDetailFragment fragment = new ClassDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.class_detail_container, fragment)
                    .commit();
        }
    }

    private void setupViews() {
        setContentView(R.layout.activity_class_detail);
        FloatingActionButton fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewKlasActivity();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void startNewKlasActivity() {
        Intent intent = new Intent(ClassDetailActivity.this, NewKlasActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ClassListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
