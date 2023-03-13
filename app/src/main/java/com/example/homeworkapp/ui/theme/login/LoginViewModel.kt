package com.example.homeworkapp.ui.theme.login

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import androidx.work.*
import com.example.homeworkapp.R
import android.content.Intent
import android.graphics.BitmapFactory
import android.speech.tts.TextToSpeech
import android.util.Log

import androidx.core.app.NotificationManagerCompat
import com.example.homeworkapp.data.entity.Credentials
import com.example.homeworkapp.data.entity.Message
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.data.entity.room.HomeWorkDatabase
import com.example.homeworkapp.data.repository.CredentialsRepository
import com.example.homeworkapp.data.repository.ReminderRepository
import com.example.homeworkapp.ui.util.NotificationWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class LoginViewModel(
    application: Application
): ViewModel() {

    val credentials: LiveData<List<Credentials>>
    private val repository: CredentialsRepository
    val searchResults: MutableLiveData<List<Reminder>>
    val reminder: LiveData<List<Reminder>>

    private var textToSpeech: TextToSpeech? = null
    private val repositoryReminder: ReminderRepository
    private val _state = MutableStateFlow(MessageListViewState())
    private val _reminderInEdit = MutableStateFlow(ReminderInEditViewState())
    private val _notificationTime = MutableStateFlow(NotificationTimeViewState())
    private var appContext = application.applicationContext
    val state: StateFlow<MessageListViewState>
        get() = _state

    val reminderInEdit: StateFlow<ReminderInEditViewState>
        get() = _reminderInEdit

    val notificationTime: StateFlow<NotificationTimeViewState>
        get() = _notificationTime

    init {
        createNotificationChannel(context = application.applicationContext)
       // setOneTimeNotification(application.applicationContext)
        val credentialDb = HomeWorkDatabase.getInstance(application)
        val credentialDao = credentialDb.credentialsDao()
        repository = CredentialsRepository(credentialDao)
        insertProduct(Credentials(id=1, password = "password", username = "user"))
        credentials = repository.credentials

        val reminderDb = HomeWorkDatabase.getInstance(application)
        val reminderDao = reminderDb.reminderDao()
        repositoryReminder = ReminderRepository(reminderDao)
        reminder = repositoryReminder.reminders

        searchResults = repositoryReminder.searchResults



    }

    fun insertReminder(reminder: Reminder, viewModel: LoginViewModel, executionTime: Int, useNotification: Boolean) {
        if(useNotification){
            setSceduledNotification( appContext , reminder, executionTime, viewModel)
        }
        repositoryReminder.insertReminder(reminder)
    }

    fun updateReminder(reminder: Reminder) {
        repositoryReminder.insertReminder(reminder)
    }

    fun searchReminderWithId(id: Long){
        repositoryReminder.getReminderWithId(id)
    }
    fun insertProduct(credential: Credentials) {
        repository.insertProduct(credential)
    }

    fun findProduct(id: Long) {
        repository.getCredentialsWithId(id)
    }

    fun deleteReminder(id: Long) {
        repositoryReminder.deleteReminder(id)
    }

    fun setReminderInEdit(reminder: Reminder) {
        viewModelScope.launch {
            _reminderInEdit.value = ReminderInEditViewState(
                reminder = reminder
            )
        }
    }

    fun setNotificationTime(time: Int) {
        viewModelScope.launch {
            _notificationTime.value = NotificationTimeViewState(
                time = time
            )
        }
    }

    fun textToSpeech(text: String?){

        textToSpeech = TextToSpeech(
            appContext
        ){
            if ( it == TextToSpeech.SUCCESS){
                textToSpeech?.let {txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }
}


private fun setSceduledNotification(context: Context, reminder: Reminder, executionTime: Int, viewModel: LoginViewModel) {
    val workManager = WorkManager.getInstance(context)
    val delay = executionTime.toLong()
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
    Log.i("NotificationDelay", "Minutes $delay")
    val notificationWorker = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(delay,TimeUnit.SECONDS)
        .setConstraints(constraints)
        .build()

    workManager.enqueue(notificationWorker)

    // Monitoring for state of work
    workManager.getWorkInfoByIdLiveData(notificationWorker.id)
        .observeForever { workInfo ->
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                createSuccessNotification(context, reminder.message)
                reminder.show_reminder = true
                viewModel.updateReminder(reminder)
            }
        }
}

private fun createErrorNotification(context: Context) {
    val noticationId = 2
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(R.drawable.header_background)
        .setContentInfo("Failure!")
        .setContentText("Your countdown failed")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is unique for each notification you defined
        notify(noticationId, builder.build())
    }
}
private fun createSuccessNotification(context: Context, message: String) {
    val noticationId = 1
    val myBitmap = BitmapFactory.decodeResource(context.resources, com.example.homeworkapp.R.drawable.tausta)
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(R.drawable.tausta)
        .setContentInfo("Success!")
        .setContentText(message)
        .setLargeIcon(myBitmap)
        .setStyle(NotificationCompat.BigPictureStyle()
            .bigPicture(myBitmap)
            .bigLargeIcon(null)
        )
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is unique for each notification you defined
        notify(noticationId, builder.build())
    }
}

private fun createReminderMadeNotification(context: Context,reminder: Reminder, timeToExecute: Int){
    val noticationId = 3
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(R.drawable.header_background)
        .setContentInfo("New Reminder made")
        .setContentText(" $${reminder.message}")
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    with(NotificationManagerCompat.from(context)) {
        // notificationId is unique for each notification you defined
        notify(noticationId, builder.build())
    }
}
private fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "NotificationChannelName"
        val descriptionText = "NotificationChannelDescriptionText"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }

        // register the channel with the system
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

data class MessageListViewState(
    val messages: List<Message> = emptyList()
)
data class ReminderInEditViewState(
    val reminder: Reminder = Reminder()
)

data class NotificationTimeViewState(
    val time: Int = 0
)

