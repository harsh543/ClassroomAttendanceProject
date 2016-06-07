package com.example.harshbajaj.intheclassroom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private View photoView;
    private int width, height;
    private FaceDetector.Face[] detectedFaces;
    private int NUMBER_OF_FACES=4;
    private FaceDetector faceDetector;
    private int NUMBER_OF_FACE_DETECTED;
    private ImageView plusIcon,groupIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoView =(View)findViewById(R.id.photograph);
        groupIcon=(ImageView)findViewById(R.id.groupicon);
        plusIcon=(ImageView)findViewById(R.id.plusicon);
      /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                try {
                    BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
                    bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
                    Bitmap photograph =
                            BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()), null,
                                    bitmapFatoryOptions);


                    if (photograph != null) {
                        if(Build.VERSION.SDK_INT >= 16) {
                         /*  *//* BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
                            bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
                           *//* width=photograph.getWidth();
                            height=photograph.getHeight();
                            detectedFaces=new FaceDetector.Face[NUMBER_OF_FACES];
                            faceDetector=new FaceDetector(width,height,NUMBER_OF_FACES);
                            NUMBER_OF_FACE_DETECTED=faceDetector.findFaces(photograph, detectedFaces);
                            // photoView should refer to view whose reference is obtained in onCreate() using findViewById(), and whose background you want to update*/
                            photoView.setBackground(new BitmapDrawable(getResources(), photograph));

                            width = photograph.getWidth();
                            height = photograph.getHeight();
                            detectedFaces = new FaceDetector.Face[NUMBER_OF_FACES];
                            faceDetector= new FaceDetector(width, height, NUMBER_OF_FACES);
                            NUMBER_OF_FACE_DETECTED = faceDetector.findFaces(photograph, detectedFaces);
                            /*mIL.invalidate();*/
                            Log.e("Faces:count=", String.valueOf(NUMBER_OF_FACE_DETECTED));

                            plusIcon.setVisibility(View.GONE);
                            groupIcon.setVisibility(View.GONE);

                        } else {
                             /* BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
                            bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
                           */
                            photoView.setBackgroundDrawable(new BitmapDrawable(getResources(), photograph));
                            new MyView(getApplicationContext(),photograph);
                            plusIcon.setVisibility(View.GONE);
                            groupIcon.setVisibility(View.GONE);
                        }
                    }
                }
                catch (Exception e){

                }


            }
            else if (requestCode == REQUEST_CAMERA) {
                try {
                    BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
                    bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
                    Bitmap photograph =
                            BitmapFactory.decodeStream(getContentResolver().openInputStream(data.getData()), null,
                                    bitmapFatoryOptions);
                    if (photograph != null) {
                        if(Build.VERSION.SDK_INT >= 16) {
                             /* BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
                            bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
                           */
                            photoView.setBackground(new BitmapDrawable(getResources(), photograph));
                            width = photograph.getWidth();
                            height = photograph.getHeight();
                            detectedFaces = new FaceDetector.Face[NUMBER_OF_FACES];
                            faceDetector= new FaceDetector(width, height, NUMBER_OF_FACES);
                            NUMBER_OF_FACE_DETECTED = faceDetector.findFaces(photograph, detectedFaces);
                            Log.e("Faces:count=", String.valueOf(NUMBER_OF_FACE_DETECTED));
                            /*mIL.invalidate();*/
                            plusIcon.setVisibility(View.GONE);
                            groupIcon.setVisibility(View.GONE);

                        } else {
                             /* BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
                            bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
                           */
                            photoView.setBackgroundDrawable(new BitmapDrawable(getResources(), photograph));
                           new MyView(getApplicationContext(),photograph);
                            plusIcon.setVisibility(View.GONE);
                            groupIcon.setVisibility(View.GONE);

                        }
                    }
                }
                catch (Exception e){

                }

            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class MyView extends View
    {
        private Bitmap myBitmap;
        private int width, height;
        private FaceDetector.Face[] detectedFaces;
        private int NUMBER_OF_FACES=4;
        private FaceDetector faceDetector;
        private int NUMBER_OF_FACE_DETECTED;
        private float eyeDistance;

        public MyView(Context context,Bitmap photograph)
        {
            super(context);
           /* BitmapFactory.Options bitmapFatoryOptions=new BitmapFactory.Options();
            bitmapFatoryOptions.inPreferredConfig=Bitmap.Config.RGB_565;
            myBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.faceswapping,bitmapFatoryOptions);
 */

            myBitmap=photograph;

            width=myBitmap.getWidth();
            height=myBitmap.getHeight();
            detectedFaces=new FaceDetector.Face[NUMBER_OF_FACES];
            faceDetector=new FaceDetector(width,height,NUMBER_OF_FACES);
            NUMBER_OF_FACE_DETECTED=faceDetector.findFaces(myBitmap, detectedFaces);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawBitmap(myBitmap, 0,0, null);
            Paint myPaint = new Paint();
            myPaint.setColor(Color.GREEN);
            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setStrokeWidth(3);

            for(int count=0;count<NUMBER_OF_FACE_DETECTED;count++)
            {
                FaceDetector.Face face=detectedFaces[count];
                PointF midPoint=new PointF();
                face.getMidPoint(midPoint);

                eyeDistance=face.eyesDistance();
                canvas.drawRect(midPoint.x-eyeDistance, midPoint.y-eyeDistance, midPoint.x+eyeDistance, midPoint.y+eyeDistance, myPaint);
            }
        }

    }
}