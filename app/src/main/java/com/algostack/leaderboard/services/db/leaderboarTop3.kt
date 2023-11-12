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
interface leaderboarTop3 {

    @Insert
    suspend fun upsert(top3: List<Top3>)

    @Query("SELECT * FROM leaderboard_top3 ")
     fun getAllList() : List<Top3>

    @Delete
    suspend fun deleteAll(top3: Top3)

}