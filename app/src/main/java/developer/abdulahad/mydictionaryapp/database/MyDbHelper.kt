package developer.abdulahad.mydictionaryapp.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import developer.abdulahad.mydictionaryapp.models.Category

class MyDbHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME,null, VERSIONS),
    MyInterface {
    companion object{
        var DB_NAME = "DictionaryToMe"

        var VERSIONS = 1

        var TABLE_NAME = "Table_dictionary"

        var ID = "myId"

        var MY_CATEGORY = "MY_CATEGORY"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ($ID INTEGER not null primary key autoincrement unique, $MY_CATEGORY text not null)"

        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun add(category: Category) {
        var database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(MY_CATEGORY,category.category)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()
    }

    override fun read(): ArrayList<Category> {
        val list = ArrayList<Category>()
        val query = "select * from $TABLE_NAME"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val category = Category(
                    cursor.getInt(0),
                    cursor.getString(1)
                )

                list.add(category)

            }while (cursor.moveToNext())
        }
        return list
    }

    override fun update(category: Category) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,category.id)
        contentValues.put(MY_CATEGORY,category.category)
        database.update(TABLE_NAME,contentValues,"$ID = ?", arrayOf(category.id.toString()))
    }

    override fun delete(category: Category) {
        val database = this.writableDatabase
        database.delete(TABLE_NAME,"$ID = ?", arrayOf(category.id.toString()))
        database.close()
    }

}