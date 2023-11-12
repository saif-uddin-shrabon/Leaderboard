package com.algostack.leaderboard.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algostack.leaderboard.services.model.LeaderboardResponse
import com.algostack.leaderboard.services.repository.LeaderboardRepository
import com.algostack.leaderboard.utlis.NetworkResult
import com.algostack.leaderboard.view.Leaderboard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewmodel @Inject constructor(
    private val leaderboardRepository: LeaderboardRepository
) : ViewModel() {

    // Live data
    val leaderBoardLiveData : LiveData<NetworkResult<LeaderboardResponse>>
        get() =  leaderboardRepository.LidearboardResponseLiveData

    var applicationContext: Context? = null

    // api request
    fun viewLeaderBoard(){
        viewModelScope.launch {
            applicationContext?.let { leaderboardRepository.getLeaderboardList(it) }
        }
    }


}