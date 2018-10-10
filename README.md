 # Trailers
 -------
 Trailers is an android app that display movies by genre.

 ## User Stories
 The following checked functionality is completed:

 Splash Screen
   [x] Display gradient background, logo
   [x] Redirect main activity when the views are ready to show
   
 Main movie list screen
  [x] Display movies horizontaly for each genre. (Movie poster and title should be shown)
  [x] Allow the user to tap a movie to show movie detail in another Activity.
  [x] Screen is scrollable to show all contents on small screens.
  [x] Screen will grow vertically and dynamically based on size of genres.(Views are not fixed)
  [x] Allow the user to tap Profile button to show users profile.
  [x] Allow the user to refresh movie by swiping down the screen

 Movie detail screen
  [x] The complete movie detail should be shown (ratings, synopsis, cast&Crew, favorite)
  [x] Screen is scrollable to show all contents on small screens
  [x] Allow the user to tap Share button to share movie through other apps.
  [x] Allow the user to tap Link button to open a movie detail link on WebView.

 Profile screen
  [x] Display users profile include picture, name, recommented by location, suggestion by radius
  [ ] Save Data in local Databse to support offline
  [x] Allow the user to tap Trailers button to show movie list

 Misc
 [x] User Repository pattern to abstract the data source
 [x] LiveData is being used
 [ ] Apply MVVM pattern with ViewModel in Google's new Archetecture components

## Video
![](record/record.gif)

## Download apk
To be uploaded

## Open-source libraries used

- [ButterKnife](https://github.com/JakeWharton/butterknife) - Bind Android views and callbacks to fields and methods.
- [Picasso](http://square.github.io/picasso/) - image downloading and caching library for Android
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [okhttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) - [Circular ImageView](https://github.com/hdodenhof/CircleImageView) - A Circular ImageView for Android
An OkHttp interceptor which logs HTTP request and response data.
