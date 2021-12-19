package rutledge.james.jsonplaceholderapp.data.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

/**
 * A network request queue that can be used as a singleton
 *
 * @param context
 */
class NetworkRequestQueue(context: Context) {
    private val queue = Volley.newRequestQueue(context.applicationContext)

    /**
     * Add a GET request for a JSON Array to the queue
     *
     * @param URL Address to send get request to.
     * @param successCallback Callback for when a JSONArray is returned successfully
     * @param errorCallback Callback when get request fails
     */
    fun addJSONArrayGetRequest(
        URL: String,
        successCallback: (JSONArray) -> Unit,
        errorCallback: (VolleyError) -> Unit
    ) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            URL,
            null,
            { successCallback(it) },
            { errorCallback(it) }
        )

        queue.add(request)
    }

    /**
     * Add a GET request for a JSON Object to the queue
     *
     * @param URL Address to send get request to.
     * @param successCallback Callback for when a JSONArray is returned successfully
     * @param errorCallback Callback when get request fails
     */
    fun addJSONObjectGetRequest(
        URL: String,
        successCallback: (JSONObject) -> Unit,
        errorCallback: (VolleyError) -> Unit
    ) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            URL,
            null,
            { successCallback(it) },
            { errorCallback(it) }
        )

        queue.add(request)
    }
}