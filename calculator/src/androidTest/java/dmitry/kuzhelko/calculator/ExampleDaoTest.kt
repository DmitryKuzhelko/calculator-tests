package dmitry.kuzhelko.calculator

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import dmitry.kuzhelko.calculator.storage.Dao.ExampleDao
import dmitry.kuzhelko.calculator.storage.ExampleDatabase
import dmitry.kuzhelko.calculator.storage.entity.Expression
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

        //Создаём пример с id = 1 и записываем его в базу
        val example = Expression("2+2=4")
        example.id = 1
        exampleDao.saveExample(example)

        //Достаём пример из базы с тем же id = 1
        val exampleFromDb = exampleDao.getExample(1).blockingGet()

        //Сравниваем поле value до записи в базу и после считывания
        assertTrue(example.value == exampleFromDb.value)
    }

    //Записываем в бд несколько примеров, а затем считываем список всех примеров и сравниваем размер этого списка с кол-вом записанных примеров
    @Test
    @Throws(Exception::class)
    fun whenInsertExampleThenReadThem() {

        //записываем два примера в базу
        exampleDao.saveExample(Expression("2+2=4"))
        exampleDao.saveExample(Expression("9-4=5"))

        //список в который считываются записанные примеры из базы
        val list = exampleDao.loadAllExamples().blockingGet()

        //сравниваем количество того что записали(2 примера) с тем что достали из бд(размер списка)
        assertEquals(2, list.size)
    }
}