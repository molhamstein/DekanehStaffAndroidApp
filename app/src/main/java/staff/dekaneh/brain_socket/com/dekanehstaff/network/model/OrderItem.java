package staff.dekaneh.brain_socket.com.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderItem implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("retailPrice")
    @Expose
    private int retailPrice;

    public int getCount() {
        return count;
    }

    public String getNameAr() {
        return nameAr;
    }

    public int getRetailPrice() {
        return retailPrice;
    }
}
