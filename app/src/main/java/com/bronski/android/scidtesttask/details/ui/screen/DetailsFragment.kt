package com.bronski.android.scidtesttask.details.ui.screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bronski.android.scidtesttask.core.ui.BaseFragment
import com.bronski.android.scidtesttask.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataItem()
    }

    private fun initDataItem() {
        args.dataItem?.let {
            with(binding) {
                setTittleName(it.name)
                nameTextView.text = it.name
                dateTextView.text = it.date
                descriptionTextView.text = it.description
            }
        }
    }

    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)

}