package com.jayden.deviceadministration.app.receiver

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PersistableBundle
import android.os.UserHandle
import android.util.Log
import androidx.annotation.RequiresApi
import com.jayden.deviceadministration.app.service.ProvisioningForegroundService
import com.jayden.deviceadministration.repository.logger.AdminLoggerFacade
import com.jayden.deviceadministration.repository.logger.AdminLoggerRepository
import com.jayden.deviceadministration.repository.AdminRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdminReceiver : DeviceAdminReceiver(), KoinComponent {
    val repo: AdminRepository by inject<AdminRepository>()
    val logger: AdminLoggerRepository by inject<AdminLoggerRepository>()
    val facade: AdminLoggerFacade by inject<AdminLoggerFacade>()

    // receiver method
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
    }

    // built-in methods in DeviceAdminReceiver
    override fun onBugreportFailed(context: Context, intent: Intent, failureCode: Int) {
        Log.v(TAG, "Bug report failed with code: $failureCode")
        super.onBugreportFailed(context, intent, failureCode)
    }

    override fun onBugreportShared(context: Context, intent: Intent, bugreportHash: String) {
        Log.v(TAG, "Bug report shared; hash: $bugreportHash")
        super.onBugreportShared(context, intent, bugreportHash)
    }

    override fun onBugreportSharingDeclined(context: Context, intent: Intent) {
        Log.v(TAG, "Bug report sharing was declined")
        super.onBugreportSharingDeclined(context, intent)
    }

    override fun onChoosePrivateKeyAlias(
        context: Context,
        intent: Intent,
        uid: Int,
        uri: Uri?,
        alias: String?
    ): String? {
        Log.v(TAG, "Private key delegation received from uid: $uid, returning null")
        return null
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onComplianceAcknowledgementRequired(context: Context, intent: Intent) {
        Log.v(TAG, "Profile turn-off received, acknowledging.")
        val manager = getManager(context)
        manager.acknowledgeDeviceCompliant()
    }

    override fun onDisableRequested(context: Context, intent: Intent): CharSequence? {
        Log.v(TAG, "Admin Disable requested. Why would you do such a thing?")
        return "why"
    }

    override fun onDisabled(context: Context, intent: Intent) {
        Log.v(TAG, "Admin no longer granted.")
        super.onDisabled(context, intent)
        repo.onAdminStatusChanged()
    }

    override fun onEnabled(context: Context, intent: Intent) {
        Log.v(TAG, "Admin granted to this app")
        super.onEnabled(context, intent)
        repo.onAdminStatusChanged()
    }

    override fun onLockTaskModeEntering(context: Context, intent: Intent, pkg: String) {
        Log.v(TAG, "Entering lock task mode with package: $pkg")
        super.onLockTaskModeEntering(context, intent, pkg)
    }

    override fun onLockTaskModeExiting(context: Context, intent: Intent) {
        Log.v(TAG, "Exiting lock task mode")
        super.onLockTaskModeExiting(context, intent)
    }

    override fun onNetworkLogsAvailable(
        context: Context,
        intent: Intent,
        batchToken: Long,
        networkLogsCount: Int
    ) {
        Log.v(TAG, "$networkLogsCount logs available, token of: $batchToken")
        val manager = getManager(context)
        val logs = manager.retrieveNetworkLogs(
            getWho(context),
            batchToken
        )
        super.onNetworkLogsAvailable(context, intent, batchToken, networkLogsCount)
        val tcpLogs = logs?.let { facade.filterTcpNetworkLogs(it.toList()) }
        val dnsLogs = logs?.let { facade.filterDnsNetworkLogs(it.toList()) }

        val pendingResult = goAsync()

        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try {
                tcpLogs?.let {
                    logger.saveTcpNetworkLogs(it)
                }
                dnsLogs?.let {
                    logger.saveDnsNetworkLogs(it)
                }
            } finally {
                pendingResult.finish()
            }
        }
    }

    override fun onPasswordChanged(context: Context, intent: Intent, user: UserHandle) {
        Log.v(TAG, "Password changed for a user on this device")
        super.onPasswordChanged(context, intent, user)
    }

    override fun onPasswordExpiring(context: Context, intent: Intent, user: UserHandle) {
        Log.v(TAG, "Password expired for a user on this device")
        super.onPasswordExpiring(context, intent, user)
    }

    override fun onPasswordFailed(context: Context, intent: Intent, user: UserHandle) {
        Log.v(TAG, "Password attempt failed for a user on this device")
        super.onPasswordFailed(context, intent, user)
    }

    override fun onPasswordSucceeded(context: Context, intent: Intent, user: UserHandle) {
        Log.v(TAG, "Password succeeded for a user on this device")
        super.onPasswordSucceeded(context, intent, user)
    }

    override fun onProfileProvisioningComplete(context: Context, intent: Intent) {
        Log.w(TAG, "========== PROFILE COMPLETE ==========")
        Log.w(TAG, "Package: ${context.packageName}")
        Log.w(TAG, "Intent: ${intent.action}")
        val manager = getManager(context)
        manager.setProfileEnabled(getWho(context))
        if (manager.isProfileOwnerApp(context.packageName)) {

            repo.onAdminStatusChanged()
            Log.v(TAG, "Profile is now enabled")
            context.stopService(Intent(context.applicationContext, ProvisioningForegroundService::class.java))
        }
        super.onProfileProvisioningComplete(context, intent)
    }

    override fun onSecurityLogsAvailable(context: Context, intent: Intent) {
        Log.v(TAG, "New Security Logs are available")
        val manager = getManager(context)
        val logs = manager.retrieveSecurityLogs(getWho(context))
        super.onSecurityLogsAvailable(context, intent)
        val mLogs = logs?.let {
            facade.mapSecurityLogs(it)
        }

        val pendingResult = goAsync()

        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try {
                mLogs?.let {
                    logger.saveSecurityLogs(it)
                }
            } finally {
                pendingResult.finish()
            }
        }
    }

    override fun onSystemUpdatePending(context: Context, intent: Intent, receivedTime: Long) {
        Log.v(TAG, "System Update is now Pending")
        super.onSystemUpdatePending(context, intent, receivedTime)
    }

    override fun onTransferAffiliatedProfileOwnershipComplete(context: Context, user: UserHandle) {
        Log.v(TAG, "Transfer of affiliated profile admin has been completed")
        super.onTransferAffiliatedProfileOwnershipComplete(context, user)
        repo.onAdminStatusChanged()
    }

    override fun onTransferOwnershipComplete(context: Context, bundle: PersistableBundle?) {
        Log.v(TAG, "Transfer of this admin has been completed")
        super.onTransferOwnershipComplete(context, bundle)
        repo.onAdminStatusChanged()
    }

    override fun onUserAdded(context: Context, intent: Intent, addedUser: UserHandle) {
        Log.v(TAG, "User added to this device")
        super.onUserAdded(context, intent, addedUser)
    }

    override fun onUserRemoved(context: Context, intent: Intent, removedUser: UserHandle) {
        Log.v(TAG, "User removed from this device")
        super.onUserRemoved(context, intent, removedUser)
    }

    override fun onUserStarted(context: Context, intent: Intent, startedUser: UserHandle) {
        Log.v(TAG, "User has started a profile/user on this device")
        super.onUserStarted(context, intent, startedUser)
    }

    override fun onUserStopped(context: Context, intent: Intent, stoppedUser: UserHandle) {
        Log.v(TAG, "User has stopped a profile/user on this device ")
        super.onUserStopped(context, intent, stoppedUser)
    }

    override fun onUserSwitched(context: Context, intent: Intent, switchedUser: UserHandle) {
        Log.v(TAG, "User has switched to another profile/user on this device")
        super.onUserSwitched(context, intent, switchedUser)
    }

    companion object {
        private const val TAG = "AdminReceiver"
    }
}