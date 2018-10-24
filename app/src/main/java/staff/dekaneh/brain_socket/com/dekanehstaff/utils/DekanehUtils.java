package staff.dekaneh.brain_socket.com.dekanehstaff.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class DekanehUtils {

    public static void call(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }
}
