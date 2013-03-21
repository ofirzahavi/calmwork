package com.calm.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.calm.android.R;
import roboguice.inject.InjectView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/20/13
 * Time: 5:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserWorksActivity extends CalmActivity {


    private static final String JPEG_FILE_SUFFIX = "jpg";
    @InjectView(R.id.works_user_name)
    private TextView mUserNameTextView;

    @InjectView(R.id.picture_button)
    private Button mTakePicButton;

    @InjectView(R.id.camera_picture)
    private ImageView mImageView;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final String ALBUM_NAME = "calmwork";
    private static final String JPEG_FILE_PREFIX = "calmphoto";
    private Uri fileUri;
    private File mStorageDir;
    private String mCurrentPhotoPath = "new";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("CALM",0);
        String userName = settings.getString("userName", "Error - no user found");

        mUserNameTextView.setText(userName);

        if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)){
           /*
            mStorageDir =  new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES
                    ) ,
                    ALBUM_NAME
            );
             */

            mStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            mTakePicButton.setEnabled(true);
            mTakePicButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dispatchTakePictureIntent(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }
            });
        } else {
            mTakePicButton.setEnabled(false);
        }


    }


    @Override
    protected int getLayoutId() {
        return R.layout.main;  //To change body of implemented methods use File | Settings | File Templates.


    }

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void dispatchTakePictureIntent(int actionCode) {
        try {

            File f = createImageFile();

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            startActivityForResult(takePictureIntent, actionCode);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
     //   Bitmap imageBitmap = (Bitmap) extras.get("data");
    //    mImageView.setImageBitmap(imageBitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if ( (resultCode == RESULT_OK) && (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) ){
         //   System.out.println(data.toString());
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            //mImageView.setImageBitmap(photo);
                 setPic();
            //    handleSmallCameraPhoto(data);

        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp;
        File image = File.createTempFile(
                imageFileName,
                JPEG_FILE_SUFFIX // , mStorageDir
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        System.out.println(mCurrentPhotoPath);
        return image;
    }

    private void setPic() {
     /*
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        Bitmap bitmap1 = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        String fname = this.getFilesDir().getAbsolutePath()+mCurrentPhotoPath;
        Bitmap bitmap = BitmapFactory.decodeFile(fname, bmOptions);

        mImageView.setImageBitmap(bitmap);

        */
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
        mImageView.setImageBitmap(bitmap);

    }
}