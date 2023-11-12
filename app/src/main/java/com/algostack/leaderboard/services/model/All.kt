package com.algostack.leaderboard.services.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "leaderboard_all"
)
data class All(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val first_name: String,
    val gender: String,
    val giftcoin: Int,
    val hive_id: String,
    val last_name: String,
    val level: Int,
    val position: Int,
    val profile_pic: String,
    val user_tag: String,
    val userid: String,
    val username: String
)