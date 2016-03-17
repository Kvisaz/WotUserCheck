package ru.kvisaz.wotolenemer.rest;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.kvisaz.wotolenemer.model.User;
import ru.kvisaz.wotolenemer.model.WotResp;

public interface RestAPI {
    String BaseUrl = "https://api.worldoftanks.ru/";
    String appId = "application_id=demo";
    int resultLimit = 50;
    String resQuery = "limit="+resultLimit+"&";

    @GET("wot/account/list/?"+resQuery+appId)
    Call<WotResp<List<User>>> findUsers(@Query("search") String search);

}
