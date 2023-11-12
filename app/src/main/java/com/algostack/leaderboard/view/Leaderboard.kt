package com.algostack.leaderboard.view

import android.app.Application
import com.algostack.leaderboard.services.api.Leaderboardapi
import com.algostack.leaderboard.services.db.LeaderboardDatabase
import com.algostack.leaderboard.services.repository.LeaderboardRepository
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import javax.inject.Inject

@HiltAndroidApp
class Leaderboard : Application() {

}