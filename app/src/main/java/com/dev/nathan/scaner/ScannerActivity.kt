package com.dev.nathan.scaner

import android.app.Activity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.InputFilter
import android.text.InputType

import com.google.zxing.client.android.BeepManager

import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.BarcodeCallback
import android.view.*
import android.view.View.generateViewId
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.EditText
import org.jetbrains.anko.*

import org.jetbrains.anko.sdk25.coroutines.onClick


class ScannerActivity : Activity() {
    private var barcodeView: DecoratedBarcodeView? = null
    private var beepManager: BeepManager? = null
    private var lastText: String? = null
    private lateinit var ui: ScannerActivityUi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ScannerActivityUi()
        ui.setContentView(this)

        //region <! Quando clicar no botao digite o codigo de barras !>
        ui.enterBarcode.onClick { showDialog() }
        //endregion

        //region <! Istanciando o leitor de codigo de barras !>


        //deixando layout default do barcode invisivel

        ui.barcodeViews.barcodeView.decoderFactory
        ui.barcodeViews.viewFinder.visibility = View.GONE
        //deixando o barcode continuo
      //  ui.barcodeViews?.decodeContinuous(callback)

        //musiquinha de leitura de codigo de barra
        beepManager = BeepManager(this)


        //endregion

        //region <! Torna a tela modo fullScream !>
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //endregion

    }



    override fun onResume() {
        super.onResume()
        ui.barcodeViews.resume()
    }

    override fun onPause() {
        super.onPause()
        ui.barcodeViews.pause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return ui.barcodeViews!!.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }




    //region  <! Funçao da chamada do alert dialog para escrever o codigo de barras manualmente !>
    private fun showDialog() {
        var codeBarEditText: EditText? = null
        alert {
            customView {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    textView {
                        gravity = Gravity.CENTER
                        text = ("Codigo de barras")
                        textSize = 18f
                        textColor = ContextCompat.getColor(context, R.color.colorBlack)
                    }.lparams(matchParent) {
                        topMargin = dip(20)
                    }
                    codeBarEditText = editText {
                        generateViewId()
                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                        textColor = ContextCompat.getColor(context, R.color.colorBlack)
                        hintTextColor = ContextCompat.getColor(context, R.color.colorGray)
                        filters = arrayOf(InputFilter.LengthFilter(25), InputFilter { source, start, end, _, _, _ ->
                            for (i in start until end) {
                                if (source?.get(i) == ' ') {
                                    return@InputFilter ""
                                }
                            }
                            null
                        })
                        imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI
                    }.lparams(matchParent) {
                        topMargin = dip(10)
                        marginStart = dip(20)
                        marginEnd = dip(20)
                    }
                    lparams(matchParent, wrapContent)
                }
            }
            positiveButton("Confirmar", {
                if (!codeBarEditText?.text.isNullOrEmpty()) {
                    //TODO: tirar a linha abaixo substituir por uma intent #print temporario
                    ui.barcodeViews?.setStatusText(codeBarEditText?.text.toString())
                    beepManager?.playBeepSoundAndVibrate()
                }
            })
            negativeButton("Cancelar", {})
        }.show()
    }
    //endregion

    //region <! Recebe o return do Barcode com o codigo de barras !>
    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            if (result.text == null || result.text == lastText) {
                // Impedir scans duplicados
                return
            }
            lastText = result.text
            //TODO: tirar a linha abaixo substituir por uma intent #print temporario
            ui.barcodeViews?.setStatusText(result.text)
            beepManager?.playBeepSoundAndVibrate()
        }

        override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
    }
    //endregion

}