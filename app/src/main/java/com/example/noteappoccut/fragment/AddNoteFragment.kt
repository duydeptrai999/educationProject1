package com.example.noteappoccut.fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.noteappoccut.MainActivity
import com.example.noteappoccut.R
import com.example.noteappoccut.databinding.FragmentAddNoteBinding
import com.example.noteappoccut.model.Note
import com.example.noteappoccut.viewmodel.NoteViewModel


class AddNoteFragment : Fragment(R.layout.fragment_add_note),MenuProvider {

    private var addNoteBinding : FragmentAddNoteBinding?= null
    private  val binding get() = addNoteBinding!!

    private lateinit var notesViewModel : NoteViewModel
    private lateinit var  addNoteView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addNoteBinding= FragmentAddNoteBinding.inflate(inflater,container,false)
        return binding.root
          }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel=(activity as MainActivity).noteViewModel

       addNoteView = view
              }

    private fun saveNote(view:View){

        val noteTitle= binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

       if (noteTitle.isNotBlank()){
           val note= Note(0, noteTitle,noteDesc)
           notesViewModel.addNote(note)
           Toast.makeText(addNoteView.context,"note Saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)


       }else{
           Toast.makeText(addNoteView.context, "please enter note tile",Toast.LENGTH_SHORT).show()
       }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
       menu.clear()
        menuInflater.inflate(R.menu.menu_add_note,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu->{
                saveNote(addNoteView)
                true
            }
            else->false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addNoteBinding= null
    }
}


