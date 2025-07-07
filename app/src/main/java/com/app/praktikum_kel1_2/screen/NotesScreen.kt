package com.app.praktikum_kel1_2.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.praktikum_kel1_2.model.response.NoteItem
import com.app.praktikum_kel1_2.model.viewModel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(viewModel: NotesViewModel = viewModel()) {
    val notesState by viewModel.notes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("My Notes", style = MaterialTheme.typography.titleLarge)
            Text("+ Add new", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (notesState.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // Highlight note pertama
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF8E1)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(notesState[0].title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(notesState[0].content, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Share", color = MaterialTheme.colorScheme.primary)
                        Button(onClick = { /* TODO: Implement save */ }) {
                            Text("Save")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid notes lainnya
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(notesState.drop(1)) { note ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE0F7FA)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(note.title, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(note.content, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Share", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    val dummyNotes = listOf(
        NoteItem("Zidan", "1", "user1", "Belajar Compose", "Kita akan belajar membuat UI dengan Jetpack Compose.", "", ""),
        NoteItem("Admin", "2", "user2", "Authentication", "Bahas tentang JWT dan login session.", "", ""),
        NoteItem("Admin", "3", "user3", "Testing", "Mencoba fitur baru di versi beta.", "", "")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("My Notes", style = MaterialTheme.typography.titleLarge)
            Text("+ Add new", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF8E1)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(dummyNotes[0].title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(dummyNotes[0].content, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Share", color = MaterialTheme.colorScheme.primary)
                    Button(onClick = { }) {
                        Text("Save")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(dummyNotes.drop(1)) { note ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE0F7FA)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(note.title, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(note.content, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Share", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}
