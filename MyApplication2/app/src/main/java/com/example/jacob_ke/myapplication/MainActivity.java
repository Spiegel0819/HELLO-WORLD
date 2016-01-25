/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jacob_ke.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
        implements Fragment_List.OnHeadlineSelectedListener {
    private static final String TAG = "MainActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Context context = getApplicationContext();
//        CharSequence text = " onCreate!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onCreate");
        setContentView(R.layout.bi_fragment_layout);
        //手機會套用有fragment_container的   平板會套用另一個且透過該layout開啟headline跟article fragment
        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {
//            text = " 手機!!!";
//            duration = Toast.LENGTH_SHORT;
//            toast = Toast.makeText(context,TAG + text, duration);
//            toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//            toast.show();
            Log.v(TAG, "手機");
            //手機會進這個判斷
            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            //手機做的事  先把HeadlinesFragment叫出來並把內容放進fragment_container顯示
            // Create an instance of ExampleFragment
            Fragment_List firstFragment = new Fragment_List();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

        }else{
//            text = " 平板!!!";
//            duration = Toast.LENGTH_SHORT;
//            toast = Toast.makeText(context,TAG + text, duration);
//            toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//            toast.show();
            Log.v(TAG, "平板");
        }
    }

    public void onTitleSelected(int position) {
        Log.v(TAG, "onListItemClick");
        // The user selected the headline of an article fro m the HeadlinesFragment
        //平板會做   手機不會做  因為手機的news_articals沒有article_fragment
        // Capture the article fragment from the activity layout
        Fragment_Content articleFrag = (Fragment_Content)
                getSupportFragmentManager().findFragmentById(R.id.fragment_content);


        if (articleFrag != null) {
            //平板
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            Log.v(TAG, Integer.toString(position));
            articleFrag.updateContentView(position);

        } else {
            //手機
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            Fragment_Content newFragment = new Fragment_Content();
            Bundle args = new Bundle();
            args.putInt(Fragment_Content.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment );
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();
        CharSequence text = "onStart!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onStart!!!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Context context = getApplicationContext();
        CharSequence text = "onRestart!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onRestart!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = getApplicationContext();
        CharSequence text = "onResume!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onResume!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Context context = getApplicationContext();
        CharSequence text = "onPause!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onPause!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Context context = getApplicationContext();
        CharSequence text = "onStop!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onStop!!!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Context context = getApplicationContext();
        CharSequence text = "onDestroy!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onDestroy!!!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Context context = getApplicationContext();
        CharSequence text = "onSaveInstanceState!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context, TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onSaveInstanceState!!!");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Context context = getApplicationContext();
        CharSequence text = "onRestoreInstanceState!!!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context,TAG + text, duration);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 350, 500);
//        toast.show();
        Log.v(TAG, "onRestoreInstanceState!!!");
    }
}