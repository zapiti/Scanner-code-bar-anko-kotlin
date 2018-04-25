package com.dev.nathan.scaner.componente

import android.content.Context
import android.view.ViewManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko.custom.ankoView

fun ViewManager.decoratedBarcodeView(): DecoratedBarcodeView = decoratedBarcodeView {}
inline fun ViewManager.decoratedBarcodeView (init: (@AnkoViewDslMarker DecoratedBarcodeView).() -> Unit): DecoratedBarcodeView =
        ankoView({ ctx: Context -> DecoratedBarcodeView(ctx) }, theme = 0) { init() }

class DecoratedBarcodeViewt(ctx: Context) : DecoratedBarcodeView(ctx) {
    init {
        generateViewId()
    }
}
