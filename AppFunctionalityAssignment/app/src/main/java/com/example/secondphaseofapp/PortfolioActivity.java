package com.example.secondphaseofapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.secondphaseofapp.utils.ResumeUtils;

public class PortfolioActivity extends AppCompatActivity {

    private final double MY_STOCKS_MONEY = 500;
    private final double MY_BONDS_MONEY = 1000;
    private final double MY_PROPERTY_MONEY = 2000;
    private final double MY_CASH = 3000;

    private final double PARENTS_STOCKS_MONEY = 10000;
    private final double PARENTS_BONDS_MONEY = 50000;
    private final double PARENTS_PROPERTY_MONEY = 300000;
    private final double PARENTS_CASH = 20000;

    private TextView my_stocks_val, my_stocks_prop;
    private TextView my_bonds_val, my_bonds_prop;
    private TextView my_property_val, my_property_prop;
    private TextView my_cash_val, my_cash_prop;

    private TextView parents_stocks_val, parents_stocks_prop;
    private TextView parents_bonds_val, parents_bonds_prop;
    private TextView parents_property_val, parents_property_prop;
    private TextView parents_cash_val, parents_cash_prop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        my_stocks_val = (TextView) findViewById(R.id.my_investments_stocks_money);
        my_stocks_prop = (TextView) findViewById(R.id.my_investments_stocks_proportion);
        my_bonds_val = (TextView) findViewById(R.id.my_investments_bonds_money);
        my_bonds_prop = (TextView) findViewById(R.id.my_investments_bonds_proportion);
        my_property_val = (TextView) findViewById(R.id.my_investments_property_money);
        my_property_prop = (TextView) findViewById(R.id.my_investments_property_proportion);
        my_cash_val = (TextView) findViewById(R.id.my_investments_cash_money);
        my_cash_prop = (TextView) findViewById(R.id.my_investments_cash_proportion);

        parents_stocks_val = (TextView) findViewById(R.id.parents_investments_stocks_money);
        parents_stocks_prop = (TextView) findViewById(R.id.parents_investments_stocks_proportion);
        parents_bonds_val = (TextView) findViewById(R.id.parents_investments_bonds_money);
        parents_bonds_prop = (TextView) findViewById(R.id.parents_investments_bonds_proportion);
        parents_property_val = (TextView) findViewById(R.id.parents_investments_property_money);
        parents_property_prop = (TextView) findViewById(R.id.parents_investments_property_proportion);
        parents_cash_val = (TextView) findViewById(R.id.parents_investments_cash_money);
        parents_cash_prop = (TextView) findViewById(R.id.parents_investments_cash_proportion);

        setMyFields();
        setParentsFields();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_portfolio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_menu_item) {
            Intent intent = new Intent(this, MainActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else if (id == R.id.weather_menu_item) {
            Intent intent = new Intent(this, WeatherActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else if (id == R.id.places_facts_menu_item) {
            Intent intent = new Intent(this, LocationFactsActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        else if (id == R.id.resume_menu_item) {
            Intent intent = new Intent(Intent.ACTION_VIEW, ResumeUtils.getResumeUri(this));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            else {
                Log.d("PortfolioActivity", "Resume failed to open.");
            }
        }
        else if (id == R.id.bonus_facts_menu_item) {
            Intent intent = new Intent(this, BonusFactsActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setMyFields() {
        double total = MY_STOCKS_MONEY + MY_BONDS_MONEY + MY_PROPERTY_MONEY + MY_CASH;
        double stocks_prop = MY_STOCKS_MONEY / total;
        double bonds_prop = MY_BONDS_MONEY / total;
        double prop_prop = MY_PROPERTY_MONEY / total;
        double cash_prop = MY_CASH / total;

        my_stocks_val.setText(String.format("$%,.2f", MY_STOCKS_MONEY));
        my_stocks_prop.setText(String.format("%.2f%%", stocks_prop));

        my_bonds_val.setText(String.format("$%,.2f", MY_BONDS_MONEY));
        my_bonds_prop.setText(String.format("%.2f%%", bonds_prop));

        my_property_val.setText(String.format("$%,.2f", MY_PROPERTY_MONEY));
        my_property_prop.setText(String.format("%.2f%%", prop_prop));

        my_cash_val.setText(String.format("$%,.2f", MY_CASH));
        my_cash_prop.setText(String.format("%.2f%%", cash_prop));

    }

    protected void setParentsFields() {
        double total = PARENTS_STOCKS_MONEY + PARENTS_BONDS_MONEY + PARENTS_PROPERTY_MONEY + PARENTS_CASH;
        double stocks_prop = PARENTS_STOCKS_MONEY / total;
        double bonds_prop = PARENTS_BONDS_MONEY / total;
        double prop_prop = PARENTS_PROPERTY_MONEY / total;
        double cash_prop = PARENTS_CASH / total;

        parents_stocks_val.setText(String.format("$%,.2f", PARENTS_STOCKS_MONEY));
        parents_stocks_prop.setText(String.format("%.2f%%", stocks_prop));

        parents_bonds_val.setText(String.format("$%,.2f", PARENTS_BONDS_MONEY));
        parents_bonds_prop.setText(String.format("%.2f%%", bonds_prop));

        parents_property_val.setText(String.format("$%,.2f", PARENTS_PROPERTY_MONEY));
        parents_property_prop.setText(String.format("%.2f%%", prop_prop));

        parents_cash_val.setText(String.format("$%,.2f", PARENTS_CASH));
        parents_cash_prop.setText(String.format("%.2f%%", cash_prop));

    }
}