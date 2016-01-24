package com.framgia.lupx.rxexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
        RxView.clicks(fab)
            .subscribe(v -> Toast.makeText(MainActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show());
        Button btnRx = (Button) findViewById(R.id.btnRx);
        RxView.clicks(btnRx).subscribe(v -> distinct());
    }

    private void filterEvenNumbers() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter(i -> i % 2 == 0)
            .subscribe(i -> Log.v("Result", "Even Numbers = [ " + i + " ]"));
    }

    private void iteratingWitForEach() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .forEach(i -> Log.v("Iterating", "" + i));
    }

    public void groupBy() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .groupBy(i -> i % 2 == 0)
            .subscribe(grouped ->
                grouped.toList()
                    .subscribe(integers -> {
                        Log.v("Result", integers + "Even(" + grouped.getKey() + ")");
                    }));
    }

    public void takeFirstNElements(){
        Observable.just(1,2,3,4,5,6,7,8,9,10)
            .take(5)
            .subscribe(i->Log.v("Result",""+i));

    }

    public void first(){
        Observable.just(1,2,3,4,5,6)
            .first().subscribe(i->Log.v("Result",""+i));
    }

    public void last(){
        Observable.just(1,2,3,4,5,6)
            .last().subscribe(i->Log.v("Result",""+i));
    }

    public void distinct(){
        Observable.just(1,2,3,1,2,3,1,2)
            .distinct()
            .subscribe(i->Log.v("Result",""+i));
    }

    public void map(){
        Observable.just(1,2,3,4)
            .map(i->i*i)
            .subscribe(i->Log.v("Result",""+i));
    }

    public void reserveMap(){
        Observable.just(1,2,3,4)
            .map(i->i*i)
            .map(i->Math.sqrt(i))
            .subscribe(i->Log.v("Result",""+i));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
