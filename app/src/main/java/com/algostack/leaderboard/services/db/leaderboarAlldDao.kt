package com.algostack.leaderboard.services.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.algostack.leaderboard.services.model.All
import com.algostack.leaderboard.services.model.Top3

@Dao
interface leaderboarAlldDao {

    @Insert
    suspend fun upsert(all: List<All>)

    @Query("SELECT * FROM leaderboard_all ")
     fun getAllList() : List<All>

    @Delete
    suspend fun deleteAll(all: All)


}