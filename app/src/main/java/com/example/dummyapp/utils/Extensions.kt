package com.example.dummyapp.utils

import android.view.View

fun View.gone(){
    visibility= View.GONE
}
fun View.visible(isVisible:Boolean){
    visibility= if(isVisible)View.VISIBLE else View.INVISIBLE
}