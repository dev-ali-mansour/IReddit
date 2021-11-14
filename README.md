# IReddit

I used Clean Architecture to structure the application by separating it to 3 layers: Presentation, domain, and data.
Each layer is in a separate module, So the application is modularized.
<br/><br/>
<p align="center">
<img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg" width="500" align="center"><br/>
</p>
<br/>
<p align="center">
<img src="https://i.ibb.co/Q902503/1-RP7405-VH22r-Qx-Zef9f-WHg.png" width="500" align="center"><br/>
</p>

## User can:
* See a list of /r/aww posts
* See more posts when scrolls to the end
* See the image if post contains an image or gif
* See an image with play icon if post contains video
* Add post to favorite by clicking on add to favorites button in each item.
* See a list of favorites
* Remove items from favorites by swiping left or right.
* Clear all favorites by clicking on the clear-all float action button.
* Search for a post
* See more posts when scrolls to the end search results
* navigate to the post on reddit.com within the application by tapping on it.

## Built With

* [Kotlin](https://kotlinlang.org) - As a programming language.
* [RXJava](https://github.com/ReactiveX/RxJava) - For multithreading while handling requests to the server and local database.
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - To handle app navigation.
* [Multidex](https://developer.android.com/studio/build/multidex) - To enable creating multi dex files because of using set of libraries that reached the maximum size of single dex file.
* [Model-View-ViewModel(MVVM)](https://developer.android.com/topic/architecture) - Offers an implementation of observer design pattern.
* [View Binding](https://developer.android.com/topic/libraries/view-binding) - It helps in decoratively binding UI elements of our layout to data source of our app.
* [Retrofit](https://square.github.io/retrofit/) - It is a type-safe REST client for Android, Java and Kotlin developed by Square. The library provides a powerful framework for authenticating and interacting with APIs and sending network requests with OkHttp.
* [Room DB](https://developer.android.com/training/data-storage/room) - To manage SQLite database easily and avoid a lot boilerplate code.
* [Glide](https://github.com/bumptech/glide) - It is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* [Timber](https://github.com/JakeWharton/timber) - It helps in logging while debugging your app. and all logging code will not be embedded in the released APK.
* [Dagger 2](https://dagger.dev/) - It is arguably the most used Dependency Injection, or DI, framework for Android. Many Android projects use Dagger to simplify building and providing dependencies across the app. It gives you the ability to create specific scopes, modules, and components, where each forms a piece of a puzzle: The dependency graph.
* [Clean Architecture](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started) - Applying Clean Architecture and Solid Principles to build a robust, maintainable, and testable application.
