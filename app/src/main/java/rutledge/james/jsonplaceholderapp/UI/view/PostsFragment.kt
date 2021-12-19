package rutledge.james.jsonplaceholderapp.UI.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import rutledge.james.jsonplaceholderapp.R
import rutledge.james.jsonplaceholderapp.UI.view.adapter.PostListAdapter
import rutledge.james.jsonplaceholderapp.UI.viewmodel.PostsViewModel
import rutledge.james.jsonplaceholderapp.databinding.FragmentPostsViewBinding

/**
 * Posts are shown in a scrollable view
 * This fragment is the first screen you see
 */
class PostsFragment : Fragment() {
    private lateinit var bnd: FragmentPostsViewBinding
    private val postsViewModel: PostsViewModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        bnd = FragmentPostsViewBinding.inflate(inflater, container, false)

        // Recyclerview setup for displaying posts
        val postsAdapter = PostListAdapter {
            val bundle = Bundle()
            bundle.putInt("postId", it.postId)
            findNavController().navigate(R.id.viewPost, bundle)
        }

        val linearLayoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        val dividerItemDecoration = DividerItemDecoration(
            context,
            linearLayoutManager.orientation
        )

        bnd.recyclerPosts.apply {
            adapter = postsAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
        }

        postsViewModel.observePostsLiveData(viewLifecycleOwner)
        registerObservers()

        return bnd.root
    }

    private fun registerObservers() {
        // The recyclerview should show contents of observablePosts
        postsViewModel
            .observablePosts
            .observe(viewLifecycleOwner) { posts ->
                bnd.recyclerPosts.adapter?.let { adapter ->
                    adapter as PostListAdapter
                    adapter.submitList(posts)
                }
            }
    }
}