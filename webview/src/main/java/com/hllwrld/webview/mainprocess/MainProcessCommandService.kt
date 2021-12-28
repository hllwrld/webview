package com.hllwrld.webview.mainprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MainProcessCommandService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return  MainProcessCommandsManager.asBinder()
    }
}