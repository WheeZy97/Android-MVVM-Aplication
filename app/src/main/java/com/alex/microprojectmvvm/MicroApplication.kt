package com.alex.microprojectmvvm

import android.app.Application
import com.alex.microprojectmvvm.di.component.ApplicationComponent
import com.alex.microprojectmvvm.di.component.DaggerApplicationComponent
import com.alex.microprojectmvvm.di.module.RoomModule
import io.realm.Realm
import io.realm.RealmConfiguration

class MicroApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
        initRealm()
    }

    private fun createRoomModule() : RoomModule =
        RoomModule(applicationContext)

    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
            .roomModule(createRoomModule())
            .build()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("microDB.realm")
            .schemaVersion(0L)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        Realm.getDefaultInstance()
    }
}