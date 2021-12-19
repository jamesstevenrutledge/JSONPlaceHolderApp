package rutledge.james.jsonplaceholderapp.util

object Tools {
    // A way of logging the class name simply by using TAG
    val Any.TAG: String
        get() {
            val className = javaClass.simpleName
            return className.take(23)
        }
}