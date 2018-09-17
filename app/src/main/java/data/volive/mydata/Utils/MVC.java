package data.volive.mydata.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MVC {
    public static Bitmap getBitmapFromByte(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
