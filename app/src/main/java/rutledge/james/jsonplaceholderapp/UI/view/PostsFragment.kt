package rutledge.james.jsonplaceholderapp.UI.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rutledge.james.jsonplaceholderapp.databinding.FragmentPostsViewBinding

/**
 * Posts are shown in a scrollable view
 * This fragment is the first screen you see
 */
class PostsFragment : Fragment() {
    private lateinit var bnd: FragmentPostsViewBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        bnd = FragmentPostsViewBinding.inflate(inflater, container, false)

        // Recyclerview setup for displaying posts
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
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
        }

        return bnd.root
    }
}