package com.wt.radiusshapeview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init shape view
        activity_main_shape.setRadius(20)
        activity_main_shape.setRadiusColor(resources.getColor(R.color.colorAccent))

        // content click
        activity_main_content.setOnClickListener({
            if (activity_main_shape.visibility == View.VISIBLE) {
                activity_main_shape.visibility = View.GONE
            } else {
                activity_main_shape.visibility = View.VISIBLE
            }
        })
    }
}
