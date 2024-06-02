package com.example.libro

import android.app.Application
import androidx.room.Room
import com.example.libro.database.LibroDB

class Arranque: Application() {

    companion object{
        lateinit var room: LibroDB
    }

    override fun onCreate() {
        super.onCreate()
        room = Room
            .databaseBuilder(applicationContext, LibroDB::class.java, "libro_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}