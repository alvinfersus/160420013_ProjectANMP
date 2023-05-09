package com.ubaya.a160420013_projectanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.util.loadImage

class ReadMoreFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var book_id:String = "0"
        var book_name:String = "0"
        var writer:String = "0"
        var category:String = "0"
        var rate:String = "0"
        var synopsis:String = "0"
        var imageUrl:String = "0"

        arguments?.let {
            book_id = ReadMoreFragmentArgs.fromBundle(requireArguments()).bookId
            book_name = ReadMoreFragmentArgs.fromBundle(requireArguments()).bookName
            writer = ReadMoreFragmentArgs.fromBundle(requireArguments()).writer
            rate = ReadMoreFragmentArgs.fromBundle(requireArguments()).rate
            category = ReadMoreFragmentArgs.fromBundle(requireArguments()).category
            synopsis = ReadMoreFragmentArgs.fromBundle(requireArguments()).synopsis
            imageUrl = ReadMoreFragmentArgs.fromBundle(requireArguments()).imageUrl

            view.findViewById<TextView>(R.id.txtBookName).text = book_name
            view.findViewById<TextView>(R.id.txtWriter).text = writer
            view.findViewById<TextView>(R.id.txtCategory).text = category
            view.findViewById<TextView>(R.id.txtDetailRate).text = "Rate : " + rate
            view.findViewById<TextView>(R.id.txtSynopsis).text = synopsis

            var progressBar = view.findViewById<ProgressBar>(R.id.progressBarBookImg)
            view.findViewById<ImageView>(R.id.imgBook).loadImage(imageUrl, progressBar)
        }
    }
}