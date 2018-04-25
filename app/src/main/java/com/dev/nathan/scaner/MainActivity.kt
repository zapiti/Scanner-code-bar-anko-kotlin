package com.dev.nathan.scaner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Button
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    private lateinit var scanBtn: Button
    private lateinit var ui: MainActivityUi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui = MainActivityUi()
        ui.setContentView(this)

        scanBtn = find(MainActivityUi.scan_button) as Button



        scanBtn.onClick {
            //region <! Setando a o layout do scaner !>
            val scanIntegrator = IntentIntegrator(this@MainActivity)
            scanIntegrator.captureActivity = (ScannerActivity::class.java)
                    scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)//aceitando todos os tipos de codigo
                    scanIntegrator.setOrientationLocked(true)
            scanIntegrator.setBeepEnabled(true)//ativando a musiquinha
            scanIntegrator.initiateScan()
        }

    }



}