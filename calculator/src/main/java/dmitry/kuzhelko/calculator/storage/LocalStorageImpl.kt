package dmitry.kuzhelko.calculator.storage

import android.util.Log
import dmitry.kuzhelko.calculator.storage.entity.Expression
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class LocalStorageImpl(private val database: ExampleDatabase) : LocalStorage {

    override fun saveExpression(example: String) {
        Completable.fromAction { database.exampleDao().saveExample(Expression(value = example)) }
                .subscribeOn(Schedulers.io())
        Log.i("LocalStorageImpl", "example = $example")
    }

    override fun getExpressionById(id: Int?): Single<Expression> {
        return database.exampleDao().getExample(id)
    }

    override fun loadAllExpression(): Single<List<Expression>> {
        return database.exampleDao().loadAllExamples()
    }
}