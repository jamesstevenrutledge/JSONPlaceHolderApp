package rutledge.james.jsonplaceholderapp

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import rutledge.james.jsonplaceholderapp.UI.viewmodel.DetailedPostViewModel
import rutledge.james.jsonplaceholderapp.UI.viewmodel.PostsViewModel
import rutledge.james.jsonplaceholderapp.data.network.NetworkRequestQueue
import rutledge.james.jsonplaceholderapp.data.repository.PostsRepositoryImpl

val JSONPlaceholderKoinModule = module {
    single(createOnStart = true) { NetworkRequestQueue(androidContext()) }
    single(createOnStart = true) { PostsRepositoryImpl(get()) }
    single(createOnStart = true) { PostsViewModel(get<PostsRepositoryImpl>()) }
    single { DetailedPostViewModel(get<PostsRepositoryImpl>()) }
}