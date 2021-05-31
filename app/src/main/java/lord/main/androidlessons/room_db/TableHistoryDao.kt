package lord.main.androidlessons.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TableHistoryDao {
    /**
     * Зарегистрировать уведомление в БД
     *
     * @param notify сохраняемые в БД атрибуты уведомления
     * @return уникальный идентификатор записи в таблице
     */
    @Insert
    fun registerNotify(notify: TableHistory): Long

    /**
     * Функция удаления записи из таблицы истории уведомлений по её уникальному идентификатору
     *
     * @param id уникальный идентификатор записи
     * @return количество удалённых строк
     */
    @Query("DELETE FROM TableHistory WHERE _id = :id")
    fun unregistrNotify(id: Long): Int

    /**
     * Функция получения всех записей в истории уведомлений
     *
     * @return список всех зарегистрированных уведомлений
     */
    @get:Query("SELECT * FROM TableHistory")
    val all: List<TableHistory>
}