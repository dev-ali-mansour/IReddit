package dev.alimansour.ireddit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.alimansour.domain.util.Resource
import dev.alimansour.ireddit.MyApplication
import dev.alimansour.ireddit.R
import dev.alimansour.ireddit.databinding.FragmentHomeBinding
import dev.alimansour.ireddit.ui.MainActivity
import dev.alimansour.ireddit.util.ConnectivityManager
import dev.alimansour.ireddit.util.hideSoftKeyboard
import dev.alimansour.ireddit.util.navigateToPost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var postsAdapter: PostsAdapter

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val limit = 25
    private var after: String? = null
    private var isScrolling = false
    private var isLoading = false
    private var query = ""
    private var isSearching = false
    private var isConnected = false

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = binding.postsRecyclerView.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                after?.let {
                    if (isSearching) homeViewModel.searchForPost(query, limit, it)
                    else homeViewModel.getPosts(limit, it)
                }
                isScrolling = false
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        runCatching {
            (requireActivity().application as MyApplication).appComponent
                .viewModelComponentBuilder()
                .context(requireContext())
                .activity(requireActivity())
                .build()
                .inject(this)
        }.onFailure { it.printStackTrace() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        isConnected = connectivityManager.isConnected()

        (requireActivity() as MainActivity).isNavViewVisible = true
        binding.postsRecyclerView.isVisible = isConnected
        binding.textviewNotConnected.isVisible = !isConnected

        postsAdapter.setOnItemClickListener { post ->
            requireContext().navigateToPost(post)
        }
        postsAdapter.setOnItemFavoriteClickListener { post ->
            homeViewModel.addPostToFavorite(post)
        }

        if (isConnected) {
            initRecyclerView()
            viewPosts()
            setSearchView()
            observeActions()
            homeViewModel.getPosts(limit, "foo")
        }
        return binding.root
    }

    private fun observeActions() {
        binding.apply {
            homeViewModel.action.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        resource.data?.let { message ->
                            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                        }
                    }
                    is Resource.Error -> {
                        val message = "Failed to add the post to favorites: ${resource.message}"
                        Timber.d(message)
                        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun viewPosts() {
        binding.apply {
            homeViewModel.posts.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        resource.data?.let { list ->
                            val posts = postsAdapter.differ.currentList.toMutableList()
                            list.forEach { post ->
                                if (!posts.contains(post)) posts.add(post)
                            }
                            if (posts.isNotEmpty()) {
                                after = posts.last().after
                                postsAdapter.differ.submitList(posts.toList())
                            }
                        }
                    }
                    is Resource.Error -> {
                        val message = "Failed to Load Posts: ${resource.message}"
                        Timber.d(message)
                        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun initRecyclerView() {
        binding.postsRecyclerView.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HomeFragment.onScrollListener)
        }
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0?.let {
                        if (it.isEmpty()) return false
                        lifecycleScope.launch {
                            delay(2000)
                            query = it
                            after = "foo"
                            isSearching = true
                            postsAdapter.differ.submitList(listOf())
                            homeViewModel.searchForPost(query, limit, after!!)
                            (requireActivity() as MainActivity).activityTitle =
                                getString(R.string.title_search_results)
                            requireActivity().hideSoftKeyboard()
                        }
                    }
                    return false
                }
            })

        binding.searchView.setOnCloseListener {
            after = "foo"
            isSearching = false
            postsAdapter.differ.submitList(listOf())
            homeViewModel.getPosts(limit, after!!)
            (requireActivity() as MainActivity).activityTitle = getString(R.string.title_home)
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}