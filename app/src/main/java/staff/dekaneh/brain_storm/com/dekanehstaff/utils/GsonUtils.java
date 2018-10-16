package staff.dekaneh.brain_storm.com.dekanehstaff.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;


public class GsonUtils {

    public static <T> String convertArrayToJsonString(List<T> items){
        return new Gson().toJson(items);
    }

    public static <T> String convertObjectToJson(T item) {
        Log.d("DADADA", "convertObjectToJson: " + new Gson().toJson(item));
        return new Gson().toJson(item);
    }
}
