package com.algostack.leaderboard.view

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.algostack.leaderboard.R
import com.algostack.leaderboard.databinding.FragmentHomeBinding
import com.algostack.leaderboard.utlis.NetworkResult
import com.algostack.leaderboard.viewmodel.LeaderboardViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private var _binding : FragmentHomeBinding ?= null
   private val binding = _binding!!

    private val leaderboardViewmodel by viewModels<LeaderboardViewmodel> ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  bindObservers()
    }

    private fun bindObservers() {


        leaderboardViewmodel.leaderBoardLiveData.observe(viewLifecycleOwner, Observer {result ->

            when(result){
                is NetworkResult.Success -> {
                    if(result.data!!.status){

                        println("Check_response: ${result.data.data.host_daily.all}")

                    }else{
                        showCustomAlertDialogBox( result.message ?: "Something wrong")
                    }
                }
                is NetworkResult.Error -> {

                    showCustomAlertDialogBox( result.message ?: "Something went wrong")
                }
                is NetworkResult.Loading -> {


                }
            }


        })


    }

    fun showCustomAlertDialogBox(msg : String){
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_alert_box, null,false)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(view)

        val alert = builder.create()
        alert.setCancelable(true)

        val okBtn = view.findViewById<TextView>(R.id.okBtn)
        val textView = view.findViewById<TextView>(R.id.alertText)

        textView.text = msg


        okBtn.setOnClickListener {
            alert.dismiss()
        }

        alert.window?.setBackgroundDrawable(ColorDrawable(0))
        alert.show()



    }


}