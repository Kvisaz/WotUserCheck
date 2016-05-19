package ru.kvisaz.wotolenemer.network;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kvisaz.wotolenemer.network.model.User;
import ru.kvisaz.wotolenemer.network.model.UserInfo;
import ru.kvisaz.wotolenemer.network.model.WotResp;

public interface RestAPI {
    String BaseUrl = "https://api.worldoftanks.ru/";
    //String appId = "application_id="; // String appId = "application_id=demo"  // for test
    int resultLimit = 50;
    String resQuery = "limit="+resultLimit;

    @GET("wot/account/list/?"+resQuery)
    Call<WotResp<List<User>>> findUsers(@Query("search") String search,@Query("application_id") String appId);

    String infoExtrasQuery = "extra=statistics.random%2Cstatistics.fallout%2Cstatistics.globalmap_absolute%2Cstatistics.globalmap_champion%2Cstatistics.globalmap_middle";
    @GET("wot/account/info/?"+infoExtrasQuery)
    Call<WotResp<Map<String, UserInfo>>> getUserInfoList(@Query("account_id") List<Integer> account_ids,@Query("application_id") String appId);

}
