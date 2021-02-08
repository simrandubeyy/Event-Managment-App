package com.example.signup;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


public class registerevent extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private EditText mName;
    private EditText mEventName;
    private EditText mDate;
    private EditText mTime;
    private EditText mAddress;
    private EditText mContactNumber;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerevent);

        mButtonChooseImage = findViewById(R.id.button4);
        mButtonUpload = findViewById(R.id.button7);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mImageView = findViewById(R.id.imageView10);
        mProgressBar = findViewById(R.id.progressBar3);
        mName = findViewById(R.id.editTextTextPersonName2);
        mEventName = findViewById(R.id.editTextTextPersonName3);
        mDate = findViewById(R.id.editTextDate2);
        mTime = findViewById(R.id.editTextTime);
        mAddress = findViewById(R.id.editTextTextPostalAddress);
        mContactNumber = findViewById(R.id.editTextPhone);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference( "uploads");

        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openFileChooser();
            }
        });

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask !=null && mUploadTask.isInProgress()){
                    Toast.makeText(registerevent.this,"Upload in Progress",Toast.LENGTH_SHORT).show();
                }else{
                    uploadFile();
                }
            }
        });

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagesActivity();
            }
        });
    }

    private void openFileChooser()
    {
        Intent intent =  new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode== RESULT_OK
         && data != null && data.getData() !=null)
        {
            mImageUri = data.getData();

            mImageView.setImageURI(mImageUri);


        }
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(uri));

    }
    private void uploadFile()
    {
     if(mImageUri != null)
     {
         StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
         + "."+ getFileExtension(mImageUri));

        mUploadTask = fileReference.putFile(mImageUri)
                 .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         Handler handler = new Handler();
                         handler.postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                mProgressBar.setProgress(0);
                             }
                         }, 500);

                         Toast.makeText(registerevent.this, "Upload Successful", Toast.LENGTH_LONG).show();
                         Upload upload = new Upload(mName.getText().toString().trim(),
                                 mEventName.getText().toString().trim(),
                                 mAddress.getText().toString().trim(),
                                 mDate.getText().toString().trim(),
                                 mTime.getText().toString().trim(),
                                 mContactNumber.getText().toString().trim(),
                                 taskSnapshot.getUploadSessionUri().toString().trim());

                                String uploadId = mDatabaseRef.push().getKey();
                                mDatabaseRef.child(uploadId).setValue(upload);
                     }
                 }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Toast.makeText(registerevent.this,e.getMessage(),Toast.LENGTH_SHORT).show();

             }
         }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                 double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                 mProgressBar.setProgress((int) progress);
             }
         });
     }
     else
         {
             Toast.makeText(this,"No file Selected",Toast.LENGTH_SHORT).show();
         }
    }

    private void openImagesActivity() {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }
}