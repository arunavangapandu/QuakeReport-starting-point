/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.example.android.quakereport;

//import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

//import java.util.ArrayList;
//import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /** Adapter for the list of earthquakes */
    private EarthQuakeAdapter mAdapter;

    /** Sample JSON response for a USGS query */
    private static final String USGS_REQUEST_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);


        // Create a new {@link ArrayAdapter} of earthquakesList
        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthQuakeAdapter(this, R.layout.list_item_layout,  QueryUtils.fetchEarthquakeData(USGS_REQUEST_URL));

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        // Start the AsyncTask to fetch the earthquake data
        /*EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
        task.execute(USGS_REQUEST_URL);*/
    }


   /* public class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<Earthquake> > {

        @Override
        protected List<Earthquake> doInBackground(String... strings) {

            // Don't perform the request if there are no URLs, or the first URL is null.
            if (strings.length < 1 || strings[0] == null) {
                return null;
            }

            List<Earthquake> result = QueryUtils.fetchEarthquakeData(strings[0]);
            return result;
        }


        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */

      //  @Override
      /*  protected void onPostExecute(List<Earthquake> earthquakesList) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (earthquakesList != null && !earthquakesList.isEmpty()) {
                mAdapter.addAll(earthquakesList);
            }
        }*/

    }

