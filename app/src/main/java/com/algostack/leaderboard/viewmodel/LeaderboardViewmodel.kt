package com.algostack.leaderboard.viewmodel

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewmodel @Inject constructor(private val leaderboardRepository: LeaderboardRepository) : ViewModel() {

    // Live data
    val leaderBoardLiveData : LiveData<NetworkResult<LeaderboardResponse>>
        get() =  leaderboardRepository.LidearboardResponseLiveData

    // api request
    fun viewLeaderBoard(){
        viewModelScope.launch {
            leaderboardRepository.getLeaderboardList()
        }
    }


    // check internet connection function
    fun isInternetConnected(context: Context): Boolean{
        val connecttivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return when{
          Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ->{
              val activityNetwork = connecttivityManager.activeNetwork ?: return false
              val cap = connecttivityManager.getNetworkCapabilities(activityNetwork) ?: return false
              when {
                  cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                  cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                  else -> false
              }
          }else -> {
                // Use Deprecated method only on older devices
              val activeNetwork = connecttivityManager.activeNetworkInfo ?: return false
              return  when (activeNetwork.type){
                    ConnectivityManager.TYPE_MOBILE -> true
                  ConnectivityManager.TYPE_WIFI -> true
                  ConnectivityManager.TYPE_VPN -> true
                  else -> false

                }

          }
        }
    }


}