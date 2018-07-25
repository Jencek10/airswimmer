package com.example.teaching.project7

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.DriverManager.getConnection
import android.widget.Toast
import android.widget.SeekBar.OnSeekBarChangeListener



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

        var speed = 0
findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        if (speed < 20){
            ble.sendData("s")
            ble.sendData("x")
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // TODO Auto-generated method stub
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        // TODO Auto-generated method stub
        if (speed != progress){
            speed = progress
        }
        // progres je od 0-100 u seekbaru, speed musi byt pro S nebo X u u/D motoru
        Toast.makeText(applicationContext, progress.toString(), Toast.LENGTH_LONG).show()

    }
})

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
