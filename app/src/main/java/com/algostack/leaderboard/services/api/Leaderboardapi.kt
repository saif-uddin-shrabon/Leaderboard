package com.algostack.leaderboard.services.api

import com.algostack.leaderboard.services.model.LeaderboardResponse
import retrofit2.Response
import retrofit2.http.GET


interface Leaderboardapi {
    @GET("/mhasancse17/JsonFile/main/leaderboard.json")
    suspend fun getLeaderBoardList() : Response<LeaderboardResponse>
}