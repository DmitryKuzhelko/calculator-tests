package dmitry.kuzhelko.calculator.storage

import dmitry.kuzhelko.calculator.storage.entity.Expression
import io.reactivex.Single
import java.util.concurrent.Executors


class LocalStorageImpl(private val database: ExampleDatabase) : LocalStorage {

    override fun saveExpression(example: String) {
        Executors.newSingleThreadExecutor().execute { database.exampleDao().saveExample(Expression(value = example)) }
    }

    override fun getExpressionById(id: Int?): Single<Expression> {
        return database.exampleDao().getExample(id)
    }

    override fun loadAllExpression(): Single<List<Expression>> {
        return database.exampleDao().loadAllExamples()
    }
}