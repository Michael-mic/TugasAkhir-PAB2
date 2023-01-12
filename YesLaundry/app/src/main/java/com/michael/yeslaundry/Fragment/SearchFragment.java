package com.michael.yeslaundry.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.michael.yeslaundry.R;
import com.michael.yeslaundry.UpdateTransaksiActivity;
import com.michael.yeslaundry.adapter.TransaksiViewAdapter;
import com.michael.yeslaundry.models.Transaksi;
import com.michael.yeslaundry.models.ValueData;
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

public class SearchFragment extends Fragment {
    private TransaksiViewAdapter transaksiViewAdapter;
    private List<Transaksi> data = new ArrayList<>();
    private RecyclerView rvTransaksi;
    private SwipeRefreshLayout srlPost;
    private Button btnPending, btnDone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        rvTransaksi = view.findViewById(R.id.rv_post);
        btnPending = view.findViewById(R.id.btn_pending);
        btnDone = view.findViewById(R.id.btn_done);
        srlPost = view.findViewById(R.id.srl_post);
        transaksiViewAdapter = new TransaksiViewAdapter();
        rvTransaksi.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTransaksi.setAdapter(transaksiViewAdapter);

        srlPost.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllTransaksi();
            }
        });

        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pendingTransaksi();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneTransaksi();
            }
        });

        return view;
    }

    private void doneTransaksi() {
        APIService apiService = Utilitties.getRetrofit().create(APIService.class);
        apiService.doneTransaksi(Utilitties.YES_LAUNDRY_APIKEY).enqueue(new Callback<ValueData<Transaksi>>() {
            @Override
            public void onResponse(Call<ValueData<Transaksi>> call, Response<ValueData<Transaksi>> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    if (success == 1) {
                        data = response.body().getData();
                        transaksiViewAdapter.setData(data, SearchFragment.this::onItemPostLongClick);
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        srlPost.setRefreshing(false);
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<Transaksi>> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pendingTransaksi() {
        APIService apiService = Utilitties.getRetrofit().create(APIService.class);
        apiService.pendingTransaksi(Utilitties.YES_LAUNDRY_APIKEY).enqueue(new Callback<ValueData<Transaksi>>() {
            @Override
            public void onResponse(Call<ValueData<Transaksi>> call, Response<ValueData<Transaksi>> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    if (success == 1) {
                        data = response.body().getData();
                        transaksiViewAdapter.setData(data, SearchFragment.this::onItemPostLongClick);
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        srlPost.setRefreshing(false);
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<Transaksi>> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllTransaksi();
    }

    private void getAllTransaksi() {
        APIService apiService = Utilitties.getRetrofit().create(APIService.class);
        apiService.getAllTransaksi(Utilitties.YES_LAUNDRY_APIKEY).enqueue(new Callback<ValueData<Transaksi>>() {
            @Override
            public void onResponse(Call<ValueData<Transaksi>> call, Response<ValueData<Transaksi>> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    if (success == 1) {
                        data = response.body().getData();
                        transaksiViewAdapter.setData(data, SearchFragment.this::onItemPostLongClick);
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        srlPost.setRefreshing(false);
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<Transaksi>> call, Throwable t) {
                Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onItemPostLongClick(View view, Transaksi transaksi, int i) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.menu_popup);
        popupMenu.setGravity(Gravity.CENTER_HORIZONTAL);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_edit:
                        Intent intent = new Intent(getContext(), UpdateTransaksiActivity.class);
                        intent.putExtra("EXTRA_DATA", transaksi);
                        startActivity(intent);
                        return true;
                    case R.id.action_delete:
                        int id = transaksi.getId();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setTitle("Konfirmasi");
                        alertDialogBuilder.setMessage("Yakin ingin menghapus transaksi '" + transaksi.getNamapelanggan() + "' ?");
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                deletePost(id);
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        Toast.makeText(getContext(), "delete", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    private void deletePost(int id) {
        APIService apiService =Utilitties.getRetrofit().create(APIService.class);
        apiService.deleteTransaksi(Utilitties.YES_LAUNDRY_APIKEY, id).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if(response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        getAllTransaksi();
                    } else {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {

            }
        });
    }
}