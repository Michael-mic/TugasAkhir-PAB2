package com.michael.yeslaundry.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilitties {
    private static final String YES_LAUNDRY_BASEURL = "https://tugaspabif51.000webhostapp.com/laundry/index.php/MobileControl/";
    public static final String YES_LAUNDRY_APIKEY = "yeslaundry";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(YES_LAUNDRY_BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
