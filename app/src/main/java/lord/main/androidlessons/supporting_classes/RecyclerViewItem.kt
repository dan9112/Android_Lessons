package lord.main.androidlessons.supporting_classes

import lord.main.androidlessons.ActivityShowNotifyHistory

/**
 * Шаблон со всеми необходимыми данными для элемента RecyclerView в [активности демонстрации истории уведомлений][ActivityShowNotifyHistory]
 */
object RecyclerViewItem {
    /**
     * Уникальный идентификатор уведомления
     */
    var id = 0L

    /**
     * Уникальный идентификатор иконки из ресурсов
     */
    var iconId = 0

    /**
     * Цвет заливки иконки
     */
    var iconColor = 0

    /**
     * Уникальный идентификатор канала уведомлений
     */
    var channelId = ""

    /**
     * Заголовок уведомления
     */
    var title = ""

    /**
     * Текст уведомления
     */
    var text = ""
}