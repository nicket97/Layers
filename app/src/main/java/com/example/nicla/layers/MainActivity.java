package com.example.nicla.layers;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.nicla.layers.transformations.BlackAndWhite;
import com.example.nicla.layers.transformations.Blur;
import com.example.nicla.layers.transformations.ColorShift;
import com.example.nicla.layers.transformations.Contrast;
import com.example.nicla.layers.transformations.Exposure;
import com.example.nicla.layers.transformations.GaussianBlur;
import com.example.nicla.layers.transformations.Grain;
import com.example.nicla.layers.transformations.GrayScale;
import com.example.nicla.layers.transformations.Levels;
import com.example.nicla.layers.transformations.RotateL;
import com.example.nicla.layers.transformations.RotateR;
import com.example.nicla.layers.transformations.Sharpen;
import com.example.nicla.layers.transformations.WhiteBalance;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.layerView)
    ListView layerView;
    @BindView(R.id.imageView2)
    ImageView img;
    static MainActivity activity = null;
    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static LoadedImage workingImg = null;
    private static Uri startImg = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPermissions();
        activity = this;
        //layout.setBackgroundColor(Color.BLACK);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //test


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        ButterKnife.bind(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Layers.addLayer(new Layer(new ColorShift(50,20,10,1)));
        repaint();



    }

    private void getPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navopenImage) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 1);
        }
        else if(id == R.id.navClearLayers){
            Layers.clear();
            repaint();
        }
        else if(id == R.id.navRotateR){
            Layers.addLayer(new Layer(new RotateR()));
            repaint();
        }
        else if(id == R.id.navRotateL){
            Layers.addLayer(new Layer(new RotateL()));
            repaint();
        }
        else if(id == R.id.navExposure){
            Layers.addLayer(new Layer(new Exposure(-80)));
            repaint();
        }
        else if(id == R.id.navContrast){
            Layers.addLayer(new Layer(new Contrast(100,0.5)));
            repaint();
        }
        else if(id == R.id.navLevels){
            Layers.addLayer(new Layer(new Levels(40, 200)));
            repaint();
        }
        else if(id == R.id.navNoice){
            Layers.addLayer(new Layer(new Grain(50)));
            repaint();
        }
        else if(id == R.id.navBlur){
            Layers.addLayer(new Layer(new Blur(5)));
            repaint();
        }
        else if(id == R.id.navGaussioanBlur){
            Layers.addLayer(new Layer(new GaussianBlur(5)));
            repaint();
        }
        else if(id == R.id.navSharpen){
            Layers.addLayer(new Layer(new Sharpen()));
            repaint();
        }

        else if(id == R.id.navColorShift){
            Layers.addLayer(new Layer(new ColorShift(100,0,0,1)));
            repaint();
        }
        else if(id == R.id.navBAW){
            Layers.addLayer(new Layer(new BlackAndWhite(50)));
            repaint();
        }
        else if(id == R.id.navGrey){
            Layers.addLayer(new Layer(new GrayScale()));
            repaint();
        }
        else if(id == R.id.navWhiteBalance){
            Layers.addLayer(new Layer(new WhiteBalance(50)));
            repaint();
        }





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void repaint() {

        if(workingImg != null){

        img.setImageBitmap(Layers.getTransformedImage(workingImg).getBitmapImg());
        }
        layerView.setAdapter(new LayerViewAdapter(activity, Layers.getLayerStack()));
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            startImg = selectedImage;
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap imgMap = BitmapFactory.decodeFile(picturePath);
            /*Bitmap newBmp = Bitmap.createBitmap(imgMap.getWidth(), imgMap.getHeight(), Bitmap.Config.ARGB_8888);
// Create a canvas  for new bitmap
            Canvas c = new Canvas(newBmp);

// Draw your old bitmap on it.
            c.drawBitmap(imgMap, 0, 0, new Paint());
            */
            double scale = 1;
            if(1080/imgMap.getWidth() < 600/imgMap.getHeight()){
                scale = (double)1080/imgMap.getWidth();
            }
            else{
                scale = (double)600/imgMap.getHeight();
            }
            Log.d("SCALE", "Scale: " + scale);
            Bitmap newBmp = (Bitmap.createScaledBitmap(imgMap, (int)(imgMap.getWidth()*scale), (int)(imgMap.getHeight()*scale), false));
            workingImg = new LoadedImage(newBmp);
            repaint();


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
