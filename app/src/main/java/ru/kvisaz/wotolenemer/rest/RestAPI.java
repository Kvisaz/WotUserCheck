package ru.kvisaz.wotolenemer.rest;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.kvisaz.wotolenemer.model.User;
import ru.kvisaz.wotolenemer.model.WotResp;

public interface RestAPI {
    String BaseUrl = "https://api.worldoftanks.ru/";
    String appKeyQuery = "application_id=demo";

    @GET("wot/account/list/?"+ appKeyQuery +"&search={search}")
    Call<WotResp<List<User>>> findUsers(@Path("search") String search);

}
