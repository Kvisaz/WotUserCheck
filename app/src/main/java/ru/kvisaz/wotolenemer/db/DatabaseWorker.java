package ru.kvisaz.wotolenemer.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import nl.qbusict.cupboard.QueryResultIterable;
import ru.kvisaz.wotolenemer.Constants;
import ru.kvisaz.wotolenemer.model.UserModel;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DatabaseWorker {
    private final SQLiteDatabase database;

    public DatabaseWorker(Context context){
        database = new DatabaseHelper(context).getWritableDatabase();
    }

    public ArrayList<UserModel> readHistory(){
        Cursor cursor = cupboard().withDatabase(database).query(UserModel.class).getCursor();
        ArrayList<UserModel> users = new ArrayList<>();

        try {
            QueryResultIterable<UserModel> itr = cupboard().withCursor(cursor).iterate(UserModel.class);
            for(UserModel userModel: itr){
                users.add(userModel);
            }
        }
        catch (Exception e){
            Log.d(Constants.LOG_TAG,"DatabaseWorker readHistory exception");
            Log.d(Constants.LOG_TAG, toString());
        }
        finally {
            cursor.close();
        }
        return users;
    }

    public long saveToHistory(UserModel newUserModel){
        long id = cupboard().withDatabase(database).put(newUserModel);
        return id;
    }

    public UserModel removeFirst(){
        UserModel first = cupboard().withDatabase(database).query(UserModel.class).get();
        cupboard().withDatabase(database).delete(UserModel.class,first._id);
        return first;
    }

    public long getCount(){
        Cursor cursor = cupboard().withDatabase(database).query(UserModel.class).getCursor();
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
