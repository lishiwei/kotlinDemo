package com.orientalfinance.eastcloud.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ngblab.intelcontrol_sdk.util.InteControlHelper
import com.ngblab.intelcontrol_sdk.util.RequestListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        if (!InteControlHelper.getInstance().isOpened) {
            InteControlHelper.getInstance().open()
        } else {
            when (p0?.id) {
                R.id.btn_HotKey
                -> InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "hotkey", "9999")
                R.id.btn_query -> InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "query", null)
                R.id.btn_Virtualkey -> InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "virtualkey", "10002")
                R.id.btn_Msg -> InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "msg", "10002")
                R.id.btn_Refresh -> InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "refresh", "10002")
                R.id.btn_vod -> InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "vod", "10002")
            }

        }
    }

    companion object {
        private val TAG = "MainActivity"
    }

    lateinit var adapter: Adapter
    var data: List<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        com.ngblab.intelcontrol_sdk.util.Log.setLog(true)

        InteControlHelper.getInstance(this, "51000000648115", "YUESHIIDHL1JK7HJIK", "")
        btn_HotKey.setOnClickListener(this)
        btn_Msg.setOnClickListener(this)
        btn_Refresh.setOnClickListener(this)
        btn_vod.setOnClickListener(this)
        btn_Virtualkey.setOnClickListener(this)
        btn_query.setOnClickListener(this)
        InteControlHelper.getInstance().setListener(object : RequestListener {
            override fun onOpen() {
                Log.d(TAG, "open")
            }

            override fun onMessage(s: String) {
                Log.d(TAG, "onMessage" + s)

                Toast.makeText(baseContext, s, Toast.LENGTH_SHORT).show()
            }

            override fun onClose() {

            }

            override fun onError(s: String) {
                Log.d(TAG, "onError" + s)
            }
        })

        data = listOf("up", "down", "left", "right", "ok", "vup", "vdown", "epg", "vod", "quit", "back", "mute", "halt", "pd", "pu", "fw", "bw", "smart", "red", "yellow", "green", "blue")
        adapter = Adapter(data) {
            if (!InteControlHelper.getInstance().isOpened) {
                InteControlHelper.getInstance().open()
            } else {
                Log.d("position", it.toString())
                InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "action", data.get(it))
            }
        }
        recyclerview.adapter = adapter
        recyclerview.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)

        btn_Input.setOnClickListener {
            if (!InteControlHelper.getInstance().isOpened) {
                InteControlHelper.getInstance().open()
            } else {
                Log.d("position", it.toString())
                InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "input", edt_Input.text.toString())
            }
        }
        btn_Switch.setOnClickListener {
            if (!InteControlHelper.getInstance().isOpened) {
                InteControlHelper.getInstance().open()
            } else {
                Log.d("position", it.toString())
                InteControlHelper.getInstance().sendAction("YP8EnUi-aeSMWg3vP1BdXA==", "switch", edt_Switch.text.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        InteControlHelper.getInstance().destroy()
    }


}
