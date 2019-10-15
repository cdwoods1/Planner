package com.example.planner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PlannerListAdapter(
    numberOfItems: Int?,
    toDoList: MutableList<PlannerEvent>?,
    planner: PlannerUtils
) : RecyclerView.Adapter<PlannerListAdapter.PlannerItemView>() {


    data class PlannerEvent(var date : String?, var time: String?, var name : String?,
                            var details: String?)

    private val numItems : Int? = numberOfItems
    private var listToDo  = toDoList
    private val planner = planner


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlannerItemView {
        val inflator: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflator.inflate(R.layout.planner_list_item, parent, false)
        return PlannerItemView(view)

    }

    override fun getItemCount(): Int {
        if(numItems == null)
            return 0
        return numItems

    }

    override fun onBindViewHolder(holder: PlannerItemView, position: Int) {

        val tempList = listToDo
        if(tempList != null && tempList.size > 0)
            holder.Bind(tempList[position])
    }

    fun removeItem(position: Int) {
        val event : PlannerEvent = listToDo!![position]
        listToDo!!.removeAt(position)
        planner.removeEventFromPlanner(event.date.toString(), event)
        notifyItemRemoved(position)
        notifyDataSetChanged()

    }

    inner class PlannerItemView(val view: View) : RecyclerView.ViewHolder(view) {
        var date : TextView
        var time : TextView
        var eventName : TextView
        var eventDetails : TextView

        init {
            date = view.findViewById(R.id.date)
            time = view.findViewById(R.id.time)
            eventName = view.findViewById(R.id.event_name)
            eventDetails = view.findViewById(R.id.event_details)
        }

        fun Bind(currentPlan : PlannerEvent) {
            date.text = currentPlan.date
            time.text = currentPlan.time
            eventName.text = currentPlan.name
            eventDetails.text = currentPlan.details

            val editButton : Button = view.findViewById(R.id.edit_event)
            val deleteButton : Button = view.findViewById(R.id.delete_button)

            editButton.setOnClickListener {
                Toast.makeText(view.context, "Edit Clicked: "+view.id , Toast.LENGTH_LONG ).show()
            }

            deleteButton.setOnClickListener {
                Toast.makeText(view.context, "DElete Clicked: "+view.id, Toast.LENGTH_LONG ).show()

                removeItem(adapterPosition)
            }
        }
    }
}