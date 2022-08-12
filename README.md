# RAWGSearch

RAWG Search is an Android application writen with Kotlin. 
User of this application can search through RAWG api which contains games on various platforms.

Tools used to build this application are:
<ul>
  <li>Clean MVVM Architecture</li>
  <li>Timber - logging library</li>
  <li>Navigation - Jetpack Component</li>
  <li>RoomDB - Database component</li>
  <li>Retrofit - HTTP Client library</li>
  <li>Moshi - JSON Converting library</li>
  <li>OKHTTP Logging interceptor - Network request logging library</li>
  <li>Kotlin Coroutines - Concurrency library</li>
  <li>Picasso - Image loading library</li>
</ul>

On first screen User is prompted to select at least one genre to proceed with game selection.

<img src="images/genre_list_screen.jpg" width="320">

When selected user can pick the game from the list within selected genre('s).

<img src="images/game_list_screen.jpg" width="320">

If the game was selected, user can see the details of the game. Details contain image, title, playtime, release date, rating and ESRB rating.

<img src="images/game_details_screen.jpg" width="320">
