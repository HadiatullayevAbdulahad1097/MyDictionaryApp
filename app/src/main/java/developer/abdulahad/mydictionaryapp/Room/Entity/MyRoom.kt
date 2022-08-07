package developer.abdulahad.mydictionary.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyRoom {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var english: String? = null
    var uzbek: String? = null
    var category: String? = null
    var like: Boolean? = null
    var image: String? = null

    constructor()


    constructor(like: Boolean?) {
        this.like = like
    }

    constructor(
        id: Int?,
        english: String?,
        uzbek: String?,
        category: String?,
        like: Boolean?,
        image: String?,
    ) {
        this.id = id
        this.english = english
        this.uzbek = uzbek
        this.category = category
        this.like = like
        this.image = image
    }

    constructor(
        english: String?,
        uzbek: String?,
        category: String?,
        like: Boolean?,
        image: String?,
    ) {
        this.english = english
        this.uzbek = uzbek
        this.category = category
        this.like = like
        this.image = image
    }
}