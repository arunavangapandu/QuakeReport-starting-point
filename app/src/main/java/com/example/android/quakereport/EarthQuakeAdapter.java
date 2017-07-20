package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vvangapandu on 6/7/17.
 */

public class EarthQuakeAdapter  extends ArrayAdapter<Earthquake> {

    private String separator = " of ";


   public EarthQuakeAdapter(Context context, int resource, List<Earthquake> objects) {
        super(context, resource, objects);
    }

    /*public EarthQuakeAdapter(Context context, List<Earthquake> objects) {
        super(context, 0, objects);
    }*/


    @Nullable
    @Override
    public Earthquake getItem(int position) {

        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;

        if(listItemView == null){

            listItemView =  LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }

        // Get the object located at this position in the list
        Earthquake earthquake = getItem(position);


        // Find the textview in list_item_layout.xml with the ID mag
        TextView magView = (TextView)listItemView.findViewById(R.id.mag);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable)magView.getBackground();


        // Get the appropriate background color based on the current earthquake magnitude
        int color = getMagnitudeColor(earthquake.getMagnitude());

        //set the color on the magnitude circle
        magnitudeCircle.setColor(color);


        // Find the textview in list_item_layout.xml with the ID locationOffset
        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.locationOffset);

        // Find the textview in list_item_layout.xml with the ID city
        TextView primaryLocationView = (TextView)listItemView.findViewById(R.id.primaryLocation);

        // Set the magnitude to the magView
        magView.setText(formatMagnitude(earthquake.getMagnitude()));

        // Split the city string
        String[] locationArray = splitCityString(earthquake.getCity());

        // Get the string form the resource file
        String nearText = getContext().getResources().getString(R.string.near_the);

        /* If there is no offset given from the primary location, have to set the
            String to the locationOffset textview */

        if(locationArray.length < 2){

            // Set the location to the locationOffsetView
            locationOffsetView.setText(nearText);

            // Set the location to the primaryLocationView
            primaryLocationView.setText(locationArray[0]);

        } else {
            // Set the location to the locationOffsetView
            locationOffsetView.setText(locationArray[0] + separator);

            // Set the location to the primaryLocationView
            primaryLocationView.setText(locationArray[1]);

        }

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(earthquake.getTime());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);

        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);

        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        return listItemView;
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    // Split the string with the given delimitter, and return an array of strings

    private String[] splitCityString(String str){
        String[] location =  str.split(separator);
        return location;
    }

    private String formatMagnitude(double magnitude){
        // Create the instance of DecimalFormat by passing a pattern
        DecimalFormat formatter = new DecimalFormat("0.0");
        // format the decimal magnitude into string magnitude by showing
        // only 1 decimal value
        String mag = formatter.format(magnitude);
        return mag;

    }

    private  int getMagnitudeColor(double magnitude){

        int magnitudeColorResourceId;

        int magnitudeFloor = (int)Math.floor(magnitude);

        switch(magnitudeFloor){
            case 0:

            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;

            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;

            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;

            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;

            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;

            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;

            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;

            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;

            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;

            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);

    }

}
