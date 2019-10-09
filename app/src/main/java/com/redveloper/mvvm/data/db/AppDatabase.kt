package com.redveloper.mvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.redveloper.mvvm.data.db.entities.UsersModel

@Database(
    entities = [UsersModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao(): UserDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:builDatabase(context).also {
                instance = it
            }
        }

        private fun builDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}