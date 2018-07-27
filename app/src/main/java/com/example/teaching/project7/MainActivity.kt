package com.example.teaching.project7

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.*


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


        val seekBarLeft = findViewById<SeekBar>(R.id.seekBarLeft) as SeekBar
        seekBarLeft.progress = 0
        seekBarLeft.incrementProgressBy(51)
        seekBarLeft.max = 255
        val seekBarValue = findViewById<TextView>(R.id.lblInfo) as TextView
       // seekBarValue.setText(tvRadius.getText().toString().trim())

        seekBarLeft.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                var progress = progress
                progress = progress / 51
                progress = progress * 51
                seekBarValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val button = findViewById<Button>(R.id.sendData) as Button

        button.setOnClickListener {
            if(!ble.canSendData())
                return@setOnClickListener;


            Log.d("BLE", "PRogress BAR:   " + seekBarLeft.progress.toString());

            if (seekBarLeft.progress == 0){
                Log.d("BLE", "f");
                ble.sendData("f")
            } else if (seekBarLeft.progress == 51){
                Log.d("BLE", "g");
                ble.sendData("g")
            } else if (seekBarLeft.progress == 102){
                ble.sendData("h")
            }
            else if (seekBarLeft.progress == 153){
                ble.sendData("j")
            }
           else if (seekBarLeft.progress == 204){
            ble.sendData("k")
            }
            else if (seekBarLeft.progress == 255){
                ble.sendData("l")
            }




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
