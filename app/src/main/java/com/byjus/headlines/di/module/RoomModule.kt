package com.byjus.headlines.di.module

import android.content.Context
import androidx.room.Room
import com.byjus.headlines.di.database.LocalDatabase
import com.byjus.headlines.di.qualifiers.ApplicationContext
import com.byjus.headlines.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule
{
    @Provides
    @ApplicationScope
    fun getdb(@ApplicationContext context:Context):LocalDatabase
    {
        return Room.databaseBuilder(context,LocalDatabase::class.java,"newsdatabase").build()
    }

}