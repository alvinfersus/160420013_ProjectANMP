package com.ubaya.a160420013_projectanmp.view

import android.content.Context
import android.content.SharedPreferences
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
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.util.loadImage
import com.ubaya.a160420013_projectanmp.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedFile = "com.ubaya.a160420013_projectanmp"
        var shared: SharedPreferences = this.requireActivity().getSharedPreferences(sharedFile,
            Context.MODE_PRIVATE )
        var user_id: String? = shared.getString("user_id", "")

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.refresh(this.requireActivity(), user_id)

        observeViewModel(view)
        val refreshLayout = view?.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
//        refreshLayout?.setOnRefreshListener {
//            val recView = view?.findViewById<RecyclerView>(R.id.recViewHome)
//            recView?.visibility = View.GONE
//
//            val txtError = view?.findViewById<TextView>(R.id.txtErrorBooks)
//            txtError?.visibility = View.GONE
//
//            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarBooks)
//            progressLoad?.visibility = View.VISIBLE
//
//            viewModel.refresh()
//            refreshLayout?.isRefreshing = false
//        }
    }

    fun observeViewModel(view: View){
        viewModel.userData.observe(viewLifecycleOwner, Observer{
            val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
            val progressBarUser = view.findViewById<ProgressBar>(R.id.progressBarUser)
            val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
            val txtUserName = view.findViewById<TextView>(R.id.txtUserName)
            val txtUserID = view.findViewById<TextView>(R.id.txtUserID)
            val txtUserBOD = view.findViewById<TextView>(R.id.txtUserBOD)
            val txtPhone = view.findViewById<TextView>(R.id.txtUserPhone)

            imgProfile.loadImage(it?.image_url, progressBarUser)
            txtEmail.text = it?.email
            txtUserName.text = it?.name
            txtUserID.text = it?.id
            txtUserBOD.text = it?.birth
            txtPhone.text = it?.phone

            txtUserName.isFocusable = false
            txtUserID.isFocusable = false
            txtUserBOD.isFocusable = false
            txtPhone.isFocusable = false
        })

        viewModel.UserLoadError.observe(viewLifecycleOwner, Observer{
            val txtErrorLD = view?.findViewById<TextView>(R.id.txtErrorBooks)

            if(it == true) {
                txtErrorLD?.visibility = View.VISIBLE
            } else {
                txtErrorLD?.visibility = View.GONE
            }
        })

        viewModel.loadingUser.observe(viewLifecycleOwner, Observer{
            val recView = view?.findViewById<RecyclerView>(R.id.recViewReview)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarBooks)

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