package rutledge.james.jsonplaceholderapp.UI.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.*
import rutledge.james.jsonplaceholderapp.data.model.Comment
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

    @Test
    fun `Test getting comments`() {
        // Given
        val comments = mutableListOf<Comment>()
        comments.add(
            Comment(11, 12, "Name 1", "Email 1", "Body 1")
        )
        comments.add(
            Comment(11, 22, "Name 2", "Email 2", "Body 2")
        )
        comments.add(
            Comment(11, 32, "Name 3", "Email 3", "Body 3")
        )

        var before: List<Comment>? = null
        var after: List<Comment>? = null

        // When
        detailedPostViewModel.observeCommentLiveData(lifecycleOwner)
        fakePostsRepository.setCommentsToGet(comments)

        detailedPostViewModel.observablePostComments.value?.let {
            before = it
        }

        detailedPostViewModel.setPost(11)

        detailedPostViewModel.observablePostComments.value?.let {
            after = it
        }

        // Then
        // The list should start empty
        Assert.assertEquals(emptyList<Post>(), before)

        // And finish populated correctly
        Assert.assertEquals(comments, after)
    }

    @Test
    fun `Test getting comments when comments are for a different post`() {
        // Given
        val comments = mutableListOf<Comment>()
        comments.add(
            Comment(22, 12, "Name 1", "Email 1", "Body 1")
        )
        comments.add(
            Comment(23, 22, "Name 2", "Email 2", "Body 2")
        )
        comments.add(
            Comment(43, 32, "Name 3", "Email 3", "Body 3")
        )

        var before: List<Comment>? = null
        var after: List<Comment>? = null

        // When
        detailedPostViewModel.observeCommentLiveData(lifecycleOwner)
        fakePostsRepository.setCommentsToGet(comments)

        detailedPostViewModel.observablePostComments.value?.let {
            before = it
        }

        detailedPostViewModel.setPost(11)

        detailedPostViewModel.observablePostComments.value?.let {
            after = it
        }

        // Then
        // The list should start empty
        Assert.assertEquals(emptyList<Post>(), before)

        // And finish empty
        Assert.assertEquals(emptyList<Post>(), after)
    }

    @After
    fun tearDown() {
        lifecycleOwner.destroy()
    }
}