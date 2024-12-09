import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.purelearn.api.SignUpState
import com.example.purelearn.api.SignUpViewModel
import com.example.purelearn.ui.theme.PURELEARNTheme
import com.example.purelearn.ui.theme.components.HeaderText
import com.example.purelearn.ui.theme.components.LoginTextField

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = viewModel()) {
    val signUpState by viewModel.signUpState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
     val (agree,onAgreeChange)= rememberSaveable {
         mutableStateOf(false)
     }
    val context= LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(text = "Sign Up",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start))

        LoginTextField(
            value = name,
            onValueChange = { name = it },
            labelText = "Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(defaultPadding))

        LoginTextField(
            value = email,
            onValueChange = { email = it },
            labelText ="Email",
            leadingIcon = Icons.Default.Person,
           keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(defaultPadding))


        LoginTextField(
            value = password,
            onValueChange = { password = it },
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
           modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(defaultPadding))

        Row{
            val privacyText = "Privacy"
            val policyText="Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground ))
                {
                    append("I Agree with")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary))
                {
                  pushStringAnnotation(tag = privacyText,privacyText)
                    append(privacyText)
                }

                append(" And ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary))
                {
                    pushStringAnnotation(tag = policyText,privacyText)
                    append(policyText)
                }

            }


        }

        Button(
            onClick = {  viewModel.signUp(email, password, name)
                      ;navController.navigate("login")
                      },
            modifier = Modifier.fillMaxWidth(),
            enabled = signUpState !is SignUpState.Loading
        ) {
            if (signUpState is SignUpState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(16.dp))
            } else {
                Text("Sign Up")
            }
        }
        TextButton(onClick = {  }) {
            androidx.compose.material.Text("already have an account, Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (signUpState) {
            is SignUpState.Success -> Text((signUpState as SignUpState.Success).message, color = MaterialTheme.colorScheme .primary)
            is SignUpState.Error -> Text((signUpState as SignUpState.Error).error, color = MaterialTheme.colorScheme.error)
            else -> {}
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen()
{
    PURELEARNTheme {
       //SignUpScreen()
    }
}