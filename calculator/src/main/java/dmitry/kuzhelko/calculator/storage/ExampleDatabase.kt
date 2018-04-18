package dmitry.kuzhelko.calculator.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import dmitry.kuzhelko.calculator.storage.Dao.ExampleDao
import dmitry.kuzhelko.calculator.storage.entity.Expression

@Database(entities = [Expression::class], version = 1, exportSchema = false)
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}