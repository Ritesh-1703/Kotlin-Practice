package com.example.k_mvvm_notesapplication.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import com.example.k_mvvm_notesapplication.ViewModel.NoteViewModel
import com.example.k_mvvm_notesapplication.ViewModel.NoteViewModelFactory
import com.example.k_mvvm_notesapplication.data.Note
import com.example.k_mvvm_notesapplication.data.NoteDatabase
import com.example.k_mvvm_notesapplication.databinding.ActivityNoteEditorBinding
import com.example.k_mvvm_notesapplication.repository.NoteRepository
import com.example.k_mvvm_notesapplication.R



class NoteEditor : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "NOTE"
    }
    private lateinit var binding: ActivityNoteEditorBinding
    private lateinit var viewModel: NoteViewModel

    private var currentNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupViewModel()

        currentNote = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_NOTE, Note::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_NOTE)
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setupViewModel() {
        val dao = NoteDatabase.getDatabase(this).noteDao()
        val repo = NoteRepository(dao)
        val factory = NoteViewModelFactory(repo)

        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_note_editor, menu)
        menu.findItem(R.id.action_delete).isVisible = currentNote !=null
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_save -> {
                saveNote()
                true
            }
            R.id.action_delete -> {
                deleteNote()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun deleteNote() {
        if(currentNote !=null){
            viewModel.delete(currentNote!!)

        }
        finish()

    }

    private fun saveNote() {
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        if (title.isEmpty()){
            binding.etTitle.error = "Title is required"
            return
        }

        if (content.isEmpty()){
            binding.etContent.error = "Content is required"
            return
        }

        if (currentNote == null){
            val note = Note(title = title, content = content)
            viewModel.insert(note)
        }else{
            val note = currentNote!!.copy(title = title, content = content)
            viewModel.update(note)
        }

        finish()
    }

}