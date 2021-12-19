package rutledge.james.jsonplaceholderapp.UI.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import rutledge.james.jsonplaceholderapp.UI.view.viewholder.CommentViewHolder
import rutledge.james.jsonplaceholderapp.data.model.Comment
import rutledge.james.jsonplaceholderapp.databinding.CommentListItemBinding

/**
 * List adapter for showing comments in a recyclerview
 *
 */
class CommentListAdapter : ListAdapter<Comment, CommentViewHolder>(CommentDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bnd = CommentListItemBinding.inflate(inflater, parent, false)

        return CommentViewHolder(bnd.root)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object CommentDiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.commentId == newItem.commentId
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }
}