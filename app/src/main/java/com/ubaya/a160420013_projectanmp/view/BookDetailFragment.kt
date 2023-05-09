package com.ubaya.a160420013_projectanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160420013_projectanmp.R
import com.ubaya.a160420013_projectanmp.util.loadImage
import com.ubaya.a160420013_projectanmp.viewmodel.DetailBookViewModel

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: DetailBookViewModel
    private var book_id:String = "0"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        arguments?.let {
            book_id = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId.toString()
        }
        viewModel.fetch(book_id)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.booksLD.observe(viewLifecycleOwner, Observer{
            val txtBookName = view?.findViewById<TextView>(R.id.txtBookName)
            val txtWriter = view?.findViewById<TextView>(R.id.txtWriter)
            val txtCategory = view?.findViewById<TextView>(R.id.txtCategory)
            val txtRate = view?.findViewById<TextView>(R.id.txtDetailRate)
            val txtSynopsis = view?.findViewById<TextView>(R.id.txtSynopsis)
            val imgBook = view?.findViewById<ImageView>(R.id.imgBook)
            val progressBarBookImg = view?.findViewById<ProgressBar>(R.id.progressBarBookImg)
            val btnReadMore = view?.findViewById<Button>(R.id.btnReadMore)

            val txtCategoriesAns = view?.findViewById<TextView>(R.id.txtCategoriesAns)
            val txtBookIDAns = view?.findViewById<TextView>(R.id.txtBookIDAns)
            val txtEdiAns = view?.findViewById<TextView>(R.id.txtEdiAns)
            val txtLangAns = view?.findViewById<TextView>(R.id.txtLangAns)
            val txtPagesAns = view?.findViewById<TextView>(R.id.txtPagesAns)
            val txtPubAns = view?.findViewById<TextView>(R.id.txtPubAns)

            txtBookName?.text = viewModel.booksLD.value?.book_name
            txtWriter?.text = viewModel.booksLD.value?.writer
            txtCategory?.text = viewModel.booksLD.value?.category
            txtRate?.text = "Rate : " + viewModel.booksLD.value?.rate
            val fullSynopsis:String? = it?.synopsis
            if(it.synopsis!=null){
                if(it.synopsis.length > 245){
                    txtSynopsis?.text = it.synopsis?.substring(0,245) + "..."
                }
            }
            txtCategoriesAns?.text = viewModel.booksLD.value?.category
            txtBookIDAns?.text = viewModel.booksLD.value?.id
            txtEdiAns?.text = viewModel.booksLD.value?.edition
            txtLangAns?.text = viewModel.booksLD.value?.languages
            txtPagesAns?.text = viewModel.booksLD.value?.pages + " Pages"
            txtPubAns?.text = viewModel.booksLD.value?.publisher
            var imageurl:String? = viewModel.booksLD.value?.image_url

            if(progressBarBookImg != null){
                imgBook?.loadImage(viewModel.booksLD.value?.image_url, progressBarBookImg)
            }

            btnReadMore?.setOnClickListener {
                val action = BookDetailFragmentDirections.actionReadMore(
                    txtBookName?.text.toString(),
                    txtWriter?.text.toString(),
                    txtCategory?.text.toString(),
                    txtRate?.text.toString(),
                    fullSynopsis!!,
                    book_id,
                    imageurl!!
                )
                Navigation.findNavController(it).navigate(action)
            }
        })
    }
}