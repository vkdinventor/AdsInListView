package com.vikash.app.adsinlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.clockbyte.admobadapter.AdmobAdapterWrapper;
import com.clockbyte.admobadapter.AdmobRecyclerAdapterWrapper;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();



        RecyclerView recycler_view = (RecyclerView) findViewById(R.id.recycler_view);


        // use this if you want the RecyclerView to look like a vertical list view
        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // use this if you want the RecyclerView to look like a grid view
        //recycler_view.setLayoutManager(new GridLayoutManager(this, 2));
        int adPosition = 2;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0 ;i < 10; i++){
            list.add("item "+i);
            if(i== adPosition){
                adPosition= adPosition+3;
                Log.v("TAG","adding null item");
                list.add(null);
            }
        }


        ListAdapter adapter = new ListAdapter(list, this);

        String[] testDevicesIds = new String[]{getString(R.string.testDeviceID), AdRequest.DEVICE_ID_EMULATOR};

        AdmobRecyclerAdapterWrapper adapterWrapper = new AdmobRecyclerAdapterWrapper(this,testDevicesIds);
        adapterWrapper.setAdapter(adapter); //wrapping your adapter with a AdmobAdapterWrapper.
        //here you can use the following string to set your custom layouts for a different types of native ads
        //adapterWrapper.setInstallAdsLayoutId(R.layout.your_installad_layout);
        //adapterWrapper.setcontentAdsLayoutId(R.layout.your_installad_layout);

        //Sets the max count of ad blocks per dataset, by default it equals to 3 (according to the Admob's policies and rules)
        adapterWrapper.setLimitOfAds(3);

        //Sets the number of your data items between ad blocks, by default it equals to 10.
        //You should set it according to the Admob's policies and rules which says not to
        //display more than one ad block at the visible part of the screen,
        // so you should choose this parameter carefully and according to your item's height and screen resolution of a target devices
        adapterWrapper.setNoOfDataBetweenAds(10);

        //It's a test admob ID. Please replace it with a real one only when you will be ready to deploy your product to the Release!
        //Otherwise your Admob account could be banned
        //String admobUnitId = getResources().getString(R.string.banner_admob_unit_id);
        //adapterWrapper.setAdmobReleaseUnitId(admobUnitId);

        recycler_view.setAdapter(adapterWrapper); // setting an AdmobAdapterWrapper to a ListView
        adapter.notifyDataSetChanged();
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
