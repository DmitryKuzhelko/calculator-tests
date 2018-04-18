package dmitry.kuzhelko.calculator.storage

import dmitry.kuzhelko.calculator.storage.entity.Expression
import io.reactivex.Single

interface LocalStorage {

    fun saveExpression(example: String)

    fun getExpressionById(id: Int?): Single<Expression>

    fun loadAllExpression(): Single<List<Expression>>
}