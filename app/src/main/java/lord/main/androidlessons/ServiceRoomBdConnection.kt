package lord.main.androidlessons

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.runBlocking
import lord.main.androidlessons.room_db.AppDatabase
import lord.main.androidlessons.room_db.TableChannels
import lord.main.androidlessons.room_db.TableHistory
import java.util.*

class ServiceRoomBdConnection : Service() {
    /**
     * Объект для взаимодействия с сервисом
     */
    private var binder: Binder = Binder()

    private lateinit var db: AppDatabase

    override fun onCreate() = runBlocking {
        super.onCreate()
        db = AppDatabase.getDatabase(this@ServiceRoomBdConnection)
    }

    override fun onDestroy() = runBlocking {
        super.onDestroy()
        db.close()
    }

    /**
     * Функция сохранения уведомления в таблицу истории уведомлений БД
     *
     * @param channelId    уникальный идентификатор канала уведомлений
     * @param title        заголовок уведомления
     * @param text         текст уведомления
     * @param iconRes      ссылка на иконку в ресурсах
     * @param iconColorRes ссылка на цвет иконки в ресурсах
     * @return уникальный идентификатор строки в таблице, либо -1, если добавление не произошло
     */
    suspend fun registrNotify(
        channelId: String,
        title: String,
        text: String,
        iconRes: Int,
        iconColorRes: Int
    ): Long {
        return db.historyDao().registerNotify(TableHistory(
            null,
            channelId,
            title,
            text,
            iconRes,
            iconColorRes
        ))
    }

    /**
     * Функция возвращает список, состоящий из подсписков наименований каналов уведомлений и их уникальными идентификаторами
     *
     * @return возвращает двухуровневый список либо null, если таблица пуста
     */
    fun allChannels(): ArrayList<List<String>>? {
        val names = db.channelsDao().names
        return if (names.isNotEmpty())
            arrayListOf(names, db.channelsDao().ids)
        else null
    }

    /**
     * Функция добавляет в таблицу каналов уведомлений новую строку
     *
     * @param count           количество каналов уведомлений в приложении минус один для получения свободного индекса
     * @param name            наименование
     * @param importanceLevel уровень важности
     * @param description     описание
     * @param lightsColorRes  цвет светового сигнала или null, если отключено
     * @param vibration       1 - включена вибрация, 0 - отключена
     * @param groupId         уникальный идентификатор группы каналов или "null", если канал не сгруппирован
     * @param soundRes        код мелодии: 0 - нет, 1 - по умолчанию, остальные по порядку в ресурсах
     * @return true - добавлена, false - ошибка при добавлении
     */
    fun addChannel(
        count: Int,
        name: String,
        importanceLevel: Int,
        description: String,
        lightsColorRes: Int,
        vibration: Int,
        groupId: String,
        soundRes: Int
    ): Boolean {
        return try {
            db.channelsDao().registrChannel(TableChannels(
                "My app notification channel $count",
                name,
                importanceLevel,
                description,
                lightsColorRes,
                vibration,
                groupId,
                soundRes
            ))
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Удаляет из таблицы истории уведомлений строку
     *
     * @param id уникальный идентификатор строки, которую нужно удалить
     * @return количество удалённых строк в таблице
     */
    fun unregistrNotify(id: Long): Int {
        return db.historyDao().unregistrNotify(id)
    }

    /**
     * Возвращает все записи таблицы истории уведомлений из БД
     *
     * @return все записи таблицы
     */
    fun getHistory(): List<TableHistory> {
        return db.historyDao().all
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * Класс для реализации функций, доступных из связанной с сервисом активности
     */
    inner class Binder : android.os.Binder() {
        /**
         * Функция возвращает объект сервиса для использования его функций
         *
         * @return объект сервиса
         */
        val service: ServiceRoomBdConnection
            get() = this@ServiceRoomBdConnection
    }
}