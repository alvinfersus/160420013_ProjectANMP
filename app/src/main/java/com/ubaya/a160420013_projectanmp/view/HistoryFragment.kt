package com.ubaya.a160420013_projectanmp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.viewmodel.HistoryListViewModel

class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryListViewModel
    private val historyListAdapter = HistoryListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedFile = "com.ubaya.a160420013_projectanmp"
        var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var user_id: String? = shared.getString("user_id", "")

        viewModel = ViewModelProvider(this).get(HistoryListViewModel::class.java)
        viewModel.refresh(user_id)

        val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = historyListAdapter
        observeViewModel()
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout?.setOnRefreshListener {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
            recView?.visibility = View.GONE

            val txtError = view?.findViewById<TextView>(R.id.txtErrorHistory)
            txtError?.visibility = View.GONE

            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarHistory)
            progressLoad?.visibility = View.VISIBLE

            viewModel.refresh(user_id)
            refreshLayout?.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.historyLD.observe(viewLifecycleOwner, Observer{
            historyListAdapter.updateBookList(it)
            if(it.isEmpty()){
//                var txtNoHistory = view?.findViewById<TextView>(R.id.txtNoHistory)
                (activity as MainActivity?)?.findViewById<TextView>(R.id.txtNoHistory)?.visibility = View.VISIBLE
            } else {
                (activity as MainActivity?)?.findViewById<TextView>(R.id.txtNoHistory)?.visibility = View.GONE
            }
        })

        viewModel.historyLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorLD = view?.findViewById<TextView>(R.id.txtErrorHistory)

            if(it == true) {
                txtErrorLD?.visibility = View.VISIBLE
            } else {
                txtErrorLD?.visibility = View.GONE
            }
        })

        viewModel.loadingHistoryLD.observe(viewLifecycleOwner, Observer{
            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarHistory)

            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}