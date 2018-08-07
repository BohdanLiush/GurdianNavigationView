package com.example.bohdan.gurdiannavigationview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModelApi {

    @GET("search?&api-key=a6971452-c6a3-431a-af07-a3b8a80d9a33&show-fields=thumbnail")
    Call<Model> idsInfo(@Query("q") String number);

}
