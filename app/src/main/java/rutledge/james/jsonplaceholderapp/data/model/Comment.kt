package rutledge.james.jsonplaceholderapp.data.model

data class Comment(
    val postId: Int,
    val commentId: Int,
    val name: String,
    val email: String,
    val body: String
)