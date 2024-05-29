This app demonstrates a potential defect with Compose Navigation and animaitons.
It's a basic app that includes a "Home Screen" destination and a "Login Screen" destination, and a NavHost to manage navigation between the two destinations. 
when the app launches it starts on the Home screen, and immediately navigates to the Login screen (and pops the entire backstack), and you can see the (incorrect) animation that is used.

## To reproduce the defect:

1. Build and run the attached app.
2. Observe the animation of the screens that appear

#### Expected Result:
The screen animates to the "login screen" destination using the enterTransition defined on the NavHost (the default, fadeIn), or no animation at all.

#### Actual Result:
The screen animates to the "login screen" destination by "sliding in" diagonally from the bottom right. This is odd because there is no transition defined on the NavHost (or anywhere) that uses a sliding animation.


## App Architecture
The [`MyNavHost.kt`](https://github.com/Bradleycorn/ComposeNavIssue/blob/master/app/src/main/java/net/bradball/myapplication/MyNavHost.kt) file defines a NavHost with destinations
for the "Home Screen" and the "Login Screen". The "Home Screen" is set as the start destination. It also defines `NavController` extension methods for navigating to the
Home and Login destinations, popping the entire existing backstack when doing so. 

The [`MyApp.kt`](https://github.com/Bradleycorn/ComposeNavIssue/blob/master/app/src/main/java/net/bradball/myapplication/MyApp.kt) file has a `MyApp` composable that is loaded
(via the MainActivity) when the app launches. `MyApp` calls the `MyNavHost` composable that is discussed above.

The `MyApp` composable has a `MyAppState` instance (also defined in the `MyApp.kt`) and uses a `LaunchedEffect` to navigate to the "Login Screen" if the user is not logged in (`MyAppState.isLoggedIn == false`).

This acheives the desired result, demonstrating the defect. When the app launches, it uses the "Home Screen" as the start destination, but because the user is not logged in,
it immediately navigates to the "Login Screen" destination, and in doing so, uses the (defective) diagonaly slide-in animation, which is not defined anyhere.
