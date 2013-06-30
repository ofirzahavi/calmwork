package com.calm.android.activity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.calm.android.R;
import com.calm.android.util.Utils;
import com.google.api.client.util.DateTime;
import com.google.api.services.projectendpoint.Projectendpoint;
import com.google.api.services.projectendpoint.model.Project;
import com.squareup.picasso.Picasso;

import net.simonvt.menudrawer.StaticDrawer;
import roboguice.inject.InjectView;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.widget.Toast;

import org.apache.http.HttpResponse;


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
    Context mContext = this;
    DatePickerDialog datePickerDialog;
    DateTime mChosenDateTime;

    private ArrayList<String> images = new ArrayList<String>();
    private int numberOfImages = 1;

    private int mSelectedtDay;
    private int mSelectedMonth;
    private int mSelectedYear;

    Calendar mCalander = Calendar.getInstance();

    private int mCurrentDay = mCalander.get(Calendar.DAY_OF_MONTH);
    private int mCurrentMonth = mCalander.get(Calendar.MONTH);
    private int mCurrentYear = mCalander.get(Calendar.YEAR);

    private Date mDate;

   // private Project mProject;
  //  private void updateLabel() {
   //     lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
   // }

    @Override
    protected int getLayoutId() {
        return R.layout.new_project_screen;
    }

  //  @InjectView(R.id.newproject_edittext_name)
   // private EditText mProjectNameText;

     @InjectView(R.id.newproject_edittext_price)
     private EditText mProjectBudget;

    @InjectView(R.id.newproject_button_next)
    private Button mNextButton;

    @InjectView(R.id.newproject_add_notes_button)
    private Button mAddNotesButton;

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

    private Projectendpoint service;
    private Project mNewProject = new Project();

    private static final String DATE_FORMAT = "dd/MM/yyyy";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addListenerOnSpinnerItemSelection();
        setDateDialogs();

       // System.out.println(" budget is ********* " + mProjectBudget.getHint());

        mDueDateButton = (Button) findViewById(R.id.newproject_button_due_date);

        mDueDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePickerDialog.show();

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

        mAddNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


             //

                SharedPreferences settings = getSharedPreferences("CALM",0);
                SharedPreferences.Editor editor = settings.edit();

                if (mLevelSpinner.getSelectedItemPosition()== 0 || mSubjectSpinner.getSelectedItemPosition() == 0 ||
                        mLanguageSpinner.getSelectedItemPosition()==0 || mSelectedYear<mCurrentYear ||
                        (mSelectedYear==mCurrentYear && mSelectedMonth<mCurrentMonth) ||
                        (mSelectedYear==mCurrentYear && mSelectedMonth==mCurrentMonth && mSelectedtDay<mCurrentDay))
                       //add illegal budget check
                {

                    makeCorrectToast();
                }
                else
                {
                    mDate = new Date();
                    mDate.setDate(mSelectedtDay);
                    mDate.setMonth(mSelectedMonth);
                    mDate.setYear(mSelectedYear-1900);
                    mChosenDateTime = new DateTime(mDate);

                    mNewProject.setLevel(mLevelSpinner.getSelectedItemPosition());

                    mNewProject.setLanguage(mLanguageSpinner.getSelectedItem().toString());

                    mNewProject.setSubject(mSubjectSpinner.getSelectedItem().toString());

                    mNewProject.setDueDate(mChosenDateTime);

                    String budgetString = mProjectBudget.getText().toString();
                    if (budgetString.length() > 0){
                        mNewProject.setBudget(Integer.parseInt(budgetString));
                    } else {
                        mNewProject.setBudget(0);
                    }


                    insertProject();
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                    maketoast();
                    startActivity(intent);
                    finish();

                }


            }
        });

        //updateLabel();

      //  ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,images);


       // mImagesList.setAdapter(adapter);

     //   setResultImageView(mCameraResult);


    }

    private void makeCorrectToast() {


        if (mSubjectSpinner.getSelectedItemPosition()==0)
            maketoastInvalidSubject();
        else
        {
            if (mLevelSpinner.getSelectedItemPosition() == 0)
            {
                maketoastInvalidLevel();
            }
            else
            {
                if  (mLanguageSpinner.getSelectedItemPosition() == 0)
                    maketoastInvalidLanguage()  ;
                else
                {
                    if (mSelectedYear<mCurrentYear ||
                            (mSelectedYear==mCurrentYear && mSelectedMonth<mCurrentMonth) ||
                            (mSelectedYear==mCurrentYear && mSelectedMonth==mCurrentMonth && mSelectedtDay<mCurrentDay))
                        maketoastInvalidDate();        //TODO: fix logic
                    else
                    {
                        //Todo: handle budget check
                    }

                }

            }
        }
    }

    public void insertProject(){


        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    CalmActivity.projectEndpoint.insertProject(mNewProject).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

    }


    void maketoast()
    {
        Toast.makeText(this, ("Project Submitted successfully"),
                Toast.LENGTH_SHORT).show();
    }

    void maketoastInvalidSubject()
    {
        Toast.makeText(this, ("Please choose project's subject"),
                Toast.LENGTH_SHORT).show();
    }

    void maketoastInvalidDate()
    {
        Toast.makeText(this, ("Please choose a Valid due date"),
                Toast.LENGTH_SHORT).show();
    }

    void maketoastInvalidLevel()
    {
        Toast.makeText(this, ("Please choose project's Level"),
                Toast.LENGTH_SHORT).show();
    }

    void maketoastInvalidLanguage()
    {
        Toast.makeText(this, ("Please choose project's Language"),
                Toast.LENGTH_SHORT).show();
    }

    void maketoastInvaliBudget()
    {
        Toast.makeText(this, ("Please set a Valid Budget"),
                Toast.LENGTH_SHORT).show();
    }



    public void addListenerOnSpinnerItemSelection() {
        mLevelSpinner = (Spinner) findViewById(R.id.newproject_spinner_level);
        mLevelSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
      //  mNewProject.setLevel(mLevelSpinner.getSelectedItemPosition()); //TODO
        mSubjectSpinner = (Spinner) findViewById(R.id.newproject_spinner_subject);
        mSubjectSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        mLanguageSpinner = (Spinner) findViewById(R.id.newproject_spinner_language);
        mLanguageSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

        protected void setDateDialogs() {

            Calendar c = Calendar.getInstance();

            int startYear = c.get(Calendar.YEAR);
            int startMonth = c.get(Calendar.MONTH);
            int startDay = c.get(Calendar.DAY_OF_MONTH);


        datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {


                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                        //Date chosenDate = new Time();
                        System.out.println(year + "/" + monthOfYear + "/" + dayOfMonth);
                        Date date = new Date();

                        date.setDate(dayOfMonth);
                        date.setMonth(monthOfYear);
                        date.setYear(year - 1900);
                        DateTime dateTime = new DateTime(date);
                        //mChosenDateTime = dateTime;
                        mNewProject.setDueDate(dateTime);
                        String dateString = new SimpleDateFormat(DATE_FORMAT).format(date);
                        mDueDateButton.setText(dateString);
                        mSelectedtDay=dayOfMonth;
                        mSelectedMonth=monthOfYear;
                        mSelectedYear=year;


                    }
                }, startYear , startMonth, startDay);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Bitmap image = null;
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
        Uri imageUri = null;
        File imageFile = null;

        if ( (resultCode == RESULT_OK) && (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) ){
            imageUri = Uri.parse(mCurrentPhotoPath);
            //image = loadBitmapFromUri(getContentResolver(),imageUri );
        }
        else if (requestCode == GALLERY_PIC_REQUEST && resultCode == RESULT_OK) {
            imageUri = data.getData();
        }


        if (imageUri!=null){
            String f = Utils.getRealPathFromURI(this, imageUri);
            System.out.println("** image file name: " + f);
            imageFile = new File(f);

            /*
            ImageView newImageView = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
            newImageView.setLayoutParams(lp);
            lp.setMargins(10,0,10,0);
            newImageView.setImageBitmap(image);
            mImagesScrollView.addView(newImageView);
            */
            uploadFile(imageFile);
        }
    }

    private void uploadFile(final File imageFile) {

        try {
            String serverImageUrl = Utils.postFileToServer(imageFile);
            ImageView newImageView = new ImageView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
            newImageView.setLayoutParams(lp);
            lp.setMargins(10, 0, 10, 0);
            mImagesScrollView.addView(newImageView);
            List<String> imagesList = mNewProject.getImageIds();
            if (imagesList == null){
                imagesList = new ArrayList<String>();
            }
            imagesList.add(serverImageUrl);

            mNewProject.setImageIds(imagesList);
            Picasso.with(mContext).load(serverImageUrl).into(newImageView);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
