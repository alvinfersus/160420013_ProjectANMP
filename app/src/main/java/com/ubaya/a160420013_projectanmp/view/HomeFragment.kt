package com.ubaya.a160420013_projectanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.util.loadImage
import com.ubaya.a160420013_projectanmp.viewmodel.BookListViewModel
import com.ubaya.a160420013_projectanmp.viewmodel.UserViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel:BookListViewModel
    private lateinit var userViewModel:UserViewModel
    private val booksListAdapter = BookListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        viewModel.refresh()

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.refresh()

        val recView = view?.findViewById<RecyclerView>(R.id.recViewHistory)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = booksListAdapter
        observeViewModel()
        observeUserViewModel(view)
    }

    fun observeViewModel(){
        viewModel.booksLD.observe(viewLifecycleOwner, Observer{
            booksListAdapter.updateBookList(it)
        })

        viewModel.booksLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorLD = view?.findViewById<TextView>(R.id.txtErrorHome)

            if(it == true) {
                txtErrorLD?.visibility = View.VISIBLE
            } else {
                txtErrorLD?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            val recView = view?.findViewById<RecyclerView>(R.id.recViewHistory)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarHome)

            if(it == true) {
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            } else {
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }

    fun observeUserViewModel(view:View){
        userViewModel.userData.observe(viewLifecycleOwner, Observer{
            val txtUserName = view.findViewById<TextView>(R.id.txtNameHome)
            val txtUserID = view.findViewById<TextView>(R.id.txtUserIDHome)
            val imgProfile = view.findViewById<ImageView>(R.id.imgProfileHome)
            val progressBarProfile = view.findViewById<ProgressBar>(R.id.progressBarProfile)

            txtUserName.text = it.name
            txtUserID.text = it.id
            imgProfile.loadImage(it.image_url, progressBarProfile)
        })

        userViewModel.UserLoadError.observe(viewLifecycleOwner, Observer{
//            val txtErrorLD = view?.findViewById<TextView>(R.id.txtErrorHome)
//
//            if(it == true) {
//                txtErrorLD?.visibility = View.VISIBLE
//            } else {
//                txtErrorLD?.visibility = View.GONE
//            }
        })

        userViewModel.loadingUser.observe(viewLifecycleOwner, Observer{
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarProfile)

            if(it == true) {
                progressLoad?.visibility = View.VISIBLE
            } else {
                progressLoad?.visibility = View.GONE
            }
        })
    }
}