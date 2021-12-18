package rutledge.james.jsonplaceholderapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.network.NetworkRequestQueue
import rutledge.james.jsonplaceholderapp.data.util.PostMapper.JSONToPosts
import rutledge.james.jsonplaceholderapp.util.Tools.TAG

class PostsRepositoryImpl(val NetworkRequestQueue: NetworkRequestQueue) : PostsRepository {
    override val postLiveData: LiveData<List<Post>>
        get() = postMutableLiveData

    private val postMutableLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    override fun getAllPosts() {
        val URL = "https://jsonplaceholder.typicode.com/posts"

        NetworkRequestQueue.addJSONArrayGetRequest(
            URL,
            {
                Log.d(TAG, "getPosts: Received JSON Array of ${it.length()} posts")
                postMutableLiveData.postValue(JSONToPosts(it))
            },
            {
                Log.d(TAG, "getPosts: ${it.message}")
            }
        )
    }
}