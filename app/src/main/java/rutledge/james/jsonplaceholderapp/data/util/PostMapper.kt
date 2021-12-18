package rutledge.james.jsonplaceholderapp.data.util

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import rutledge.james.jsonplaceholderapp.data.model.Post
import rutledge.james.jsonplaceholderapp.util.Tools.TAG

object PostMapper {
    /** Turn a JSON object into Post object.
     * Returns null if the object is invalid
     */
    fun JSONToPost(JSONPost: JSONObject): Post? {
        return try {
            Post(
                JSONPost["userId"] as Int,
                JSONPost["id"] as Int,
                JSONPost["title"] as String,
                JSONPost["body"] as String
            )
        } catch (JSONException: JSONException) {
            Log.d(TAG, "JSONToPost: ${JSONException.message}, not a valid post JSON object")
            null
        }
    }

    /**
     * Convert a JSONArray into a list of post objects.
     * Will only include valid post objects.
     */
    fun JSONToPosts(JSONPosts: JSONArray): List<Post> {
        val posts = mutableListOf<Post>()

        for (i in 0 until JSONPosts.length()) {
            val post = JSONToPost(
                JSONPosts[i] as JSONObject
            )

            // If the post is valid, add it to the posts list
            post?.let {
                posts.add(it)
            }
        }

        return posts
    }
}