package org.carecampuspartner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.carecampuspartner.auth.AndroidAuthViewModel
import org.carecampuspartner.main.AppAndroid
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ActivityHolder.setActivity(this)
//        appContext = applicationContext

        val authViewModel: AndroidAuthViewModel by inject()

        signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
//                authViewModel.handleSignInResult(result.data)
            } else {
//                authViewModel.handleSignInResult(result.data)
            }
        }

        // Provide a runtime Koin binding that will launch the sign-in intent using the launcher
        val runtimeModule = module {
            single(named("googleSignIn")) {
                {
                    val intent = authViewModel.getSignInIntent(this@MainActivity)
                    signInLauncher.launch(intent)
                }
            }
            single(named("googleSignOut")) {
                {
                    authViewModel.signOut(this@MainActivity)
                }
            }
        }
        loadKoinModules(runtimeModule)

        setContent {
            AppAndroid(onRequestSignIn = {})
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    CarecampuspartnerApp()
}