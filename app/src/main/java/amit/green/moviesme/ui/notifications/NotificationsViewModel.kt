package amit.green.moviesme.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "TODO: show movies as a swipe able stack"
    }
    val text: LiveData<String> = _text
}