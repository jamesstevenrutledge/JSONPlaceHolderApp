package rutledge.james.jsonplaceholderapp.UI.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rutledge.james.jsonplaceholderapp.data.model.Comment
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.repository.PostsRepository

class DetailedPostViewModel(private val postsRepository: PostsRepository) : ViewModel() {
    // Currently selected post data, null if does not match selectedPostId
    val observablePost: LiveData<Post?>
        get() = mutablePostLiveData

    val observablePostComments: LiveData<List<Comment>>
        get() = mutablePostCommentLiveData

    private val mutablePostLiveData: MutableLiveData<Post?> = MutableLiveData()
    private val mutablePostCommentLiveData: MutableLiveData<List<Comment>> =
        MutableLiveData(emptyList())

    private var selectedPostId: Int? = null

    fun observePostLiveData(lifecycleOwner: LifecycleOwner) {
        postsRepository.postLiveData.observe(lifecycleOwner) { post ->
            // Make sure post is the correct post
            if (post.postId == selectedPostId) {
                mutablePostLiveData.postValue(post)
            }
        }
    }

    fun observeCommentLiveData(lifecycleOwner: LifecycleOwner) {
        postsRepository.postCommentsLiveData.observe(lifecycleOwner) { postComments ->
            // Empty list if there are no post comments with selectedPostId
            mutablePostCommentLiveData.postValue(
                postComments.filter { it.postId == selectedPostId }
            )
        }
    }

    // Call set post when you want to change what post is going to be displayed.
    // The data will remain null until a post with the correct id is observed.
    fun setPost(postId: Int) {
        // We do not want to fetch everything again if postId is the same
        if (selectedPostId != postId) {
            selectedPostId = postId
            mutablePostLiveData.postValue(null)
            mutablePostCommentLiveData.postValue(emptyList())
            postsRepository.setSelectedPost(postId)
        }
    }
}