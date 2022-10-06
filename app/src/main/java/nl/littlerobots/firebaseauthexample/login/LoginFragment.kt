package nl.littlerobots.firebaseauthexample.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import nl.littlerobots.firebaseauthexample.BuildConfig
import nl.littlerobots.firebaseauthexample.R
import nl.littlerobots.firebaseauthexample.databinding.FragmentLoginBinding

private const val TAG = "LoginFragment"

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)

        binding.login.setOnClickListener {
            val email = binding.email.text.trim()
            if (email.isNotBlank()) {
                binding.email.isEnabled = false
                binding.login.isEnabled = false
                val actionCodeSettings = actionCodeSettings {
                    //TODO change this to a fallback url that makes sense. This url is used when
                    // the sign in link is opened in a web browser
                    url = "https://localhost"
                    handleCodeInApp = true
                    setAndroidPackageName(
                        BuildConfig.APPLICATION_ID,
                        false,
                        BuildConfig.VERSION_NAME
                    )
                }

                // Store the email address so that we can retrieve it later
                saveUserEmail(requireContext(), email.toString())

                FirebaseAuth.getInstance()
                    .sendSignInLinkToEmail(email.toString(), actionCodeSettings)
                    .addOnFailureListener {
                        Log.e(TAG, "Error signing in", it)
                        binding.login.isEnabled = true
                        binding.email.isEnabled = true
                    }
                    .addOnSuccessListener {
                        findNavController().navigate(R.id.action_link_sent)
                    }
            }
        }
    }
}