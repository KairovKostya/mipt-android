package com.phystech.edu.mipt.screen.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.phystech.edu.mipt.R


@Composable
fun SignUpScreen(signUpScreenViewModel: SignUpScreenViewModel, navController: NavController) {
    Box(modifier = Modifier.padding(10.dp)) {
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state by signUpScreenViewModel.viewState.observeAsState()
        val viewState = state ?: return
        Logo()
        //sign up
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.sign_up_label),
            style = TextStyle(fontSize = 20.sp)
        )
        //username
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.username_label)) },
            value = viewState.username,
            onValueChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeUsername(it), navController)},
            placeholder = {
                Text(
                    text = stringResource(R.string.username_placeholder),
                    color = Color.Gray
                )
            }
        )
        //email
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.email_label)) },
            value = viewState.email,
            onValueChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeEmail(it), navController) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = {
                Text(
                    text = stringResource(R.string.email_placeholder),
                    color = Color.Gray
                )
            }
        )
        //password
        Spacer(modifier = Modifier.height(18.dp))
        OutlinedTextField(
            label = { Text(text = stringResource(R.string.password_label)) },
            value = viewState.password,
            onValueChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangePassword(it), navController) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        //
        Spacer(modifier = Modifier.height(18.dp))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row{
                Checkbox(
                    checked = viewState.keepSigned,
                    onCheckedChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeKeepSigned, navController) },
                    //modifier = Modifier.padding(5.dp)
                )
                Text(
                    stringResource(R.string.agreement_1),
                    color = Color.Gray
                )
            }

            Row{
                Checkbox(
                    checked = viewState.emailAboutPricing,
                    onCheckedChange = { signUpScreenViewModel.obtainEvent(SignUpEvent.ChangeEmailAboutPricing, navController) },
                    //modifier = Modifier.padding(5.dp)
                )
                Text(
                    stringResource(R.string.agreement_2),
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Box(modifier = Modifier.padding(20.dp, 5.dp, 20.dp, 5.dp)) {
            Button(
                onClick = { signUpScreenViewModel.obtainEvent(SignUpEvent.SignUp, navController)},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(R.string.sign_up))
            }
        }

        ClickableText(
            text = AnnotatedString(stringResource(R.string.agreement_3)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            onClick = {},
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFF53E88B),
            )
        )
    }
}}