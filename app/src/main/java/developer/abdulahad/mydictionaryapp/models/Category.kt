package developer.abdulahad.mydictionaryapp.models

class Category {
     var id:Int = 0
     var category = ""


     constructor()
     constructor(id: Int, category: String) {
          this.id = id
          this.category = category
     }

     constructor(category: String) {
          this.category = category
     }
}