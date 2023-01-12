package com.michael.yeslaundry.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.michael.yeslaundry.R;
import com.michael.yeslaundry.models.ValueNoData;
import com.michael.yeslaundry.services.APIService;
import com.michael.yeslaundry.services.Utilitties;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private Spinner spinner;
    private EditText etKG, etNama, etNoHp, etAlamat;
    private Button btnAdd, btnHitung;
    private TextView tvCoba, tvTotalHarga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        tvCoba = view.findViewById(R.id.tv_coba);
        tvTotalHarga = view.findViewById(R.id.tv_totalharga);
        etKG = view.findViewById(R.id.et_kg);
        btnHitung = view.findViewById(R.id.btn_hitung);
        etNama = view.findViewById(R.id.et_nama);
        etNoHp = view.findViewById(R.id.et_nohp);
        etAlamat = view.findViewById(R.id.et_alamat);
        btnAdd = view.findViewById(R.id.btn_add);


//        String berat = etKG.getText().toString();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.jenis_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String coba = adapterView.getItemAtPosition(i).toString();
                if (coba.equalsIgnoreCase("baju")) {
                    tvCoba.setText("50.000");
                } else if(coba.equalsIgnoreCase("sepatu")) {
                    tvCoba.setText("100.000");
                } else {
                    tvCoba.setText("70.000");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHarga = etKG.getText().toString();
                int hasil;
                int kg = Integer.parseInt(strHarga);
                String jenis = spinner.getSelectedItem().toString();
                if (jenis.equalsIgnoreCase("baju")) {
                    hasil = kg * 50000;
                } else if(jenis.equalsIgnoreCase("sepatu")) {
                    hasil = kg * 100000;
                } else {
                    hasil = kg * 70000;
                }
                tvTotalHarga.setText(Integer.toString(hasil));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String noHp = etNoHp.getText().toString();
                String alamat = etAlamat.getText().toString();
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String jenis = adapterView.getItemAtPosition(i).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                boolean bolehAdd = true;
                String strHarga = etKG.getText().toString();
                int hasil;
                int kg = Integer.parseInt(strHarga);
                String jenis = spinner.getSelectedItem().toString();
                if (jenis.equalsIgnoreCase("baju")) {
                    hasil = kg * 50000;
                } else if(jenis.equalsIgnoreCase("sepatu")) {
                    hasil = kg * 100000;
                } else {
                    hasil = kg * 70000;
                }

                if (TextUtils.isEmpty(etNama.getText().toString())) {
                    bolehAdd = false;
                    etNama.setError("Nama Pelanggan tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(etNoHp.getText().toString())) {
                    bolehAdd = false;
                    etNoHp.setError("No HP tidak boleh kosong!");
                }

                if (TextUtils.isEmpty(etAlamat.getText().toString())) {
                    bolehAdd = false;
                    etAlamat.setError("Alamat tidak boleh kosong");
                }

                if (TextUtils.isEmpty(etKG.getText().toString())) {
                    bolehAdd = false;
                    etKG.setError("Berat tidak boleh kosong");
                }

                if (bolehAdd) {
                    addTransaksi(nama, noHp, alamat, kg, jenis, hasil);
                }
            }
        });

        return view;
    }

    private void addTransaksi(String nama, String noHp, String alamat, int berat, String jenis, int hasil) {
        APIService api = Utilitties.getRetrofit().create(APIService.class);
        api.insertTransaksi(Utilitties.YES_LAUNDRY_APIKEY, nama, noHp, alamat, berat, jenis, hasil).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}