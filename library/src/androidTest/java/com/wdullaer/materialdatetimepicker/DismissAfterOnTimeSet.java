package com.wdullaer.materialdatetimepicker;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import junit.framework.Assert;

import org.junit.Before;

public class DismissAfterOnTimeSet {
    ActivityTestRule<MainActivity> testRule = new ActivityTestRule<MainActivity>(MainActivity.class,false,true);
    private TimePickerDialog timePickerDialog;

    @Before
    public void setUp() throws Exception {
        timePickerDialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                view.show(view.getFragmentManager(),"timepicker");
            }
        }, 0, 0, 0, false);
        testRule.launchActivity(null);
    }

    @org.junit.Test
    public void onTimeSetShowsDialog() throws Exception {
        timePickerDialog.show(testRule.getActivity().getFragmentManager(),"timepicker");
        Espresso.onView(ViewMatchers.withId(R.id.ok)).perform(ViewActions.click());
        Assert.assertTrue(timePickerDialog.isVisible());
    }
}
