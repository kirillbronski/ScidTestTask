package com.bronski.android.scidtesttask.core.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bronski.android.scidtesttask.R

abstract class BaseFragment<viewBinding : ViewBinding> : Fragment() {

    private var _binding: viewBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun getViewBinding(): viewBinding

    protected fun setTittleName(name: String) {
        val titleText =
            requireActivity().findViewById<TextView>(R.id.title_text_view)
        titleText.text = name
    }

    protected fun showProgressIndicator(progressBar: ProgressBar) {
        progressBar.isVisible = true
    }

    protected fun hideProgressIndicator(progressBar: ProgressBar) {
        progressBar.isVisible = false
    }
}