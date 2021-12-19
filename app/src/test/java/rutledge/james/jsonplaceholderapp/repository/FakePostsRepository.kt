package rutledge.james.jsonplaceholderapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rutledge.james.jsonplaceholderapp.data.model.Comment
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.repository.PostsRepository

class FakePostsRepository : PostsRepository {
    override val postsLiveData: LiveData<List<Post>>
        get() = postsMutableLiveData

    override val postLiveData: LiveData<Post>
        get() = postMutableLiveData

    override val postCommentsLiveData: LiveData<List<Comment>>
        get() = postCommentsMutableLiveData

    private val postsMutableLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    private val postMutableLiveData: MutableLiveData<Post> = MutableLiveData()
    private val postCommentsMutableLiveData: MutableLiveData<List<Comment>> = MutableLiveData()

    private var mPosts: List<Post> = emptyList()
    private var mComments: List<Comment> = emptyList()

    // Posts will be empty unless you run setPostsToGet
    override fun getAllPosts() {
        postsMutableLiveData.postValue(mPosts)
    }

    // No post will be found without running setPostsToGet first.
    override fun setSelectedPost(postId: Int) {
        mPosts.firstOrNull {
            it.postId == postId
        }?.let {
            postMutableLiveData.postValue(it)
        }

        postCommentsMutableLiveData.postValue(mComments)
    }

    // Use this to set the posts that should be observed after getAllPosts() is run
    fun setPostsToGet(posts: List<Post>) {
        mPosts = posts
    }

    fun setCommentsToGet(comments: List<Comment>) {
        mComments = comments
    }
}