package com.example.noteappoccut.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappoccut.model.Note
import com.example.noteappoccut.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app) {

    fun addNote(note :Note)=
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
//class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app): Đây là khai báo của lớp NoteViewModel. Lớp này là một ViewModel được thiết kế để hoạt động trong môi trường của một ứng dụng Android. Nó mở rộng từ AndroidViewModel và nhận vào một đối tượng Application cùng với một NoteRepository như là một tham số.
//
//fun addNote(note: Note) = viewModelScope.launch { noteRepository.insertNote(note) }: Đây là một hàm addNote trong NoteViewModel. Hàm này được thiết kế để thêm một đối tượng Note vào cơ sở dữ liệu thông qua noteRepository.
//
//note: Đối tượng Note cần được thêm vào cơ sở dữ liệu.
//
//viewModelScope.launch { }: Sử dụng viewModelScope để tạo một coroutine trong phạm vi của ViewModel. Điều này giúp đảm bảo rằng khi ViewModel bị hủy, tất cả các công việc đang chạy trong coroutine này sẽ bị hủy theo sau.
//
//noteRepository.insertNote(note): Gọi phương thức insertNote của noteRepository. Có vẻ như NoteRepository là một lớp (hoặc giao diện) chịu trách nhiệm về việc thao tác với cơ sở dữ liệu để thêm Note mới.
//
//Hàm này được thiết kế để chạy bất đồng bộ để không ảnh hưởng đến luồng chính của ứng dụng. Coroutine và viewModelScope giúp quản lý các tác vụ bất đồng bộ một cách hiệu quả trong Android.

    fun deleteNote(note :Note)=
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }

    fun updateNote(note :Note)=
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query : String?)=noteRepository.searchNote(query)

}