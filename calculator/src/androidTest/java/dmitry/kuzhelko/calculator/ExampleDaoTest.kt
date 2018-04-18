package dmitry.kuzhelko.calculator

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import dmitry.kuzhelko.calculator.storage.Dao.ExampleDao
import dmitry.kuzhelko.calculator.storage.ExampleDatabase
import dmitry.kuzhelko.calculator.storage.entity.Expression
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleDaoTest {

    private lateinit var db: ExampleDatabase
    private lateinit var exampleDao: ExampleDao

    @Before
    @Throws(Exception::class)
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                ExampleDatabase::class.java)
                .build()
        exampleDao = db.exampleDao()

    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        db.close()
    }

    //Вставляем одну запись и проверяем, что она же считалась
    @Test
    @Throws(Exception::class)
    fun whenInsertEmployeeThenReadTheSameOne() {

        val example = Expression("2+2=4")
        example.id = 1
        exampleDao.saveExample(example)

        var exampleFromDb: Expression? = null

        //здесь достаём запись без Single
        exampleDao.getExample(1)
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .subscribe(({ loadExample ->
                    exampleFromDb = loadExample
                }), ({
                    Log.i("ExampleDaoTest", "Error loading example from db by id")
                }))

        assertTrue(example.value == exampleFromDb!!.value)
    }

    //Записываем в бд несколько примеров, а затем считываем список всех примеров и сравниваем размер этого списка с кол-вом записанных примеров
    @Test
    @Throws(Exception::class)
    fun whenInsertExampleThenReadThem() {

        //список в который считываеются данные из базы
        var list: List<Expression>? = null

        //здесь достаём список без Single, чтобы потом можно было узнать его размер
        exampleDao.loadAllExamples()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .subscribe(({ loadList ->
                    list = loadList
                }), ({
                    Log.i("ExampleDaoTest", "Error loading examples from db")
                }))

        //записываем два примера в базу
        exampleDao.saveExample(Expression("2+2=4"))
        exampleDao.saveExample(Expression("9-4=5"))

        //сравниваем то что записали с тем что считали(размер списка)
        assertEquals(2, list!!.size)
    }
}