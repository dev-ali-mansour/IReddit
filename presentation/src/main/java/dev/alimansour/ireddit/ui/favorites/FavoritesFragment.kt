package dev.alimansour.ireddit.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dev.alimansour.domain.util.Resource
import dev.alimansour.ireddit.MyApplication
import dev.alimansour.ireddit.databinding.FragmentFavoritesBinding
import dev.alimansour.ireddit.ui.MainActivity
import dev.alimansour.ireddit.util.navigateToPost
import timber.log.Timber
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var favoritesViewModel: FavoritesViewModel

    @Inject
    lateinit var favoritesAdapter: FavoritesAdapter

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        (requireActivity() as MainActivity).isNavViewVisible = true

        favoritesAdapter.setOnItemClickListener { post ->
            requireContext().navigateToPost(post)
        }

        initRecyclerView()
        viewFavorites()

        favoritesViewModel.getFavorites()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.favoritesRecyclerView.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun viewFavorites() {
        binding.apply {
            favoritesViewModel.favorites.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        resource.data?.let { favorites ->
                            favoritesAdapter.differ.submitList(favorites)
                        }
                    }
                    is Resource.Error -> {
                        val message = "Failed to Load favorites: ${resource.message}"
                        Timber.d(message)
                        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}