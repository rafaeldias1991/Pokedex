package br.com.rafaeldias.apipokedex.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rafaeldias.apipokedex.R

class DatailsFragment : Fragment() {

    companion object {
        fun newInstance() = DatailsFragment()
    }

    private lateinit var viewModel: DatailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.datails_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}