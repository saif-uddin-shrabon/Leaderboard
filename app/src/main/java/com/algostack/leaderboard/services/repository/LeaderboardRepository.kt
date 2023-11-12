package com.algostack.leaderboard.services.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.algostack.leaderboard.services.api.Leaderboardapi
import com.algostack.leaderboard.services.db.LeaderboardDatabase
import com.algostack.leaderboard.services.model.Data
import com.algostack.leaderboard.services.model.HostDaily
import com.algostack.leaderboard.services.model.LeaderboardResponse
import com.algostack.leaderboard.utlis.NetworkResult
import com.algostack.leaderboard.utlis.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(
    private val leaderboardapi: Leaderboardapi,
    private val leaderboardDatabase: LeaderboardDatabase


) {


    private val _LiderboardResponseLiveData = MutableLiveData<NetworkResult<LeaderboardResponse>> ()
    val LidearboardResponseLiveData : LiveData<NetworkResult<LeaderboardResponse>>

        get() = _LiderboardResponseLiveData

    // api request
    suspend fun getLeaderboardList(context: Context){
        _LiderboardResponseLiveData.postValue(NetworkResult.Loading())


           if (NetworkUtils.isInternetConnected(context)){
               try {
                   val response = leaderboardapi.getLeaderBoardList()

                   if(response.isSuccessful && response.body() != null){


                       // if data are fetch successfull then insert data in Local Database
                       leaderboardDatabase.getAllDao().upsert(response.body()!!.data.host_daily.all)
                       leaderboardDatabase.getTopDao().upsert(response.body()!!.data.host_daily.top3)


                       _LiderboardResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

                   }else if(response.errorBody() != null){
                       _LiderboardResponseLiveData.postValue(NetworkResult.Error("Something went wrong !"))
                   }else{
                       _LiderboardResponseLiveData.postValue(NetworkResult.Error("Something went wrong !"))
                   }
               }catch (e: TimeoutException){
                   _LiderboardResponseLiveData.postValue(NetworkResult.Error("Request Timeout"))
               }
           }else{

               withContext(Dispatchers.IO) {


                   val listAllLeaderBoard = leaderboardDatabase.getAllDao().getAllList()
                   val listTopLeaderBoard = leaderboardDatabase.getTopDao().getAllList()

                   val allleaderList = LeaderboardResponse(
                       Data(HostDaily(listAllLeaderBoard, listTopLeaderBoard)),
                       true
                   )

                   _LiderboardResponseLiveData.postValue(NetworkResult.Success(allleaderList))
               }


           }

        }


    }

