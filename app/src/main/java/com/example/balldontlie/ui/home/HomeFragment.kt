package com.example.balldontlie.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.balldontlie.R
import com.example.balldontlie.database.DatabasePlayers
import com.example.balldontlie.database.PlayersDatabase
import com.example.balldontlie.databinding.FragmentHomeBinding
import com.example.balldontlie.domain.Players
import com.example.balldontlie.recycler_adapter.PlayersAdapter
import com.example.balldontlie.viewmodel.PlayersListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

const val TAG = "HomeFragment"
class HomeFragment : Fragment() {

    private var viewModelAdapter: PlayersAdapter? = null
    private val viewModel by viewModel<PlayersListViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewmodel = viewModel
        viewModelAdapter = PlayersAdapter()

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserverToGetData()
    }

    fun setUpObserverToGetData() {
        viewModel.playersListResults.observe(viewLifecycleOwner,
            Observer<List<Players>> { players ->
                players?.apply {
                    Log.i(TAG, "setUpObserverToGetData: ${players.size}")
                    viewModelAdapter?.results = players

                }
            })
    }

}

