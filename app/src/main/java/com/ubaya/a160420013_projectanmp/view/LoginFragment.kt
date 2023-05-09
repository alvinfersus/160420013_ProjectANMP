package com.ubaya.a160420013_projectanmp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var userViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity?)?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.GONE
        var btnLogin = view?.findViewById<Button>(R.id.btnLogin)
        btnLogin?.setOnClickListener {
            var user_id:String = view.findViewById<TextInputLayout>(R.id.textInputLayoutUserID).editText?.text.toString()
            var password:String = view.findViewById<TextInputLayout>(R.id.textInputLayoutPassword).editText?.text.toString()
            if(user_id != "" && user_id != null && password != "" && password != null){
                userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                userViewModel.refresh(this.requireActivity(), user_id, password)
                observeUserViewModel(view, user_id)
            } else {
                val toast = Toast.makeText(context, "User ID / Password Should be fulfilled"
                    , Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }

    fun observeUserViewModel(view:View, user_id:String){
        userViewModel.userData.observe(viewLifecycleOwner, Observer{
            (activity as MainActivity?)?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.VISIBLE
            var sharedFile = "com.ubaya.a160420013_projectanmp"
            var shared: SharedPreferences? = this.requireActivity().getSharedPreferences(sharedFile,
                Context.MODE_PRIVATE )
            var editor: SharedPreferences.Editor? = shared?.edit()
            editor?.putString("user_id",user_id)
            editor?.commit()

            val action = LoginFragmentDirections.actionHome()
            Navigation.findNavController(view).navigate(action)
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
//            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarProfile)
//
//            if(it == true) {
//                progressLoad?.visibility = View.VISIBLE
//            } else {
//                progressLoad?.visibility = View.GONE
//            }
        })
    }


}