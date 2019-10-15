package com.example.planner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class AddEventActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_form)

        val submitButton : Button = this.findViewById(R.id.submit_event)
        submitButton.setOnClickListener {
            onClickSubmitButton()
        }

        hideKeyboard(this)
    }

    private fun onClickSubmitButton() {

        val nameEditText : EditText = findViewById(R.id.event_name_edit_text)
        val eventDatePicker : DatePicker = findViewById(R.id.date_selector)
        val eventTimeSelector : TimePicker = findViewById(R.id.event_time_picker)
        val eventExtraDetails : EditText = findViewById(R.id.detail_text_box)

        val getDayOfMonth = eventDatePicker.dayOfMonth
        val getYear = eventDatePicker.year
        val getMonth = eventDatePicker.month
        val dateToAppend = "" + getDayOfMonth + "/" + (getMonth + 1) + "/" + getYear

        val getHour = eventTimeSelector.hour
        val getMinute = eventTimeSelector.minute

        val timeToAppend = "" + getHour + ":" + getMinute

        val resultIntent = Intent()
        resultIntent.putExtra("EventName", nameEditText.text.toString())
        resultIntent.putExtra("EventDate", dateToAppend)
        resultIntent.putExtra("EventTime", timeToAppend)
        resultIntent.putExtra("EventDetails", eventExtraDetails.text.toString())
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}