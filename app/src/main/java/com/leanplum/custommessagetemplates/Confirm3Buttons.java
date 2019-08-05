package com.leanplum.custommessagetemplates;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.leanplum.ActionArgs;
import com.leanplum.ActionContext;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.callbacks.ActionCallback;
import com.leanplum.callbacks.VariablesChangedCallback;

import static com.leanplum.custommessagetemplates.MessageTemplates.getApplicationName;

// Creating a new class for the 3-buttons Confirm Message
// Class name will be the name displayed in the Message dropdown on the Dashboard

// In this case we basically got the code from the Confirm class and added code for the third 'Maybe' button

public class Confirm3Buttons {

    private static final String NAME = "3-buttons Confirm";

    public static void register(Context currentContext) {
        Leanplum.defineAction(
                NAME,
                Leanplum.ACTION_KIND_MESSAGE | Leanplum.ACTION_KIND_ACTION,
                new ActionArgs().with(com.leanplum.custommessagetemplates.MessageTemplates.Args.TITLE, getApplicationName(currentContext))
                        .with(com.leanplum.custommessagetemplates.MessageTemplates.Args.MESSAGE, com.leanplum.custommessagetemplates.MessageTemplates.Values.CONFIRM_MESSAGE)
                        .with(com.leanplum.custommessagetemplates.MessageTemplates.Args.ACCEPT_TEXT, com.leanplum.custommessagetemplates.MessageTemplates.Values.YES_TEXT)
                        .with(com.leanplum.custommessagetemplates.MessageTemplates.Args.CANCEL_TEXT, com.leanplum.custommessagetemplates.MessageTemplates.Values.NO_TEXT)
                        // #### example: adding Text and Values for the Maybe button options
                        .with(com.leanplum.custommessagetemplates.MessageTemplates.Args.MAYBE_TEXT, com.leanplum.custommessagetemplates.MessageTemplates.Values.MAYBE_TEXT)
                        .withAction(com.leanplum.custommessagetemplates.MessageTemplates.Args.ACCEPT_ACTION, null)
                        .withAction(com.leanplum.custommessagetemplates.MessageTemplates.Args.CANCEL_ACTION, null)
                        // #### example: adding the Action for the Maybe button
                        .withAction(com.leanplum.custommessagetemplates.MessageTemplates.Args.MAYBE_ACTION, null), new ActionCallback() {

                    @Override
                    public boolean onResponse(final ActionContext context) {
                        LeanplumActivityHelper.queueActionUponActive(new VariablesChangedCallback() {
                            @Override
                            public void variablesChanged() {
                                Activity activity = LeanplumActivityHelper.getCurrentActivity();
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                        activity);
                                alertDialogBuilder
                                        .setTitle(context.stringNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.TITLE))
                                        .setMessage(context.stringNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.MESSAGE))
                                        .setCancelable(false)
                                        .setPositiveButton(context.stringNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.ACCEPT_TEXT),
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        context.runTrackedActionNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.ACCEPT_ACTION);
                                                    }
                                                })
                                        .setNegativeButton(context.stringNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.CANCEL_TEXT),
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        context.runActionNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.CANCEL_ACTION);
                                                    }
                                                })

                                        // #### example: adding the Maybe button to the Alert dialog
                                        .setNeutralButton(context.stringNamed(com.leanplum.custommessagetemplates.MessageTemplates.Args.MAYBE_TEXT),
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        context.runActionNamed(MessageTemplates.Args.MAYBE_ACTION);
                                                    }
                                                });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                        });
                        return true;
                    }
                });
    }

}
