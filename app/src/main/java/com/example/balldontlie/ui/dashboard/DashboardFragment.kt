package com.example.balldontlie.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.balldontlie.R
import com.example.balldontlie.databinding.FragmentDashboardBinding
import com.example.balldontlie.domain.Teams
import com.example.balldontlie.recycler_adapter.TeamsAdapter
import com.example.balldontlie.ui.home.TAG
import com.example.balldontlie.viewmodel.TeamsListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

private const val TAG = "DashboardFragment"

class DashboardFragment : Fragment() {

    private var viewModelAdapter: TeamsAdapter? = null
    private val viewModel by viewModel<TeamsListViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDashboardBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewmodel = viewModel
        viewModelAdapter = TeamsAdapter()

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
        viewModel.teamsListResults.observe(viewLifecycleOwner,
            Observer<List<Teams>> { teams ->
                teams?.apply {
                    Log.i(TAG, "setUpObserverToGetData: ${teams.size}")
                    viewModelAdapter?.results = teams

                }
            })
    }
}