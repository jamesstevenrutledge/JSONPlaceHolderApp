package rutledge.james.jsonplaceholderapp.UI.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import rutledge.james.jsonplaceholderapp.UI.view.adapter.CommentListAdapter
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

        val commentsAdapter = CommentListAdapter()

        val linearLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        val dividerItemDecoration = DividerItemDecoration(
            context,
            linearLayoutManager.orientation
        )

        bnd.recyclerComments.apply {
            adapter = commentsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
        }

        detailedPostViewModel.observePostLiveData(viewLifecycleOwner)
        detailedPostViewModel.observeCommentLiveData(viewLifecycleOwner)
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

        detailedPostViewModel.observablePostComments.observe(viewLifecycleOwner) { comments ->
            bnd.recyclerComments.adapter.let { adapter ->
                adapter as CommentListAdapter
                adapter.submitList(comments)
            }
        }
    }
}