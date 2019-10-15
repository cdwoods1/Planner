package com.example.planner

import android.view.View

interface MyAdapterListener {

    fun iconTextViewOnClick(v: View, position: Int)
    fun iconImageViewOnClick(v: View, position: Int)
}