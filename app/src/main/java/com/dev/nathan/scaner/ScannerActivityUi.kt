package com.dev.nathan.scaner

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.Gravity
import org.jetbrains.anko.*
import android.widget.Button
import com.dev.nathan.scaner.componente.SKDivider
import com.dev.nathan.scaner.componente.decoratedBarcodeView
import com.dev.nathan.scaner.componente.skDivider
import com.journeyapps.barcodescanner.DecoratedBarcodeView


class ScannerActivityUi: AnkoComponent<ScannerActivity>{

    lateinit var scanner : SKDivider
    lateinit var enterBarcode : Button
    lateinit var barcodeViews: DecoratedBarcodeView

    companion object {
        const val  BAR_CODE_SCANER = 1
        const val LAYOUT1 = 2
        const val LAYOUT2 = 3
    }


    override fun createView(ui: AnkoContext<ScannerActivity>)= with(ui){
        relativeLayout{

            //region <! Area do scaneamento !>
        barcodeViews  =  decoratedBarcodeView {
                id = BAR_CODE_SCANER
            }
            //endregion

            //region <! Area superior do layout com a cor transparente !>
            linearLayout{
                id= LAYOUT1
                backgroundColor = ContextCompat.getColor(context, R.color.transparentLayout)

                //texto de orientaçao
                textView {
                    text = "Posicione a linha sobre o código de barras e aguarde."
                    textSize = 16F
                    textColor = Color.WHITE
                }.lparams {
                    gravity = Gravity.CENTER
                    marginStart = dip(8)
                }

            }.lparams(matchParent,dip(100)){
                alignParentTop()
            }
            //endregion

            //region <! Area ilustrativa do centro do scaner !>
            relativeLayout(){
                scanner  = skDivider {
                    backgroundColor = Color.GREEN

                }.lparams(matchParent, dip(3)) {
                    gravity = Gravity.CENTER
                }
            }.lparams(matchParent){
                below(LAYOUT1)
                above(LAYOUT2)
            }
            //endregion

            //region <! Area inferior do layout com a cor transparente !>
            linearLayout {
                id= LAYOUT2
                backgroundColor = ContextCompat.getColor(context, R.color.transparentLayout)

                //botao de entrada do codigo de barras
                enterBarcode = button {
                    text = "Digitar o codigo de barras"


                }.lparams(wrapContent, wrapContent){
                    weight = 1F

                    gravity = Gravity.CENTER
                    setMargins(dip(30),0,dip(30),0)

                }
            }.lparams(matchParent,dip(100)){
                alignParentBottom()
            }
            //endregion

        }

    }

}