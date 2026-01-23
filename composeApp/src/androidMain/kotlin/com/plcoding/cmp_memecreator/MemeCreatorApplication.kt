package com.plcoding.cmp_memecreator

import android.app.Application
import com.plcoding.cmp_memecreator.di.initKoin

import org.koin.android.ext.koin.androidContext

class MemeCreatorApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        //in android context is needed for dependecies to be created
        //then registar this application inside androidManifest.xml
        initKoin {
            androidContext(this@MemeCreatorApplication)
        }
    }
}