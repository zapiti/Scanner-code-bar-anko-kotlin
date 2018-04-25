package com.dev.nathan.scaner

import android.view.Gravity
import org.jetbrains.anko.*


class MainActivityUi : AnkoComponent<MainActivity>{
    companion object {
        val scan_button = 666
    }
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            button {
                id = scan_button
                text = resources.getString(R.string.scan)

            }.lparams {
                gravity = Gravity.CENTER
            }
        }
    }

}