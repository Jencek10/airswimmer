package com.example.teaching.project7

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {
    private val MAC = "E0:E5:CF:23:C4:87"
    private lateinit var ble:BLEConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ble = BLEConnection()
        getConnection()
        seekBarLeft.progress = 0
        seekBarLeft.incrementProgressBy(1)
        seekBarLeft.max = 5

        seekBarUD.progress = 0
        seekBarUD.incrementProgressBy(1)
        seekBarUD.max = 5

        seekBarRight.progress = 0
        seekBarRight.incrementProgressBy(1)
        seekBarRight.max = 5




        seekBarLeft.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                txtL.text = progress.toString()

                val d = ByteArray(1)
                d[0] = progress.toByte()
                ble.sendData(d)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) { }
        })

        seekBarUD.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                txtUD.text = progress.toString()

                val d = ByteArray(1)
                d[0] = (progress+10).toByte()
                ble.sendData(d)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) { }
        })

        seekBarRight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                txtR.text = progress.toString()

                val d = ByteArray(1)
                d[0] = (progress+20).toByte()
                ble.sendData(d)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) { }
        })

        btnBT.setOnClickListener(){
            getConnection()
        }

        btnForward.setOnClickListener() {
            val d = ByteArray(1)
            d[0] = 30.toByte()
            ble.sendData(d)
                    }
        btnLeft.setOnClickListener(){
            val d = ByteArray(1)
            d[0] = 25.toByte()
            ble.sendData(d)

        }

        btnRight.setOnClickListener(){
            val d = ByteArray(1)
            d[0] = 5.toByte()
            ble.sendData(d)

        }

        btnStop.setOnClickListener() {
            val d = ByteArray(1)
            d[0] = 31.toByte()
            ble.sendData(d)
            

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
