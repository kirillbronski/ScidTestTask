package com.bronski.android.scidtesttask.list.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bronski.android.scidtesttask.R
import com.bronski.android.scidtesttask.core.state.ViewState
import com.bronski.android.scidtesttask.core.ui.BaseFragment
import com.bronski.android.scidtesttask.core.utils.RecyclerItemListener
import com.bronski.android.scidtesttask.databinding.FragmentListBinding
import com.bronski.android.scidtesttask.list.ui.adapter.DataItem
import com.bronski.android.scidtesttask.list.ui.adapter.ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>() {

    private val viewModel by viewModels<ListViewModel>()

    private val recyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(itemData: DataItem) {
            displayDetailsFragment(itemData)
        }
    }

    private val listAdapter = ListAdapter(recyclerItemListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTittleName(getString(R.string.tittle_name))
        setUpAdapter()
        observeList()
        observeViewState()
        swipeToRefreshData()
    }

    private fun observeViewState(){
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect{
                when(it){
                    is ViewState.SuccessState -> {
                        hideProgressIndicator(binding.progressBar)
                    }
                    is ViewState.LoadingState -> {
                        showProgressIndicator(binding.progressBar)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setUpAdapter() = with(binding) {
        recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        }
    }

    private fun observeList() {
        lifecycleScope.launchWhenStarted {
            viewModel.list.collectLatest {
                listAdapter.submitData(it)
            }
        }
    }

    private fun swipeToRefreshData() = with(binding) {
        swipeToRefresh.setOnRefreshListener {
            viewModel.getApiData()
            if (swipeToRefresh.isShown) {
                swipeToRefresh.isRefreshing = false
            }
        }
    }

    override fun getViewBinding() = FragmentListBinding.inflate(layoutInflater)

    private fun displayDetailsFragment(itemResultData: DataItem?) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToDetailsFragment(itemResultData)
        )
    }

}