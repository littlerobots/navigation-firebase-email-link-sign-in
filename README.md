## Firebase sign in with link example w/ Navigation Component

This example shows how to handle the sign in deep link from
[Firebase authentication](https://firebase.google.com/docs/auth/android/email-link-auth) in
combination with the [Navigation Component](https://developer.android.com/guide/navigation).

In particular, it shows how to defer the initialisation of the nav host, so that the detected sign
in link can be added to the graph. Then, after setting the graph, Navigation will handle the deep
link, navigate to the login destination where the sign in can be completed.

Alternatively a
[deep link](https://developer.android.com/guide/navigation/navigation-deep-link#implicit) with the
format of `<projectid>.firebaseapp.com/.*` for the destination could be added in the nav graph xml
directly. In that case there would be no need to defer the initialisation of the nav graph. The
downside of this approach is that the that same url is used for other things, like password reset
actions and Firebase Hosting. There's no good way to setup a filter to only trigger on sign-in deep
links, even if we could the format of those links might change in the future.
Read [the blog post](https://www.littlerobots.nl/blog/firebase-email-link-sign-in-navigation-component/)
for more on this.

In both cases the handling of the intent in the login destination is the same.

## Notes

This example tries to follow the best practices for the Navigation component:

* The start destination checks if the user is logged in, and navigates to the login graph if this is
  not the case. The start destination is fixed, and is not updated based on the login state.
* The `launchMode` for the activity is left to the default as recommended, and not set
  to `singleTop`.

The logic for the authentication is added to the `LoginFragment` and `LoginWithLinkFragment` for
example purposes. In a real application this logic would be further separated out using your
favorite architecture (view models, repositories etc).

## Running this example

* Create a new [Firebase](https://console.firebase.google.com/) project
* Add an Android app, make sure to also fill in the key store sha
* Add google-services.json to the `app` folder of this project
* Enable Firebase Authentication with the email provider + link sign in.
* Enable Dynamic Links in the Firebase console