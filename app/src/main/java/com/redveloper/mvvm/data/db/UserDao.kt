package com.redveloper.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.redveloper.mvvm.data.db.entities.CURRENT_USER_ID
import com.redveloper.mvvm.data.db.entities.UsersModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usersModel: UsersModel) : Long

    @Query("SELECT * FROM usersmodel WHERE uid = $CURRENT_USER_ID")
    fun getUser() :LiveData<UsersModel>
}