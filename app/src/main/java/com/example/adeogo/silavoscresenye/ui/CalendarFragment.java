package com.example.adeogo.silavoscresenye.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.model.CalendarEvent;
import com.example.adeogo.silavoscresenye.utilities.ConstantUtils;
import com.example.adeogo.silavoscresenye.utilities.JSONUtil;
import com.example.adeogo.silavoscresenye.utilities.NetworkUtil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Adeogo on 9/12/2017.
 */

public class CalendarFragment extends Fragment {

    List<CalendarEvent> mEventList;
    CalendarView mCalendarView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);

        mCalendarView = (CalendarView) rootView.findViewById(R.id.calendar_view);

        CalendarTask calendarTask = new CalendarTask();
        calendarTask.execute();
        return rootView;
    }

    public void setUpCalendar() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        Set<Long> days = new TreeSet<>();
        for (int i = 0; i < mEventList.size(); i++){
            CalendarEvent calendarEvent = mEventList.get(i);
            long day = JSONUtil.getUnixTime(calendarEvent.getStartTime());
            days.add(day);
        }

        int textColor = Color.parseColor("#ff0000");
        int selectedTextColor = Color.parseColor("#ff4000");
        int disabledTextColor = Color.parseColor("#ff8000");

        ConnectedDays connectedDays = new ConnectedDays(days, textColor, selectedTextColor, disabledTextColor);

//Connect days to calendar
        mCalendarView.addConnectedDays(connectedDays);

    }

    public class CalendarTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            try {
                result = NetworkUtil.getCalendarResponseFromHttpUrl(ConstantUtils.CalendarUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            mEventList = JSONUtil.getCalendarEventList(s);
            Toast.makeText(getActivity(), mEventList.get(0).getName(), Toast.LENGTH_SHORT).show();
            try {
                JSONUtil.getUnixTime("");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);

            try {
                setUpCalendar();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
