package lord.main.androidlessons

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lord.main.androidlessons.supporting_classes.RecyclerViewAdapter

/**
 * Активность демонстрации истории уведомлений
 */
class ActivityShowNotifyHistory : AppCompatActivity() {
    /**
     * Экземпляр [сервиса для взаимодействия с БД][ServiceRoomBdConnection] приложения
     */
    private lateinit var bdRoomService: ServiceRoomBdConnection
    /**
     * Флаг подключения к [сервису взаимодействия с БД][ServiceRoomBdConnection]
     */
    private var bdRoomBound = false

    /**
     * Объект для подключения к [сервису взаимодействия с БД][ServiceRoomBdConnection]
     */
    private lateinit var bdRoomServiceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_notify_history)
        bdRoomServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                bdRoomService = (service as ServiceRoomBdConnection.Binder).service
                bdRoomBound = true

                val list = findViewById<RecyclerView>(R.id.notify_list)
                list.layoutManager = LinearLayoutManager(this@ActivityShowNotifyHistory)
                list.adapter = RecyclerViewAdapter(this@ActivityShowNotifyHistory, bdRoomService)
            }

            override fun onServiceDisconnected(name: ComponentName) {
                bdRoomBound = false
            }
        }
        bindService(
            Intent(this, ServiceRoomBdConnection::class.java),
            bdRoomServiceConnection,
            BIND_AUTO_CREATE
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!bdRoomBound) return
        unbindService(bdRoomServiceConnection)
        bdRoomBound = false
    }
}