package com.example.dummyapp.extensions

import android.view.View

fun View.gone(){
    visibility= View.GONE
}
fun View.visible(isVisible:Boolean){
    visibility= if(isVisible)View.VISIBLE else View.INVISIBLE
}