package com.alex.microprojectmvvm.di.module

import com.alex.microprojectmvvm.manager.RealmDataManager
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class RealmModule {

    @Provides
    @Singleton
    fun provideRealm() : Realm =
        Realm.getDefaultInstance()

    @Provides
    @Singleton
    fun provideRealmDataManager(realm: Realm) : RealmDataManager =
        RealmDataManager(realm)
}