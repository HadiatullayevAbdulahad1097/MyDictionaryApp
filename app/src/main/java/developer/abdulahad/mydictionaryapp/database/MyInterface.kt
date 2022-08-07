package developer.abdulahad.mydictionaryapp.database

import developer.abdulahad.mydictionaryapp.models.Category

interface MyInterface {
    fun add(category: Category)

    fun read() : ArrayList<Category>

    fun update(category: Category)

    fun delete(category: Category)
}