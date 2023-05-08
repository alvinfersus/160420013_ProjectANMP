package com.ubaya.a160420013_projectanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
        viewModel = ViewModelProvider(this).get(HistoryListViewModel::class.java)
        viewModel.refresh()

        val recView = view?.findViewById<RecyclerView>(R.id.recViewHistory)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = historyListAdapter
        observeViewModel()
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout?.setOnRefreshListener {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewHistory)
            recView?.visibility = View.GONE

            val txtError = view?.findViewById<TextView>(R.id.txtErrorHistory)
            txtError?.visibility = View.GONE

            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarHistory)
            progressLoad?.visibility = View.VISIBLE

            viewModel.refresh()
            refreshLayout?.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.historyLD.observe(viewLifecycleOwner, Observer{
            historyListAdapter.updateBookList(it)
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
            val recView = view?.findViewById<RecyclerView>(R.id.recViewHistory)
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