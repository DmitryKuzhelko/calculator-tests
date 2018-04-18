package dmitry.kuzhelko.calculator.storage.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import dmitry.kuzhelko.calculator.storage.entity.Expression
import io.reactivex.Single

@Dao
interface ExampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveExample(expression: Expression)

    @Query("SELECT * FROM examples WHERE id = :id")
    fun getExample(id: Int?): Single<Expression>

    @Query("SELECT * FROM examples")
    fun loadAllExamples(): Single<List<Expression>>
}