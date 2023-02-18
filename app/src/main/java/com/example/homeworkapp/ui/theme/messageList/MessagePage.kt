package com.example.homeworkapp.ui.theme.messageList


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeworkapp.data.entity.Message
import com.example.homeworkapp.data.entity.Reminder
import com.example.homeworkapp.ui.theme.login.LoginViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessagePage(
    viewModel: LoginViewModel
) {
  //  val viewModel: MessageListViewModel = viewModel()
//    val viewState by viewModel.state.collectAsState()
    val viewState by viewModel.reminder.observeAsState(listOf())

    Column(Modifier.fillMaxWidth()) {
        MessageList(
            viewModel,
            list = viewState
        )
    }
}

@Composable
private fun MessageList(
    viewModel: LoginViewModel,
    list: List<Reminder>
){
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.Center
    ){
        items(list) { item ->
            MessageListItem(
                reminder = item,
                onClick = {},
                modifier = Modifier.fillParentMaxWidth()
              // viewModel
            )
        }
    }
}

@Composable
private fun MessageListItem(
    reminder: Reminder,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
  //  viewModel: MessageListViewModel
) {
    ConstraintLayout(modifier = Modifier.clickable { onClick() }) {
        val (divider, messageContent, messageType, icon, date) = createRefs()
        Divider(
            Modifier.constrainAs(divider) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
                width = Dimension.fillToConstraints
            }
        )
        // Title
        Text(
            text = reminder.message,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(messageContent) {
                linkTo(
                    start = parent.start,
                    end = icon.start,
                    startMargin = 24.dp,
                    endMargin = 16.dp
                )
                top.linkTo(parent.top, margin = 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        // Category
        Text(
            text = reminder.message,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(messageType) {
                linkTo(
                    start = parent.start,
                    end = icon.start,
                    startMargin = 24.dp,
                    endMargin = 8.dp
                )
                top.linkTo(messageContent.bottom, margin = 10.dp)
                bottom.linkTo(parent.bottom, 10.dp)
                width = Dimension.preferredWrapContent
            }
        )

        // date
        Text(
            text = when {
                reminder.date != null -> { reminder.date}
                else -> Date().formatToString()
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.constrainAs(date) {
                linkTo(
                    start = messageType.end,
                    end = icon.start,
                    startMargin = 8.dp,
                    endMargin = 16.dp
                )
                centerVerticallyTo(messageType)
                top.linkTo(messageContent.bottom, 6.dp)
                bottom.linkTo(parent.bottom, 10.dp)
            }
        )

        // Icon
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end)
                }
        )
        {
            Image(
                imageVector = Icons.Filled.Check,
                contentDescription = "check-mark"

            )
        }
    }
}

private fun Date.formatToString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(this)

}