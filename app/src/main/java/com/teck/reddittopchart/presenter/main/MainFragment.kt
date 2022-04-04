package com.teck.reddittopchart.presenter.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.teck.reddittopchart.R
import com.teck.reddittopchart.databinding.MainFragmentBinding
import com.teck.reddittopchart.domain.AppState
import com.teck.reddittopchart.domain.models.Data
import com.teck.reddittopchart.presenter.base.BaseFragment
import com.teck.reddittopchart.presenter.main.adapter.PostAdapter
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.getKoin
import org.koin.core.scope.Scope

class MainFragment : BaseFragment<MainFragmentBinding>(R.layout.main_fragment) {
    override val scope: Scope = getKoin().createScope<MainFragment>()
    override val viewBinding: MainFragmentBinding by viewBinding()
    override val viewModel: MainViewModel = scope.get()
    private var adapter: PostAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner
            .lifecycleScope
            .launchWhenCreated {
                viewModel.getMore().collect {
                    adapter = PostAdapter()
                    viewBinding.recycleViewPosts.adapter = adapter
                    adapter?.submitData(it)
                }
            }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                adapter = PostAdapter()
                viewBinding.recycleViewPosts.adapter = adapter
                if (appState.data is Data) {
                    adapter = PostAdapter()
                    viewBinding.recycleViewPosts.adapter = adapter
                }
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}