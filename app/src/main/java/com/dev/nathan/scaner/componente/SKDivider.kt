package com.dev.nathan.scaner.componente

import android.content.Context
import android.graphics.Color
import android.view.ViewManager
import android.widget.RelativeLayout

import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip

fun ViewManager.skDivider(): SKDivider = skDivider {}
inline fun ViewManager.skDivider(init: (@AnkoViewDslMarker SKDivider).() -> Unit): SKDivider =
        ankoView({ ctx: Context -> SKDivider(ctx) }, theme = 0) { init() }

class SKDivider(ctx : Context) : _LinearLayout(ctx){
    init {
        generateViewId()
        backgroundColor =  Color.WHITE
    }

    fun lparams(
            width: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            height: Int = dip(1),
            init: RelativeLayout.LayoutParams.() -> Unit
    ): SKDivider {
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        layoutParams.init()
        this.layoutParams = layoutParams
        return this
    }

    fun lparams(
            width: Int = android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            height: Int = dip(1)
    ): SKDivider {
        val layoutParams = RelativeLayout.LayoutParams(width, height)
        this.layoutParams = layoutParams
        return this
    }
}