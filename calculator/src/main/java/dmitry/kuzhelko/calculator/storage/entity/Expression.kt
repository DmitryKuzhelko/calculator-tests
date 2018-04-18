package dmitry.kuzhelko.calculator.storage.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "examples")
data class Expression(val value: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
}