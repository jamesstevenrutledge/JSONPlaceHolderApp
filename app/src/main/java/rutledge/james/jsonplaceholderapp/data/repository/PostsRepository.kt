package rutledge.james.jsonplaceholderapp.data.repository

import androidx.lifecycle.LiveData
import rutledge.james.jsonplaceholderapp.data.model.Comment
import rutledge.james.jsonplaceholderapp.data.model.Post

interface PostsRepository {
    // Observe this to see a list of posts
    val postsLiveData: LiveData<List<Post>>

    // Observe this to see the last selected post
    val postLiveData: LiveData<Post>

    // Observe this to see the comments of the selected post
    val postCommentsLiveData: LiveData<List<Comment>>

    // Will update postsLiveData with latest posts
    fun getAllPosts()

    // Will update postLiveData with data for the post and comments related to postId
    fun setSelectedPost(postId: Int)
}