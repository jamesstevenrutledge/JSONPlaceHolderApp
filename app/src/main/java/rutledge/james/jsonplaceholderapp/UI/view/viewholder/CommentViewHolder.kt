package rutledge.james.jsonplaceholderapp.UI.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import rutledge.james.jsonplaceholderapp.data.model.Comment
import rutledge.james.jsonplaceholderapp.databinding.CommentListItemBinding

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var bnd: CommentListItemBinding
    fun onBind(comment: Comment) {
        bnd = CommentListItemBinding.bind(itemView).apply {
            txtBody.text = comment.body
            txtName.text = comment.name
            txtEmail.text = comment.email
        }
    }
}