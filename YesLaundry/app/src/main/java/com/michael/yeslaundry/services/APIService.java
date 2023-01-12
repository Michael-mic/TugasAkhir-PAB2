package com.michael.yeslaundry.services;

import com.michael.yeslaundry.models.Transaksi;
import com.michael.yeslaundry.models.ValueData;
import com.michael.yeslaundry.models.ValueNoData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("getAllTransaksi")
    Call<ValueData<Transaksi>> getAllTransaksi(@Field("key") String key);

    @FormUrlEncoded
    @POST("insertTransaksi")
    Call<ValueNoData> insertTransaksi (@Field("key") String key,
                                       @Field("namapelanggan") String nama,
                                       @Field("nohp") String nohp,
                                       @Field("alamat") String alamat,
                                       @Field("kg") int kg,
                                       @Field("jenis") String jenis,
                                       @Field("harga") int harga);

    @FormUrlEncoded
    @POST("updateTransaksi")
    Call<ValueNoData> updateTransaksi (@Field("key") String key,
                                       @Field("id") int id,
                                       @Field("namapelanggan") String nama,
                                       @Field("nohp") String nohp,
                                       @Field("alamat") String alamat,
                                       @Field("kg") int kg,
                                       @Field("jenis") String jenis,
                                       @Field("harga") int harga,
                                       @Field("status") String status);

    @FormUrlEncoded
    @POST("pendingTransaksi")
    Call<ValueData<Transaksi>> pendingTransaksi (@Field("key") String key);

    @FormUrlEncoded
    @POST("doneTransaksi")
    Call<ValueData<Transaksi>> doneTransaksi (@Field("key") String key);

    @FormUrlEncoded
    @POST("deleteTransaksi")
    Call<ValueNoData> deleteTransaksi(@Field("key") String key,
                                 @Field("id") int id);


}
