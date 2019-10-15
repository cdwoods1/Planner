package com.example.planner

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlannerUtils {

    var plannerBook : MutableMap<String, MutableList<PlannerListAdapter.PlannerEvent>?>

    init {
        plannerBook = HashMap()
    }

    fun getPlannerToDoList(date : String) : MutableList<PlannerListAdapter.PlannerEvent>? {
        if(plannerBook.get(date) != null)
            return plannerBook.get(date)
        else
            return mutableListOf<PlannerListAdapter.PlannerEvent>()
    }

    fun addEventToPlanner(date : String, newEvent : PlannerListAdapter.PlannerEvent) {
        if(plannerBook.get(date) == null)
            plannerBook[date]= mutableListOf()
        var tempList : MutableList<PlannerListAdapter.PlannerEvent>? = plannerBook[date]?.toMutableList()
        tempList?.add(newEvent)

        plannerBook[date] = tempList
    }

    fun removeEventFromPlanner(date: String, oldEvent : PlannerListAdapter.PlannerEvent) {
        if(plannerBook[date] == null)
            return
        val plannerCopy = plannerBook[date]?.toMutableList()?: return
        plannerCopy.forEach {
            if(it.date == oldEvent.date && it.time == oldEvent.time &&
                    it.name == oldEvent.name && it.details == oldEvent.details) {
                plannerBook[date]?.remove(oldEvent)

                if(plannerBook[date]?.size == 0)
                    plannerBook.remove(date)
            }
        }
    }
}