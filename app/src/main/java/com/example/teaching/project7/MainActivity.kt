package com.example.teaching.project7

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val MAC = "E0:E5:CF:23:C4:87"
    private lateinit var ble:BLEConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ble = BLEConnection()
        getConnection()


        btnTest.setOnClickListener {
            if(!ble.canSendData())
                return@setOnClickListener;

            ble.sendData("r")
        }

    }

    private fun getConnection()
    {
        var connectingStarted = ble.connect(this , MAC, object: BLEConnection.ConnectionChangedListener {
            override fun onConnectionChanged(connected: Boolean) {
                if (connected)
                    lblInfo.setText("Connected")
                else
                    lblInfo.setText("Disconnected")
            }
      });}
}
