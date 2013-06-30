package com.calm.android.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.calm.android.R;
import com.calm.android.SplashActivity;
import com.calm.android.util.Utils;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calmuserendpoint.Calmuserendpoint;
import com.google.api.services.calmuserendpoint.model.CalmUser;
import com.google.api.services.projectendpoint.Projectendpoint;
import net.simonvt.menudrawer.MenuDrawer;
import roboguice.inject.InjectView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class CalmActivity extends RoboSherlockFragmentActivity {

    public static final String WEB_CLIENT_ID = "1080189142533-1hin5t3rbq6655cop98oam64n9q9kbni.apps.googleusercontent.com";

    private static final String JPEG_FILE_SUFFIX = "jpg";
    public static final int DISMISS_PD = 0x7;
    public static final int LOGIN_SUCSESS = 0x1 ;

    protected Context mContext = this;

    private ActionBar actionBar;
    private MenuDrawer mMenuDrawer;

    private Button mLogoutButton;

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    protected static final String ALBUM_NAME = "calmwork";
    protected static final String JPEG_FILE_PREFIX = "calmphoto";
    private Uri fileUri;
    private File mStorageDir;
    protected String mCurrentPhotoPath = "new";

    protected static final int CAMERA_PIC_REQUEST = 0x1101;
    protected static final int GALLERY_PIC_REQUEST = 0x1110;
    protected ImageView resultImageView;

    public static GoogleAccountCredential credential = null;
    public static Projectendpoint.ProjectEndpoint projectEndpoint;
    public static Calmuserendpoint.CalmUserEndpoint userEndpoint;

    public ProgressDialog pd;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (credential == null){
            SharedPreferences preferences = getSharedPreferences(Utils.PREFERENCES, 0);
            String accountName = preferences.getString(Utils.ACCOUNT_NAME, null);
            if (accountName != null){
                CalmActivity.credential = GoogleAccountCredential.usingAudience(mContext,"server:client_id:" + WEB_CLIENT_ID);
                setEndpoints(accountName);
            }
        }
        //setContentView(R.layout.main);

        mStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        actionBarSetup();
        menuDrawerSetup();

    }

    private void actionBarSetup() {
        actionBar = getSupportActionBar();
    }

    private void menuDrawerSetup() {
        int layoutId = getLayoutId();
        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setContentView(layoutId);
        mMenuDrawer.setMenuView(R.layout.side_menu);

        mLogoutButton = (Button) findViewById(R.id.sidemenu_logout);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logout();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);


            }
        });

        mLogoutButton = (Button) findViewById(R.id.sidemenu_homeworks);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                startActivity(intent);


            }
        });

    }

    protected abstract int getLayoutId();

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_student:
                item.setTitle("Student");
               // Toast.makeText(this, , Toast.LENGTH_SHORT);
                System.out.println("Student");
                intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_techer:
                item.setTitle("Teacher");
                System.out.println("Teacher");
                intent = new Intent(getApplicationContext(), TeacherHomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_upload_image:
                if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)){
                    dispatchTakePictureIntent(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }

                return true;
        }

        return super.onOptionsItemSelected(item);    //To change body of overridden methods use File | Settings | File Templates.
    }


    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
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


    protected void dispatchTakePictureIntent(int actionCode) {
        try {

            File f = createImageFile();

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
            startActivityForResult(takePictureIntent, actionCode);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }



   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if ( (resultCode == RESULT_OK) && (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) ){
            //pass mFileName??
            Intent intent = new Intent(getApplicationContext(), CreateProjectActivity.class);
            intent.putExtra("photoPath", mCurrentPhotoPath);
            startActivity(intent);


        }

    }  */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap image = null;
        /*
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == RESULT_OK) {
            if ( data.getExtras() == null){
                Uri selectedImage = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                } catch (FileNotFoundException e) {
// Utils.Log(LogLevel.Error,TAG, "CreateBasicType onActivityResult. Unable to find file. image:" + selectedImage.getPath() + ", error: " + e.toString(),e);
                    e.printStackTrace();
                } catch (IOException e) {
// Utils.Log(LogLevel.Error,TAG, "CreateBasicType onActivityResult. error. image:" + selectedImage.getPath() + ", error: " + e.toString(),e);
                    e.printStackTrace();
                }
            }
            else {
                image = (Bitmap) data.getExtras().get("data");
            }
        }*/
        if ( (resultCode == RESULT_OK) && (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) ){
            Uri imageUri = Uri.parse(mCurrentPhotoPath) ;
            image = loadBitmapFromUri(getContentResolver(),imageUri );
        }
        else if (requestCode == GALLERY_PIC_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            image = loadBitmapFromUri(getContentResolver(), imageUri);
        }
        if (image!=null){
            resultImageView.setImageBitmap(image);
        }
    }
    protected void setResultImageView(ImageView v){
        resultImageView = v;
    }
    public void getPictureFromGalleryClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_PIC_REQUEST);
    }
    public void getPictureFromCameraClick(View v) {
        /*
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        */
        if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)){
            dispatchTakePictureIntent(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }
    public static Bitmap loadBitmapFromUri(ContentResolver cr, Uri uri){
        if(cr != null && uri != null){
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeStream(cr.openInputStream(uri), null, opts);
                opts = new BitmapFactory.Options();
//Bitmap b = BitmapFactory.decodeStream(cr.openInputStream(uri), null, opts);
                Bitmap b = BitmapFactory.decodeStream(cr.openInputStream(uri));//, null, opts);
/*
// check whether the bitmap should be rotated before presented to user
String fileName = getRealPathFromURI(cr, uri);
if(fileName.endsWith("jpg") || fileName.endsWith("jpeg")){
try {
ExifInterface exif = new ExifInterface(fileName);
int rotateBy = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);//ExifInterface.ORIENTATION_NORMAL);
b = rotateBitmapBy(b, exifOrientationToDegrees(rotateBy));
} catch (IOException e) {
e.printStackTrace();
}
} */
                return b;
            } catch (FileNotFoundException e) {
//Utils.Log(LogLevel.Error,TAG, e.toString(),e);
                e.printStackTrace();
            }
        }
        return null;
    }

    public void logout(){
        SharedPreferences settings = getSharedPreferences("CALM",0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("userName", null);

        editor.commit();

    }

    public static void setEndpoints(String accountName){
        if (accountName!= null){

            CalmActivity.credential.setSelectedAccountName(accountName) ;
            Projectendpoint.Builder builder = new Projectendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), CalmActivity.credential);
            Projectendpoint projectService = builder.build();
            CalmActivity.projectEndpoint = projectService.projectEndpoint();

            Calmuserendpoint.Builder userbuilder = new Calmuserendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), CalmActivity.credential);
            Calmuserendpoint userService = userbuilder.build();
            CalmActivity.userEndpoint = userService.calmUserEndpoint();
        }

    }

    public static Runnable getUserService(final Handler handler){

        return new Runnable() {

            @Override
            public void run() {
                try{

                    System.out.println("getting user");
                    CalmUser calmUser;

                    calmUser = CalmActivity.userEndpoint.getCalmUser(CalmActivity.credential.getSelectedAccountName()).execute();

                    //user does not exist, create new user
                    if (calmUser.containsKey("error_message")) {

                        calmUser = new CalmUser();
                        calmUser.setMail(CalmActivity.credential.getSelectedAccountName());
                        calmUser = CalmActivity.userEndpoint.insertCalmUser(calmUser).execute();

                    }

                    //user exist
                    if (!(calmUser.containsKey("error_message"))){
                        System.out.println("got user");
                        Message msg = handler.obtainMessage();
                        msg.what = LOGIN_SUCSESS;
                        handler.sendMessage(msg);


                    }

                    //TODO: HANDLE CASE WHEN USER WAS NOT CREATED

                } catch (Exception e){
                    System.out.println("******* catch");
                    e.printStackTrace();
                }

                finally {
                    Message msg = handler.obtainMessage();
                    msg.what = DISMISS_PD;
                    handler.sendMessage(msg);

                }

            }
        };
    }


    public final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==CalmActivity.DISMISS_PD){
                dismissProgressDialog();

            } else if(msg.what==CalmActivity.LOGIN_SUCSESS){
                Intent intent = new Intent(mContext, StudentHomeActivity.class);
                startActivity(intent);
            }
            super.handleMessage(msg);
        }
    };

    private void dismissProgressDialog(){
        if (pd != null){
            pd.dismiss();
            pd = null;
        }
    }
}
