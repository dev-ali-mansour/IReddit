package dev.alimansour.ireddit.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.alimansour.ireddit.MyApplication
import dev.alimansour.ireddit.databinding.FragmentFavoritesBinding
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var favoritesViewModel: FavoritesViewModel
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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}