package dev.alimansour.ireddit.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dev.alimansour.domain.util.Resource
import dev.alimansour.ireddit.MyApplication
import dev.alimansour.ireddit.databinding.FragmentHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        homeViewModel.posts.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Timber.d("Loading Posts!")
                }
                is Resource.Success -> {
                    resource.data?.let { posts ->
                        if (posts.isEmpty()) {
                            Timber.d("No posts found!")
                        } else {
                            posts.forEach { post ->
                                Timber.d("Post: $post")
                            }
                        }
                    }
                }
                is Resource.Error -> {
                    Timber.d("Failed to Load Posts: ${resource.message}")
                }
            }
        }

        lifecycleScope.launch {
            homeViewModel.getPosts(5, "foo")
            delay(10000)
            homeViewModel.searchForPost("exited", 10, "foo")
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}