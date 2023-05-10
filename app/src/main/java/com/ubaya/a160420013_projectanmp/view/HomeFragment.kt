package com.ubaya.a160420013_projectanmp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
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

        var sharedFile = "com.ubaya.a160420013_projectanmp"
        var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var user_id: String? = shared.getString("user_id", "")

        if(user_id == ""){
            val action = HomeFragmentDirections.actionLogin()
            Navigation.findNavController(view).navigate(action)
        } else {
            viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
            viewModel.refresh()

            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            userViewModel.refresh(this.requireActivity(), user_id)

            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
            recView?.layoutManager = LinearLayoutManager(context)
            recView?.adapter = booksListAdapter
            observeViewModel()
            observeUserViewModel(view)
        }
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
            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
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
            val txtUserName = view.findViewById<TextView>(R.id.txtNameReivewer)
            val txtUserID = view.findViewById<TextView>(R.id.txtUserIDHome)
            val imgProfile = view.findViewById<ImageView>(R.id.imgProfileHome)
            val progressBarProfile = view.findViewById<ProgressBar>(R.id.progressBarProfile)

            txtUserName.text = userViewModel.userData.value?.name
            txtUserID.text = userViewModel.userData.value?.id
            imgProfile.loadImage(userViewModel.userData.value?.image_url, progressBarProfile)
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