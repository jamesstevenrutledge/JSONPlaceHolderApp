package rutledge.james.jsonplaceholderapp.UI.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import rutledge.james.jsonplaceholderapp.UI.view.viewholder.PostViewHolder
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.databinding.PostListItemBinding

/**
 * List adapter for showing posts in a recyclerview
 *
 */
class PostListAdapter : ListAdapter<Post, PostViewHolder>(PostDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bnd = PostListItemBinding.inflate(inflater, parent, false)

        return PostViewHolder(bnd.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}