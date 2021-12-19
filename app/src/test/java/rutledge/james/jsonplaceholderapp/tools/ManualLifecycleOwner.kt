package rutledge.james.jsonplaceholderapp.tools

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class ManualLifecycleOwner : LifecycleOwner {
    private var lifecycleRegistry = LifecycleRegistry(this)

    fun startListening() = lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)

    fun destroy() = lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}