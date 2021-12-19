package rutledge.james.jsonplaceholderapp.data.repository

import androidx.lifecycle.LiveData
import rutledge.james.jsonplaceholderapp.data.model.Post

interface PostsRepository {
    // Observe this to see a list of posts
    val postsLiveData: LiveData<List<Post>>

    // Observe this to see the last selected post
    val postLiveData: LiveData<Post>

    // Will update postsLiveData with latest posts
    fun getAllPosts()

    // Will update postLiveData with data for the post related to postId
    fun setSelectedPost(postId: Int)
}