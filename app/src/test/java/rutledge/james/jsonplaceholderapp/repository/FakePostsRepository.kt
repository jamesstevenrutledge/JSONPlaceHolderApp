package rutledge.james.jsonplaceholderapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.repository.PostsRepository

class FakePostsRepository : PostsRepository {
    override val postLiveData: LiveData<List<Post>>
        get() = postMutableLiveData

    private val postMutableLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    private var mPosts: List<Post> = emptyList()

    override fun getAllPosts() {
        postMutableLiveData.postValue(mPosts)
    }

    // Use this to set the posts that should be observed after getAllPosts() is run
    fun setPostsToGet(posts: List<Post>) {
        mPosts = posts
    }
}