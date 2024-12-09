import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.purelearn.api.LoginViewModel
import com.example.purelearn.ui.theme.PURELEARNTheme
import com.example.purelearn.ui.theme.components.HeaderText
import com.example.purelearn.ui.theme.components.LoginTextField


val defaultPadding = 16.dp
val itemSpacing=8.dp
@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginState by viewModel.loginState

        val (checked,onCheckedChange)= rememberSaveable {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(text = "Login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start))

        LoginTextField(
            value = email.value,
            onValueChange = { email.value = it },
            labelText = "Email",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(itemSpacing))

        LoginTextField(
            value = password.value,
            onValueChange = { password.value = it },
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(itemSpacing))


        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                Text("Remember me")
            }
        }

        TextButton(onClick = {  }) {
            Text("Does not have an account, sign up")
        }


    Spacer(modifier = Modifier.height(itemSpacing))


        Button(
            onClick = { viewModel.login(email.value, password.value) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }



        Spacer(modifier = Modifier.height(16.dp))
        Text(text = loginState)

        LaunchedEffect(loginState) {
            if (loginState.isNotEmpty()) {
                Toast.makeText(context, loginState, Toast.LENGTH_LONG).show()
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen()
{
    PURELEARNTheme {
       // LoginScreen()
    }
}