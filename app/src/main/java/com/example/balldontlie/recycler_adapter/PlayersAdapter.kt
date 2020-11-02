package com.example.balldontlie.recycler_adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.balldontlie.R
import com.example.balldontlie.database.DatabasePlayers
import com.example.balldontlie.databinding.RowPlayersBinding
import com.example.balldontlie.domain.Players


class PlayersAdapter : RecyclerView.Adapter<PlayersViewHolder>() {
    // private var viewModelAdapter: JokesAdapter? = null

    var results: List<Players> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val withDataBinding: RowPlayersBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                PlayersViewHolder.LAYOUT,
                parent,
                false
        )
        return PlayersViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.results = results[position]
        }
    }

    override fun getItemCount() = results.size
}

class PlayersViewHolder(val viewDataBinding: RowPlayersBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_players
    }
}
