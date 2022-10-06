package nl.littlerobots.firebaseauthexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // // Note that we need to use onPostCreate here, otherwise
    // // the nav host is not available yet
    // override fun onPostCreate(savedInstanceState: Bundle?) {
    //     super.onPostCreate(savedInstanceState)
    //     // To prevent hardcoding (parts) of the sign in deep link,
    //     // we setup the nav graph here and optionally add
    //     // the incoming deep link to the graph as needed.
    //     // This only needs to be done if this activity is launched fresh, so we check
    //     // for a null savedInstanceState.
    //     if (savedInstanceState == null) {
    //         val link = intent.data?.toString()
    //         val navGraph = findNavController(R.id.nav_host_fragment)
    //             .navInflater
    //             .inflate(R.navigation.nav_graph)
    //
    //         // if we have a sign in link, add it to the graph
    //         if (link != null && FirebaseAuth.getInstance().isSignInWithEmailLink(link)) {
    //             addDeeplinkToLoginDestination(navGraph, link)
    //         }
    //         findNavController(R.id.nav_host_fragment)
    //             .setGraph(navGraph, intent.extras)
    //     }
    // }

    private fun addDeeplinkToLoginDestination(navGraph: NavGraph, link: String) {
        val loginGraph = navGraph.findNode(R.id.nav_login) as NavGraph
        // our destination for handling the login link
        val loginWithLinkDestination =
            requireNotNull(loginGraph.findNode(R.id.loginWithLinkFragment))
        loginWithLinkDestination.addDeepLink(link)
    }
}
