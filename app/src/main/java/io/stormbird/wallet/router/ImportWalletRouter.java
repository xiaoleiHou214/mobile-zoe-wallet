package io.stormbird.wallet.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io.stormbird.wallet.ui.ImportWalletActivity;

public class ImportWalletRouter {

	public void openForResult(Activity activity, int requestCode) {
		Intent intent = new Intent(activity, ImportWalletActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
		activity.startActivityForResult(intent, requestCode);
	}
}
