package nl.littlerobots.firebaseauthexample.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import nl.littlerobots.firebaseauthexample.R

private const val TAG = "LoginWithLinkFragment"

class LoginWithLinkFragment : Fragment(R.layout.fragment_login_with_link) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.nav_login, true)
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = arguments?.getParcelable<Intent>(NavController.KEY_DEEP_LINK_INTENT)
        val link = intent?.data?.toString()
        if (link != null && FirebaseAuth.getInstance().isSignInWithEmailLink(link)) {
            val email = getUserEmail(requireContext()) ?: error("No email was stored")
            FirebaseAuth.getInstance().signInWithEmailLink(email, link).addOnSuccessListener {
                findNavController().popBackStack(R.id.nav_login, true)
            }.addOnFailureListener {
                Log.e(TAG, "Could not sign in with the email link")
                findNavController().popBackStack(R.id.nav_login, false)
            }
        }
    }
}