package com.algostack.leaderboard.view

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.algostack.leaderboard.R
import com.algostack.leaderboard.databinding.ListofItemBinding
import com.algostack.leaderboard.services.model.All
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class LeaderBoardAdapter : ListAdapter<All, LeaderBoardAdapter.LeaderViewHolder> (ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderViewHolder {

      val binding = ListofItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return LeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeaderViewHolder, position: Int) {

          val item = getItem(position)
          holder.bind(item)
    }

    inner class LeaderViewHolder(private val binding: ListofItemBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(item: All) {

            binding.numid.text = item.position.toString()
            binding.name.text = item.first_name
            Glide.with(itemView)
                .load(item.profile_pic)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .into(binding.circleImageView)
            if (item.gender == ""){
                binding.gendar.text = "male"
            }else{
                binding.gendar.text = item.gender
            }
            binding.idea.text = item.level.toString()

          binding.coin.text = item.giftcoin.toString()

        }

    }


    class ComparatorDiffUtil : DiffUtil.ItemCallback<All>() {
        override fun areItemsTheSame(oldItem: All, newItem: All): Boolean {

            return oldItem.position == newItem.position
        }

        override fun areContentsTheSame(oldItem: All, newItem: All): Boolean {
            return oldItem == newItem
        }
    }


}