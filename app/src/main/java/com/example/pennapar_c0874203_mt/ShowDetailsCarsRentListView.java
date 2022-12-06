package com.example.pennapar_c0874203_mt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.pennapar_c0874203_mt.databinding.ActivityShowDetailsCarsRentListViewBinding;

public class ShowDetailsCarsRentListView extends AppCompatActivity {

    private ActivityShowDetailsCarsRentListViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDetailsCarsRentListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //This is simple way to show list view
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, UserCar.selectedCarList);
        binding.list.setAdapter(adapter);
        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserCar.selectedCarList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
