package rutledge.james.jsonplaceholderapp.UI.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.data.repository.PostsRepository

class PostsViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {
    val observablePosts: LiveData<List<Post>>
        get() = mutablePostsLiveData

    private val mutablePostsLiveData: MutableLiveData<List<Post>> = MutableLiveData()

    init {
        postsRepository.postLiveData.observeForever {
            mutablePostsLiveData.postValue(it)
        }

        refreshPosts()
    }

    // Call refreshPosts() if you want to get any updates to the posts list
    fun refreshPosts() {
        postsRepository.getAllPosts()
    }
}