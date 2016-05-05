package ru.kvisaz.wotolenemer.network.model;

/**
 *  Standard World of Tanks API Server response  13.03.2016
 *  see  https://ru.wargaming.net/developers/documentation/guide/getting-started/
 *
 */
public class WotResp<T> {
    public String status; // ‘ok’  OR ‘error’
    public WotServerError error; // dict in JSON
    public T data; // dict in JSON, depends on type of call

    private class WotServerError {
        public String field;
        public String message;
        public int code;
        public String value;
    }
}
