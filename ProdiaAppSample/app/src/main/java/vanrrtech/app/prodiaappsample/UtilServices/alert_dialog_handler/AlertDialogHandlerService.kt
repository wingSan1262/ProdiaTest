package vanrrtech.app.prodiaappsample.UtilServices.alert_dialog_handler

import android.content.Context
import androidx.appcompat.app.AlertDialog

class AlertDialogHandlerService(val activityContext : Context) {

    fun showQuestionDialog(title : String,
                           msg : String,
                           okButAction: () -> Unit,
                           negativeButAction: () -> Unit) {
        AlertDialog.Builder(activityContext)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("OK") { dialog, which ->
                okButAction()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                negativeButAction()
            }
            .show()
    }

    fun showInformationDialog(title : String,
                           msg : String,
                           okButAction: () -> Unit) {
        AlertDialog.Builder(activityContext)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("OK") { dialog, which ->
                okButAction()
            }
            .show()
    }
}