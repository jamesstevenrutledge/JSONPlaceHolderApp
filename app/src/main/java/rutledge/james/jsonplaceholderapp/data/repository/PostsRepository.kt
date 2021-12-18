package rutledge.james.jsonplaceholderapp.data.repository

import androidx.lifecycle.LiveData
import rutledge.james.jsonplaceholderapp.data.model.Post

interface PostsRepository {
    val postLiveData: LiveData<List<Post>>

    /**
     * Returns a list of posts
     */
    fun getAllPosts()
}