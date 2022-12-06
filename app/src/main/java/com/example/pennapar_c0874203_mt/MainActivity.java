package com.example.pennapar_c0874203_mt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.pennapar_c0874203_mt.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final double TAX = 0.13;

    private ActivityMainBinding binding;
    private ArrayList<Car> cars = new ArrayList<>();
    private Car mySelection;
    int count = 0;
    private int radioCheckedId;
    private boolean isGPS = false;
    private boolean isChildSeat = false;
    private boolean isUnlimit = false;
    int userAge;
    double priceTotal;
    double priceDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addCars();
        radioCheckedId = R.id.radio20;
        setAge();

        //SPINNER
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, cars);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Car car = (Car) adapterView.getSelectedItem();
                mySelection = car;
                binding.dailyRent.setText(""+car.getDailyRentPrice());
                calculateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //SEEK BAR
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.daysTV.setText(""+progress+ " Day(s)");
                count = seekBar.getProgress();
                calculateTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //RADIO
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioCheckedId = checkedId;
                setAge();
                calculateTotal();
            }
        });

        //CHECKBOXS
        binding.checkboxGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isGPS = b;
                calculateTotal();
            }
        });

        binding.checkboxChildSeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isChildSeat = b;
                calculateTotal();
            }
        });

        binding.checkboxUnlimit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isUnlimit = b;
                calculateTotal();
            }
        });

        //VIEW DETAILE BUTTON
        binding.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count < 1){
                    Snackbar.make(binding.getRoot(),"Quantity should be more than 0",Snackbar.LENGTH_SHORT).show();
                    return;
                } else {
                    //go to next screen
                    UserCar.selectedCarList.add(new UserCar(
                            mySelection.getModel(),
                            userAge,
                            count,
                            isGPS,
                            isChildSeat,
                            isUnlimit,
                            priceDays,
                            priceTotal
                            ));
                    goToShowDetailsCarsRentListView();
                }
            }
        });
    }

    private void goToShowDetailsCarsRentListView() {
        Intent intent = new Intent(this, ShowDetailsCarsRentListView.class);
        startActivity(intent);
    }

    private void setAge() {
        if (radioCheckedId == R.id.radio20) {
            userAge = 20;
        } else if (radioCheckedId == R.id.radio60) {
            userAge = 60;
        } else {
            userAge = 30;
        }
    }

    private void calculateTotal() {
        priceDays = mySelection.getDailyRentPrice() * count;
        if (userAge <= 20) {
            priceDays += (5*count);
        } else if (userAge >= 60) {
            priceDays -= 10;
        }

        if (isGPS) {
            priceDays += 5;
        }

        if (isChildSeat) {
            priceDays += 7;
        }

        if (isUnlimit) {
            priceDays += 10;
        }

        double tax = priceDays * TAX;
        priceTotal = priceDays + tax;

        binding.amount.setText(""+priceDays);
        binding.totalPayment.setText(""+priceTotal);
    }

    private void addCars() {
        cars.add(new Car("BMW", 50));
        cars.add(new Car("Audi", 100));
        cars.add(new Car("Honda", 150));
        cars.add(new Car("Tesla", 200));
        cars.add(new Car("Toyota", 250));
        cars.add(new Car("Volks Wagon", 300));
        cars.add(new Car("Mercedes", 350));
        cars.add(new Car("Yamaha", 400));
    }
}