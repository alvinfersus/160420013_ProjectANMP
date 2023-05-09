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
import com.ubaya.a160420013_projectanmp.viewmodel.ReviewListModel

class ReviewFragment : Fragment() {
    private lateinit var viewModel: ReviewListModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReviewListModel::class.java)
        var book_id:String = "0"
        arguments?.let {
            book_id = ReviewFragmentArgs.fromBundle(requireArguments()).bookId
        }
        viewModel.refresh(book_id)

        val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = reviewListAdapter
        observeViewModel()
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout?.setOnRefreshListener {
            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
            recView?.visibility = View.GONE

            val txtError = view?.findViewById<TextView>(R.id.txtErrorBooks)
            txtError?.visibility = View.GONE

            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarBooks)
            progressLoad?.visibility = View.VISIBLE

            viewModel.refresh(book_id)
            refreshLayout?.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer{
            reviewListAdapter.updateReviewList(it)
        })

        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorLD = view?.findViewById<TextView>(R.id.txtErrorReviews)

            if(it == true) {
                txtErrorLD?.visibility = View.VISIBLE
            } else {
                txtErrorLD?.visibility = View.GONE
            }
        })

        viewModel.loadingReviewLD.observe(viewLifecycleOwner, Observer{
            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarReviews)

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