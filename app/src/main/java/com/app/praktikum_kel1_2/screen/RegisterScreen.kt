package com.app.praktikum_kel1_2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.praktikum_kel1_2.navigation.Screen
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.app.praktikum_kel1_2.R
import com.app.praktikum_kel1_2.model.request.RegisterRequest
import com.app.praktikum_kel1_2.service.api.ApiClient
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavHostController) {
    // State untuk field input
    var fullName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // State untuk error validasi tiap field
    var fullNameError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    // State untuk loading
    var isLoading by remember { mutableStateOf(false) }

    // Fokus dan context
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .imePadding(), // Menghindari keyboard menutupi input
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.b),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

            Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Register",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.primary
            ))

        Spacer(modifier = Modifier.height(24.dp))

        // Input Nama Lengkap
        OutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                fullNameError = false
            },
            isError = fullNameError,
            label = {Text("Nama Lengkap")},
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
        ))
        if (fullNameError) {
            Text("Nama lengkap wajib diisi", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input Username
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = false
            },
            isError = usernameError,
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
        ))
        if (usernameError) {
            Text("Username wajib diisi", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            isError = emailError,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            ))
        if (emailError) {
            Text("Email tidak valid", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            isError = passwordError,
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            ))
        if (passwordError) {
            Text("Password wajib diisi", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Input Konfirmasi Password
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                confirmPasswordError = false
            },
            isError = confirmPasswordError,
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline
            ))
        if (confirmPasswordError) {
            Text("Password tidak cocok", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Register
        Button(
            onClick = {
                focusManager.clearFocus()

                // Validasi form sebelum mengirim
                fullNameError = fullName.isBlank()
                usernameError = username.isBlank()
                emailError = email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                passwordError = password.isBlank()
                confirmPasswordError = confirmPassword != password

                // Jika semua valid, kirim data ke server
                if (!fullNameError && !usernameError && !emailError && !passwordError && !confirmPasswordError) {
                    isLoading = true
                    coroutineScope.launch {
                        try {
                            val response = ApiClient.instance.register(
                                RegisterRequest(
                                    nm_lengkap = fullName,
                                    email = email,
                                    username = username,
                                    password = password
                                )
                            )
                            isLoading = false
                            val body = response.body()

                            if (response.isSuccessful && body != null) {
                                Toast.makeText(context, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.Register.route) { inclusive = true }
                                }
                            } else {
                                val errorMsg = body?.message ?: response.message()
                                Toast.makeText(context, "Gagal: $errorMsg", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: Exception) {
                            isLoading = false
                            Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),  // <-- Titik bukan koma
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tombol navigasi ke halaman login
        TextButton(
            onClick = { navController.navigate(Screen.Login.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sudah punya akun? Login")
        }
    }

    // Dialog loading saat request berlangsung
    if (isLoading) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {},
            title = { Text("Mohon tunggu") },
            text = {
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Sedang mengirim data...")
                }
            }
        )
    }
}
@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            RegisterScreen(navController = rememberNavController())
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_5", name = "Register Screen with Errors")
@Composable
fun RegisterScreenWithErrorsPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                // Preview dengan state error
                var fullName by remember { mutableStateOf("") }
                var username by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var confirmPassword by remember { mutableStateOf("") }

                RegisterScreen(
                    navController = rememberNavController(),
                    // Tambahkan parameter state jika perlu
                )

                // Tombol untuk simulasi error state
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Simulasi trigger error
                        fullName = ""
                        username = ""
                        email = "invalid"
                        password = "123"
                        confirmPassword = "456"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    Text("Trigger Error State")
                }
            }
        }
    }
}