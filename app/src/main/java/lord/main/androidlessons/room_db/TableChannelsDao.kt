package lord.main.androidlessons.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Интерфейс для работы с таблицой каналов уведомлений БД
 */
@Dao
interface TableChannelsDao {
    /**
     * Зарегистрировать канал уведомлений в БД
     *
     * @param channel сохраняемые в БД атрибуты канала
     * @return уникальный идентификатор записи в таблице
     */
    @Insert
    @Throws(Exception::class)
    fun registrChannel(channel: TableChannels)

    /**
     * Функция получения уникальных идентификаторов всех зарегистрированных каналов
     *
     * @return список уникальных идентификаторов
     */
    @get:Query("SELECT _id FROM notify_channels")
    val ids: List<String>

    /**
     * Функция получения наименований всех зарегистрированных каналов
     *
     * @return список наименований
     */
    @get:Query("SELECT name FROM notify_channels")
    val names: List<String>
}