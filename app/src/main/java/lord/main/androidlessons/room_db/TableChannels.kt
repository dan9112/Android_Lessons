package lord.main.androidlessons.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс, представляющий собой представление таблицы каналов уведомлений в БД
 */
@Entity(tableName = "notify_channels")
class TableChannels (
    /**
     * Уникальный идентификатор канала уведомлений
     */
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: String,

    /**
     * Наименование канала уведомлений
     */
    @ColumnInfo(name = "name")
    val name: String,

    /**
     * Уровень важности сообщений канала уведомлений
     */
    @ColumnInfo(name = "importance_level")
    val importanceLevel: Int,

    /**
     * Описание канала уведомлений
     */
    @ColumnInfo(name = "description")
    val description: String?,

    /**
     * Уникальный идентификатор цвета индикации о пришедшем уведомлении по каналу в таблице цветов.
     *
     * null - индикация цветом отключена для канала
     */
    @ColumnInfo(name = "lights_color_res")
    val idLights: Int?,

    /**
     * Флаг включения вибрации для индикации о пришедшем уведомлении по каналу
     */
    @ColumnInfo(name = "vibration")
    val vibration: Int,

    /**
     * Уникальный идентификатор группы, к которой принадлежит канал.
     *
     * null - канал не сгруппирован
     */
    @ColumnInfo(name = "group_id")
    val groupId: String?,

    /**
     * Уникальный индикатор мелодии уведомления из ресурсов.
     *
     * null - звуковые уведомления отключены, default - уведомления по умолчанию
     */
    @ColumnInfo(name = "sound_res")
    val soundId: Int?
)