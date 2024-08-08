package com.example.noteappoccut.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappoccut.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase :RoomDatabase() {

    abstract fun  getNote(): NoteDao

        companion object{

        // những thay đổi được thực hiện sẽ hiển thị ngay lập tức với các lồng khác
        @Volatile
        private var instance : NoteDatabase? = null
            // đảm bảo chỉ có 1 luồng có thể thực thi mã trong khỗi tại 1 thời điểm
        private val LOCK = Any()

        //Đây là một hàm invoke, khi bạn gọi đối tượng như một hàm, hàm này sẽ được gọi.
        // Trong trường hợp này, nó được sử dụng để tạo một đối tượng NoteDatabase.
    operator  fun invoke(context: Context) = instance?:
        synchronized(LOCK){
            instance?:
          createDatabase(context).also{
              instance = it

          }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()
    }

    //instance ?: synchronized(LOCK) { createDatabase(context).also { instance = it } }:
    //
    //Trong mô hình Singleton, nếu instance đã được khởi tạo, nó sẽ được trả về ngay lập tức. Ngược lại, sẽ có một block synchronized đảm bảo rằng chỉ có một luồng có thể thực hiện trong khối đó tại một thời điểm.
    //Bên trong khối synchronized, nếu instance vẫn là null, thì createDatabase(context) sẽ được gọi để tạo một đối tượng NoteDatabase.
    //Hàm also được sử dụng để thực hiện một hành động và trả về đối tượng đã được tạo, sau đó instance sẽ được gán giá trị của đối tượng đã được tạo.
    //private fun createDatabase(context: Context):
    //
    //Hàm này sử dụng Room.databaseBuilder để tạo một đối tượng của lớp NoteDatabase. Các tham số được truyền vào là context, lớp của cơ sở dữ liệu (NoteDatabase::class.java), và tên cơ sở dữ liệu ("note_db"). Cuối cùng, hàm build() sẽ trả về một đối tượng NoteDatabase được tạo ra.
    //Tổng cộng, đoạn mã này sử dụng mô hình Singleton để đảm bảo rằng chỉ có một đối tượng của NoteDatabase được tạo và tái sử dụng trong ứng dụng. Việc sử dụng @Volatile và synchronized giúp đảm bảo tính đồng bộ và an toàn khi làm việc với nhiều luồng.
}