package com.app.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.assignment1.data.UserStore
import com.app.assignment1.ui.theme.Assignment1Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Main() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    /*val tokenValue = remember {
        mutableStateOf(TextFieldValue())
    }*/
    val studentId = remember {
        mutableStateOf(TextFieldValue())
    }
    val emailId = remember {
        mutableStateOf(TextFieldValue())
    }
    val userNameId = remember {
        mutableStateOf(TextFieldValue("446"))
    }

    val store = UserStore(context)
    //val tokenText = store.getAccessToken.collectAsState(initial = "")
    val studentText = store.getAccessStudentId.collectAsState(initial = "")
    val emailText = store.getAccessEmailId.collectAsState(initial = "")
    val userNameText = store.getAccessuserNameId.collectAsState(initial = "")

    Column(
        modifier = Modifier.clickable { keyboardController?.hide() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "DataStorage Example", fontWeight = FontWeight.Bold)

        //Spacer(modifier = Modifier.height(15.dp))

        //Text(text = tokenText.value)

        //Spacer(modifier = Modifier.height(15.dp))

        Text(text = studentText.value)

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = emailText.value)

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = userNameText.value)

        Spacer(modifier = Modifier.height(15.dp))



        /*TextField(
            value = tokenValue.value,
            onValueChange = { tokenValue.value = it },
        )

        Spacer(modifier = Modifier.height(30.dp))*/

        TextField(
            value = studentId.value,
            onValueChange = { studentId.value = it },
            label = {Text("Student ID")},
        )

        Spacer(modifier = Modifier.height(30.dp))


        TextField(
            value = emailId.value,
            onValueChange = { emailId.value = it },
            label = {Text("Email ID")},
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = userNameId.value,
            onValueChange = { userNameId.value = it },
            label = {Text("User Name")},
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Button(
                onClick = {
                    // Call the function to load stored values
                    CoroutineScope(Dispatchers.IO).launch {
                        val loadedUserName = store.loadUserName()
                        val loadedEmail = store.loadEmail()
                        val loadedStudentID = store.loadStudentID()

                        // Update the mutable state variables
                        userNameId.value = TextFieldValue(loadedUserName)
                        emailId.value = TextFieldValue(loadedEmail)
                        studentId.value = TextFieldValue(loadedStudentID)
                    }
                }
            ) {
                Text(text = "Load")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {

                        store.saveStudentId(studentId.value.text)
                        store.saveEmailId(emailId.value.text)
                        store.saveuserNameId(userNameId.value.text)

                    }
                }
            ) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                onClick = {
                    // Call the function to clear stored values
                    CoroutineScope(Dispatchers.IO).launch {
                        store.clearStoredValues()
                    }

                    // Clear text fields
                    userNameId.value = TextFieldValue()
                    emailId.value = TextFieldValue()
                    studentId.value = TextFieldValue()
                }
            ) {
                Text(text = "Clear")
            }
        }

        /*Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    //store.saveToken(tokenValue.value.text)
                    store.saveStudentId(studentId.value.text)
                    store.saveEmailId(emailId.value.text)
                    store.saveuserNameId(userNameId.value.text)
                }
            }
        ) {
            Text(text = "Save")
        }*/
        Spacer(modifier = Modifier.height(15.dp))
        Divider()
        Text(text="Varun Bhatt")
        Text(text="301364446")
    }
}