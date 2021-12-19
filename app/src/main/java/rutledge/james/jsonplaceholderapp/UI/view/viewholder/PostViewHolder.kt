package rutledge.james.jsonplaceholderapp.UI.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.databinding.PostListItemBinding

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var bnd: PostListItemBinding
    fun onBind(post: Post, onClick: () -> Unit) {
        bnd = PostListItemBinding.bind(itemView).apply {
            txtTitle.text = post.title
            txtDescription.text = post.body

            // onClick Could be assigned to any element if design changes
            constraintContainer.setOnClickListener {
                onClick()
            }
        }
    }
}