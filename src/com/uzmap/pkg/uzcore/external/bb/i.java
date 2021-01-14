package com.uzmap.pkg.uzcore.external.bb;

import android.content.Context;
import android.os.Parcelable;
import android.widget.RelativeLayout;
import com.uzmap.pkg.uzcore.external.l;

public class i extends RelativeLayout {
   public i(Context context, Object o) {
      super(context);
   }

   public boolean post(Runnable action) {
      if (l.SDK_INT >= 16) {
         this.postOnAnimation(action);
         return true;
      } else {
         return super.post(action);
      }
   }

   public boolean postDelayed(Runnable action, long delayMillis) {
      if (l.SDK_INT >= 16) {
         this.postOnAnimationDelayed(action, delayMillis);
         return true;
      } else {
         return super.postDelayed(action, delayMillis);
      }
   }

   public Parcelable onSaveInstanceState() {
      return null;
   }

   public void onRestoreInstanceState(Parcelable state) {
   }
}
