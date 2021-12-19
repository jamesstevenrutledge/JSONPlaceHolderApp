package rutledge.james.jsonplaceholderapp.UI.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.*
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.repository.FakePostsRepository
import rutledge.james.jsonplaceholderapp.tools.ManualLifecycleOwner

class PostsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val lifecycleOwner = ManualLifecycleOwner()
    private lateinit var fakePostsRepository: FakePostsRepository
    private lateinit var postsViewModel: PostsViewModel

    @Before
    fun setup() {
        lifecycleOwner.startListening()
        fakePostsRepository = FakePostsRepository()
        postsViewModel = PostsViewModel(fakePostsRepository)
    }

    @Test
    fun `Test changing observable posts with refreshPosts()`() {
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

        var before: List<Post>? = null
        var after: List<Post>? = null

        // When
        postsViewModel.observePostsLiveData(lifecycleOwner)
        fakePostsRepository.setPostsToGet(posts)

        postsViewModel.observablePosts.value?.let {
            before = it
        }

        postsViewModel.refreshPosts()

        postsViewModel.observablePosts.value?.let {
            after = it
        }

        // Then
        // The list should start empty
        Assert.assertEquals(before, emptyList<Post>())

        // And finish populated correctly
        Assert.assertEquals(after, posts)
    }

    @After
    fun tearDown() {
        lifecycleOwner.destroy()
    }
}