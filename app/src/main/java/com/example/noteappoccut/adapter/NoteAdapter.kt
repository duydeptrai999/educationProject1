package com.example.noteappoccut.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappoccut.databinding.NoteLayoutBinding
import com.example.noteappoccut.fragment.HomeFragmentDirections
import com.example.noteappoccut.model.Note

class   NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class   NoteViewHolder(val itemBinding : NoteLayoutBinding) :RecyclerView.ViewHolder(itemBinding.root)


    private val differCallback = object :  DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return  oldItem.id== newItem.id &&
                    oldItem.noteDesc== newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
           return  oldItem == newItem
        }


    }
    val  differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
           }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote = differ.currentList[position]

        holder.itemBinding.noteTitle.text =currentNote.noteTitle
        holder.itemBinding.noteDesc.text =currentNote.noteDesc

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(currentNote)
            it.findNavController().navigate(direction)
        }
    }
}
//Đoạn mã trên là một RecyclerView.Adapter được thiết kế để hiển thị danh sách các ghi chú (Note) trong ứng dụng. Dưới đây là giải thích từng phần của mã nguồn:
//
//Package và Imports:
//
//Mã nguồn nằm trong package com.example.noteappoccut.adapter.
//Các thư viện và class được import để sử dụng trong mã nguồn.
//Class NoteAdapter:
//
//Là một lớp kế thừa từ RecyclerView.Adapter và sử dụng kiểu dữ liệu NoteViewHolder làm kiểu dữ liệu của view holder.
//Inner Class NoteViewHolder:
//
//Là một lớp inner class kế thừa từ RecyclerView.ViewHolder.
//Trong constructor của NoteViewHolder, nó nhận một tham số là itemBinding, có kiểu là NoteLayoutBinding. Điều này giúp quản lý việc liên kết với layout và truy cập các thành phần giao diện người dùng của mỗi mục trong danh sách.
//DifferCallback (differCallback):
//
//Là một đối tượng của DiffUtil.ItemCallback để xác định sự khác biệt giữa các mục trong danh sách.
//Phương thức areItemsTheSame và areContentsTheSame xác định xem hai mục có giống nhau về mặt dữ liệu hay không.
//val differ = AsyncListDiffer(this, differCallback):
//
//Là một đối tượng AsyncListDiffer được sử dụng để theo dõi sự thay đổi trong danh sách các ghi chú.
//this được truyền vào để thông báo cho AsyncListDiffer rằng NoteAdapter sẽ quản lý sự thay đổi danh sách.
//Phương thức onCreateViewHolder:
//
//Được gọi khi RecyclerView cần một ViewHolder mới.
//Sử dụng LayoutInflater để tạo một đối tượng NoteLayoutBinding từ layout được định nghĩa trong NoteLayoutBinding.
//Trả về một đối tượng NoteViewHolder với NoteLayoutBinding đã được tạo.
//Phương thức getItemCount:
//
//Trả về số lượng mục hiện tại trong danh sách.
//Phương thức onBindViewHolder:
//
//Được gọi khi RecyclerView cần hiển thị dữ liệu của một mục tại một vị trí cụ thể.
//Lấy ra Note tại vị trí hiện tại từ danh sách thông qua differ.currentList.
//Gán giá trị noteTitle và noteDesc từ currentNote cho các thành phần giao diện người dùng trong itemBinding.
//Thiết lập sự kiện click cho itemView để chuyển hướng đến màn hình chỉnh sửa với dữ liệu của ghi chú hiện tại.
//Tóm lại, NoteAdapter sử dụng AsyncListDiffer và DiffUtil để hiệu quả xử lý sự thay đổi trong danh sách và cung cấp các phương thức để tạo và hiển thị ViewHolder cho mỗi mục trong danh sách ghi chú.