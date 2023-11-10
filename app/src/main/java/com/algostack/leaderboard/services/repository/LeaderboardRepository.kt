package com.algostack.leaderboard.services.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.algostack.leaderboard.services.api.Leaderboardapi
import com.algostack.leaderboard.services.model.LeaderboardResponse
import com.algostack.leaderboard.utlis.NetworkResult
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val leaderboardapi: Leaderboardapi) {


    private val _LiderboardResponseLiveData = MutableLiveData<NetworkResult<LeaderboardResponse>> ()
    val LidearboardResponseLiveData : LiveData<NetworkResult<LeaderboardResponse>>

        get() = _LiderboardResponseLiveData

    // api request
    suspend fun getLeaderboardList(){
        _LiderboardResponseLiveData.postValue(NetworkResult.Loading())

        try {
            val response = leaderboardapi.getLeaderBoardList()

            if(response.isSuccessful && response.body() != null){

                _LiderboardResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

            }else if(response.errorBody() != null){
                _LiderboardResponseLiveData.postValue(NetworkResult.Error("Something went wrong !"))
            }else{
                _LiderboardResponseLiveData.postValue(NetworkResult.Error("Something went wrong !"))
            }
        }catch (e: IOException){
            _LiderboardResponseLiveData.postValue(NetworkResult.Error("No internet Connection"))
        }catch (e: TimeoutException){
            _LiderboardResponseLiveData.postValue(NetworkResult.Error("Request Timeout"))
        }catch (e: HttpException){
            _LiderboardResponseLiveData.postValue(NetworkResult.Error("Unexpected response"))
        }
    }

}