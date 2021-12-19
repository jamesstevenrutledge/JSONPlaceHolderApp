package rutledge.james.jsonplaceholderapp.UI.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.repository.PostsRepository

class DetailedPostViewModel(private val postsRepository: PostsRepository) : ViewModel() {
    // Currently selected post data, null if does not match selectedPostId
    val observablePost: LiveData<Post?>
        get() = mutablePostLiveData

    private val mutablePostLiveData: MutableLiveData<Post?> = MutableLiveData()

    private var selectedPostId: Int? = null

    fun observePostLiveData(lifecycleOwner: LifecycleOwner) {
        postsRepository.postLiveData.observe(lifecycleOwner) { post ->
            // Make sure post is the correct post
            if (post.postId == selectedPostId) {
                mutablePostLiveData.postValue(post)
            }
        }
    }

    // Call set post when you want to change what post is going to be displayed.
    // It will nullify the live data
    // The data will remain null until a post with the correct id is observed.
    fun setPost(postId: Int) {
        selectedPostId = postId
        mutablePostLiveData.postValue(null)
        postsRepository.setSelectedPost(postId)
    }
}