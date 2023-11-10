package com.algostack.leaderboard.services.model

data class Top3(
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