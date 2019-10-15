package com.example.planner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var planner : PlannerUtils? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickAddEvent()
        planner = PlannerUtils()

        val recyclerView: RecyclerView = findViewById(R.id.ToDoList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        var currentDaySelection : String


        val calendarView = findViewById<CalendarView>(R.id.plannerCalendar)
        calendarView?.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val msg = "Selected date is " + dayOfMonth + "/" + (month + 1) + "/" + year
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            val textDisplayingDate = findViewById<TextView>(R.id.dateSelected)
            currentDaySelection = "" + dayOfMonth + "/" + (month + 1) + "/" + year
            textDisplayingDate.text = currentDaySelection
                val newList : MutableList<PlannerListAdapter.PlannerEvent>?
                    = planner?.getPlannerToDoList(currentDaySelection)

            val plan = planner
            recyclerView.adapter = PlannerListAdapter(newList?.size, newList, plan!!)
        }

    }

    private fun onClickAddEvent() {
        val addEventButton : Button = findViewById(R.id.add_button)
        addEventButton.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        data: Intent?
    ) {
        val recyclerView: RecyclerView = findViewById(R.id.ToDoList)


        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val returnName = data?.getStringExtra("EventName")
                val returnDate = data?.getStringExtra("EventDate")
                val returnTime = data?.getStringExtra("EventTime")
                val returnDetails = data?.getStringExtra("EventDetails")
                if(returnDate != null) {
                    planner?.addEventToPlanner(
                        returnDate, PlannerListAdapter.PlannerEvent(
                            returnDate, returnTime, returnName, returnDetails
                        )
                    )

                    val newList : MutableList<PlannerListAdapter.PlannerEvent>?
                            = planner?.getPlannerToDoList(returnDate)


                    val plan = planner
                    if(returnDate == dateSelected.text.toString() )
                        recyclerView.adapter = PlannerListAdapter(newList?.size, newList, plan!!)
                }
            }
        }
    }


}
