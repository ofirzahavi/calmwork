package com.calm.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.model.Project;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.widget.Toast;



/**
 * Created with IntelliJ IDEA.
 * User: ZAHAVI
 * Date: 28/03/13
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class CreateProjectActivity extends CalmActivity {

    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    Calendar myCalendar = Calendar.getInstance();
    TextView lblDateAndTime;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    //        updateLabel();
        }
    };
    private ArrayList<String> images = new ArrayList<String>();
    private int numberOfImages = 1;

  //  private void updateLabel() {
   //     lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
   // }

    @Override
    protected int getLayoutId() {
        return R.layout.new_project_screen;
    }

  //  @InjectView(R.id.newproject_edittext_name)
   // private EditText mProjectNameText;

    @InjectView(R.id.newproject_button_next)
    private Button mNextButton;

    @InjectView(R.id.newproject_spinner_subject)
    private Spinner mSubjectSpinner;

    @InjectView(R.id.newproject_spinner_level)
    private Spinner mLevelSpinner;

    @InjectView(R.id.newproject_spinner_language)
    private Spinner mLanguageSpinner;

    @InjectView(R.id.newproject_button_due_date)
    private Button mDueDateButton;

    @InjectView(R.id.images_horizontal_scrollview)
    private LinearLayout mImagesScrollView;


  //  @InjectView(R.id.newproject_button_budget)
  //  private Button mBudgetButton;


    @InjectView(R.id.newproject_take_pic_btn)
    private ImageButton mPictureButton;

  //  private Project project;
  //  @InjectView(R.id.newproject_image_list)
 //   private ListView mImagesList;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();
     //   project = new Project("");
     //   images.add("image 1");
     //   images.add("image 2");

      //  lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
        mDueDateButton = (Button) findViewById(R.id.newproject_button_due_date);

        mDueDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(CreateProjectActivity.this, d, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        mPictureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isIntentAvailable(getApplicationContext(), MediaStore.ACTION_IMAGE_CAPTURE)){
                    images.add("Image " + numberOfImages);
                    dispatchTakePictureIntent(CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }

            }
        });


        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

             //   String project_name =  mProjectNameText.getText().toString();
                //String project_description =  mProjectDescriptionText.getText().toString();   //TODO: set all values

                //project.title=mSubjectSpinner.getSelectedItem().toString();

                //TODO - CHECK IF PASSWORDS MATCH
                //TOAST
                maketoast();
                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

           //     editor.putString("projectName", project_name);
                //editor.putString("projectDescription", project_description);

                editor.commit();

                Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                startActivity(intent);

            }
        });

        //updateLabel();

      //  ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,images);


       // mImagesList.setAdapter(adapter);

     //   setResultImageView(mCameraResult);


    }

    void maketoast()
    {
        Toast.makeText(this, ("Project Submitted successfully"),
                Toast.LENGTH_SHORT).show();
    }

    public void addListenerOnSpinnerItemSelection() {
        mLevelSpinner = (Spinner) findViewById(R.id.newproject_spinner_level);
        mLevelSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mSubjectSpinner = (Spinner) findViewById(R.id.newproject_spinner_subject);
        mSubjectSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mLanguageSpinner = (Spinner) findViewById(R.id.newproject_spinner_language);
        mLanguageSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


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
            ImageView newImageView = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
            newImageView.setLayoutParams(lp);
            lp.setMargins(10,0,10,0);
            newImageView.setImageBitmap(image);
            mImagesScrollView.addView(newImageView);
        }
    }



}
