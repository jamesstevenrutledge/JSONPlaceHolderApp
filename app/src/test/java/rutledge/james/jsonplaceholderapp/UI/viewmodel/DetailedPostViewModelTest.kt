package rutledge.james.jsonplaceholderapp.UI.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.*
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.repository.FakePostsRepository
import rutledge.james.jsonplaceholderapp.tools.ManualLifecycleOwner

class DetailedPostViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val lifecycleOwner = ManualLifecycleOwner()
    private lateinit var fakePostsRepository: FakePostsRepository
    private lateinit var detailedPostViewModel: DetailedPostViewModel

    @Before
    fun setup() {
        lifecycleOwner.startListening()
        fakePostsRepository = FakePostsRepository()
        detailedPostViewModel = DetailedPostViewModel(fakePostsRepository)
    }

    @Test
    fun `Test changing observable post with setPost()`() {
        // Given
        val posts = mutableListOf<Post>()
        posts.add(
            Post(11, 12, "Title 1", "Body 1")
        )
        posts.add(
            Post(21, 22, "Title 2", "Body 2")
        )
        posts.add(
            Post(31, 32, "Title 3", "Body 3")
        )

        var before: Post? = null
        var after: Post? = null

        // When
        detailedPostViewModel.observePostLiveData(lifecycleOwner)
        fakePostsRepository.setPostsToGet(posts)

        detailedPostViewModel.observablePost.value?.let {
            before = it
        }

        detailedPostViewModel.setPost(22)

        detailedPostViewModel.observablePost.value?.let {
            after = it
        }

        // Then
        // The post should remain null until it is changed with setPost
        Assert.assertNull(before)

        Assert.assertEquals(after, posts[1])
    }

    @Test
    fun `Test changing observable post with setPost, with invalid postId`() {
        // Given
        val posts = mutableListOf<Post>()
        posts.add(
            Post(11, 12, "Title 1", "Body 1")
        )
        posts.add(
            Post(21, 22, "Title 2", "Body 2")
        )
        posts.add(
            Post(31, 32, "Title 3", "Body 3")
        )

        var before: Post? = null
        var after: Post? = null

        // When
        detailedPostViewModel.observePostLiveData(lifecycleOwner)
        fakePostsRepository.setPostsToGet(posts)

        detailedPostViewModel.observablePost.value?.let {
            before = it
        }

        // 42 is an invalid postId
        detailedPostViewModel.setPost(42)

        detailedPostViewModel.observablePost.value?.let {
            after = it
        }

        // Then
        // The post should remain null until it is changed with setPost
        Assert.assertNull(before)

        // And finish
        Assert.assertNull(after)
    }

    @After
    fun tearDown() {
        lifecycleOwner.destroy()
    }
}