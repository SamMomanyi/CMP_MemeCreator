package com.plcoding.cmp_memecreator

import android.app.Application
import com.plcoding.cmp_memecreator.di.initKoin
import org.koin.android.ext.koin.androidContext

class MemeCreatorApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MemeCreatorApplication)
        }
    }
}