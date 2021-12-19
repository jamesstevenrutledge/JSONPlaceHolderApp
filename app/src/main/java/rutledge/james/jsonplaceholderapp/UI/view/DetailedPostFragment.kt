package rutledge.james.jsonplaceholderapp.UI.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import rutledge.james.jsonplaceholderapp.UI.viewmodel.DetailedPostViewModel
import rutledge.james.jsonplaceholderapp.databinding.FragmentDetailedPostViewBinding

class DetailedPostFragment : Fragment() {
    private lateinit var bnd: FragmentDetailedPostViewBinding

    private val detailedPostViewModel: DetailedPostViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.getInt("postId")?.let {
            detailedPostViewModel.setPost(it)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bnd = FragmentDetailedPostViewBinding.inflate(inflater, container, false)

        detailedPostViewModel.observePostLiveData(viewLifecycleOwner)
        registerObservers()

        return bnd.root
    }

    private fun registerObservers() {
        detailedPostViewModel.observablePost.observe(viewLifecycleOwner) {
            it?.let {
                bnd.inclPost.txtTitle.text = it.title
                bnd.inclPost.txtDescription.text = it.body
            } ?: run {
                // Could show loading screen...
                bnd.inclPost.txtTitle.text = ""
                bnd.inclPost.txtDescription.text = ""
            }
        }
    }
}