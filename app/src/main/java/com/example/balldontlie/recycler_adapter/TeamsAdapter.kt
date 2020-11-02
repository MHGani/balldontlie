package com.example.balldontlie.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.balldontlie.R
import com.example.balldontlie.databinding.RowTeamsBinding
import com.example.balldontlie.domain.Teams

class TeamsAdapter : RecyclerView.Adapter<TeamsViewHolder>() {
    // private var viewModelAdapter: JokesAdapter? = null

    var results: List<Teams> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val withDataBinding: RowTeamsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TeamsViewHolder.LAYOUT,
            parent,
            false
        )
        return TeamsViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.results1 = results[position]
        }
    }

    override fun getItemCount() = results.size
}

class TeamsViewHolder(val viewDataBinding: RowTeamsBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_teams
    }
}
