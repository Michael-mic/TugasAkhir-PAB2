package com.michael.yeslaundry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.yeslaundry.models.Transaksi;
import com.michael.yeslaundry.models.ValueNoData;
import com.michael.yeslaundry.services.APIService;
import com.michael.yeslaundry.services.Utilitties;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTransaksiActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText etKG, etNama, etNoHp, etAlamat;
    private Button btnUpdate, btnHitung, btnSelesai;
    private TextView tvCoba, tvTotalHarga;
    private Transaksi transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaksi);

        transaksi = getIntent().getParcelableExtra("EXTRA_DATA");
        int id = transaksi.getId();
        String status = transaksi.getStatus();
        String status2 = "Done";

        spinner = (Spinner) findViewById(R.id.spinner);
        tvCoba = findViewById(R.id.tv_coba);
        tvTotalHarga =findViewById(R.id.tv_totalharga);
        etKG = findViewById(R.id.et_kg);
        btnHitung = findViewById(R.id.btn_hitung);
        etNama = findViewById(R.id.et_nama);
        etNoHp = findViewById(R.id.et_nohp);
        etAlamat = findViewById(R.id.et_alamat);
        btnUpdate = findViewById(R.id.btn_add);
        btnSelesai = findViewById(R.id.btn_selesai);

        etNama.setText(transaksi.getNamapelanggan());
        etNoHp.setText(transaksi.getNohp());
        etAlamat.setText(transaksi.getAlamat());
        etKG.setText(Integer.toString(transaksi.getKg()));
        tvTotalHarga.setText(Integer.toString(transaksi.getHarga()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UpdateTransaksiActivity.this, R.array.jenis_array, android.R.layout.simple_spinner_item);
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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
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
                    updateTransaksi(id, nama, noHp, alamat, kg, jenis, hasil, status);
                }
            }
        });

        if (transaksi.getStatus().equalsIgnoreCase("done")) {
            btnSelesai.setEnabled(false);
        }

        btnSelesai.setOnClickListener(new View.OnClickListener() {
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
                    String selesai = "done";
                    selesaiTransaksi(id, nama, noHp, alamat, kg, jenis, hasil, selesai);
                }
            }
        });
    }

    private void selesaiTransaksi(int id, String nama, String noHp, String alamat, int kg, String jenis, int hasil, String selesai) {
        APIService apiService = Utilitties.getRetrofit().create(APIService.class);
        apiService.updateTransaksi(Utilitties.YES_LAUNDRY_APIKEY, id, nama, noHp, alamat, kg, jenis, hasil, selesai).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        Toast.makeText(UpdateTransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateTransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateTransaksiActivity.this, "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(UpdateTransaksiActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateTransaksi(int id, String nama, String noHp, String alamat, int kg, String jenis, int hasil, String status) {
        APIService apiService = Utilitties.getRetrofit().create(APIService.class);
        apiService.updateTransaksi(Utilitties.YES_LAUNDRY_APIKEY, id, nama, noHp, alamat, kg, jenis, hasil, status).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        Toast.makeText(UpdateTransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateTransaksiActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateTransaksiActivity.this, "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                Toast.makeText(UpdateTransaksiActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}