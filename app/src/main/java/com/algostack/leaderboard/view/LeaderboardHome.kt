package com.algostack.leaderboard.view

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.algostack.leaderboard.R
import com.algostack.leaderboard.databinding.FragmentLeaderboardHomeBinding
import com.algostack.leaderboard.utlis.NetworkResult
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaderboardHome : Fragment() {

   private var _binding: FragmentLeaderboardHomeBinding ?= null
    private val binding get() = _binding!!

    private val leaderboardViewmodel by viewModels<LeaderboardViewmodel> ()

    private lateinit var adapter: LeaderBoardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        _binding = FragmentLeaderboardHomeBinding.inflate(inflater,container,false)

        adapter = LeaderBoardAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         leaderboardViewmodel.viewLeaderBoard()

        binding.reclist.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        binding.reclist.adapter = adapter




         bindObservers()
    }

    private fun bindObservers() {


        leaderboardViewmodel.leaderBoardLiveData.observe(viewLifecycleOwner, Observer {result ->

            when(result){
                is NetworkResult.Success -> {
                    if(result.data!!.status){

                        for (i in result.data.data.host_daily.top3){
                            if(i.position == 1){
                                binding.top1name.text = i.first_name
                                binding.coin1.text = i.giftcoin.toString()
                                Glide.with(requireActivity())
                                    .load(i.profile_pic)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(R.drawable.profile)
                                    .error(R.drawable.profile)
                                    .into(binding.top1img)
                            }else if(i.position == 2){
                                binding.top2name.text = i.first_name
                                binding.coin2.text = i.giftcoin.toString()
                                Glide.with(requireActivity())
                                    .load(i.profile_pic)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(R.drawable.profile)
                                    .error(R.drawable.profile)
                                    .into(binding.top2img)
                            }else if(i.position == 3){
                                binding.top3name.text = i.first_name
                                binding.coin3.text = i.giftcoin.toString()
                                Glide.with(requireActivity())
                                    .load(i.profile_pic)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(R.drawable.profile)
                                    .error(R.drawable.profile)
                                    .into(binding.top3img)
                            }
                        }

                        println("Check_response: ${result.data.data.host_daily.all}")
                        val allResult = result.data.data.host_daily.all
                        adapter.submitList(allResult)

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