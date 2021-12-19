package rutledge.james.jsonplaceholderapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.network.NetworkRequestQueue
import rutledge.james.jsonplaceholderapp.data.util.PostMapper.JSONToPost
import rutledge.james.jsonplaceholderapp.data.util.PostMapper.JSONToPosts
import rutledge.james.jsonplaceholderapp.util.Tools.TAG

class PostsRepositoryImpl(private val NetworkRequestQueue: NetworkRequestQueue) : PostsRepository {
    override val postsLiveData: LiveData<List<Post>>
        get() = postsMutableLiveData

    override val postLiveData: LiveData<Post>
        get() = postMutableLiveData

    private val postsMutableLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    private val postMutableLiveData: MutableLiveData<Post> = MutableLiveData()

    override fun getAllPosts() {
        val URL = "https://jsonplaceholder.typicode.com/posts"

        NetworkRequestQueue.addJSONArrayGetRequest(
            URL,
            {
                Log.d(TAG, "getPosts: Received JSON Array of ${it.length()} posts")
                postsMutableLiveData.postValue(JSONToPosts(it))
            },
            {
                Log.d(TAG, "getPosts: ${it.message}")
            }
        )
    }

    override fun setSelectedPost(postId: Int) {
        postsLiveData.value?.firstOrNull {
            it.postId == postId
        }?.let {
            postMutableLiveData.postValue(it)
        } ?: run {
            getDetailedPostFromNetwork(postId)
        }
    }

    private fun getDetailedPostFromNetwork(postId: Int) {
        val URL = "https://jsonplaceholder.typicode.com/posts/$postId"

        NetworkRequestQueue.addJSONObjectGetRequest(
            URL,
            {
                Log.d(TAG, "getPosts: Received JSON Array of ${it.length()} posts")
                postMutableLiveData.postValue(JSONToPost(it))
            },
            {
                Log.d(TAG, "getPosts: ${it.message}")
            }
        )
    }
}