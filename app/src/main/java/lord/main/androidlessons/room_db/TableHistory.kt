package lord.main.androidlessons.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс, представляющий собой представление таблицы истории уведомлений в БД
 */
@Entity
class TableHistory (
    /**
     * Уникальный идентификатор строки в таблице истории уведомлений
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long?,

    /**
     * Уникальный идентификатор канала уведомлений
     */
    @ColumnInfo(name = "channel_id")
    var channelId: String,

    /**
     * Заголовок уведомления
     */
    @ColumnInfo(name = "title")
    var title: String?,

    /**
     * Текст уведомления
     */
    @ColumnInfo(name = "text")
    var text: String?,

    /**
     * Уникальный идентификатор иконки уведомления из ресурсов
     */
    @ColumnInfo(name = "icon_res")
    var iconRes: Int,

    /**
     * Уникальный идентификатор цвета иконки уведомления из ресурсов
     */
    @ColumnInfo(name = "icon_color_res")
    var iconColorRes: Int
)