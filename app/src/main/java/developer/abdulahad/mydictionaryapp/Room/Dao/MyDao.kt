package developer.abdulahad.mydictionary.Room.Dao

import androidx.room.*
import developer.abdulahad.mydictionary.Room.Entity.MyRoom

@Dao
interface MyDao {
    @Insert
    fun create(myRoom: MyRoom)

    @Query("select * from myRoom")
    fun read(): List<MyRoom>

    @Update
    fun update(myRoom: MyRoom)

    @Delete
    fun delete(myRoom: MyRoom)
}