package com.algostack.leaderboard.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algostack.leaderboard.services.model.LeaderboardResponse
import com.algostack.leaderboard.services.repository.LeaderboardRepository
import com.algostack.leaderboard.utlis.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewmodel @Inject constructor(private val leaderboardRepository: LeaderboardRepository) : ViewModel() {

    val leaderBoardLiveData : LiveData<NetworkResult<LeaderboardResponse>>
        get() =  leaderboardRepository.LidearboardResponseLiveData

    fun viewLeaderBoard(){
        viewModelScope.launch {
            leaderboardRepository.getLeaderboardList()
        }
    }


}