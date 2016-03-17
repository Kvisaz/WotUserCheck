package ru.kvisaz.wotolenemer.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


/**
 *   Client functions
 *   get server response in same thread - use loader or any other async task
 *
 */
public class Client {

   /* public static String getPage(String pageCode) throws IOException
    {
      return grab(RetrofitFactory.getApiService().loadPage(pageCode));
    }*/



    //  Client common functions
    private static String grab(Call<ResponseBody> call) throws IOException {
        Response response = call.execute();

        if(!response.isSuccess()){
            return null;
        }

        return getAsString((ResponseBody) response.body());
    }

    private static String getAsString(ResponseBody responseBody) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
        StringBuilder sb = new StringBuilder("");
        while(br.ready())
        {
            sb.append(br.readLine());
        }
        br.close();

        return sb.toString();
    }


}
