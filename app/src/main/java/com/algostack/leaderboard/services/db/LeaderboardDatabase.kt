package com.algostack.leaderboard.services.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.algostack.leaderboard.services.model.All
import com.algostack.leaderboard.services.model.Top3
import dagger.Module
import dagger.Provides
import java.util.concurrent.locks.Lock


@Database(
    entities = [All::class, Top3::class],
    version = 1
)
abstract class LeaderboardDatabase : RoomDatabase() {

    abstract fun getAllDao(): leaderboarAlldDao // Correct the DAO interface name
    abstract fun getTopDao(): leaderboarTop3 // Correct the DAO interface name


}
