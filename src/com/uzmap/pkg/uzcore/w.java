package com.uzmap.pkg.uzcore;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class w {
    public static v a(int animType, int subAnimTypeType, long animDuration) {
        if (animDuration <= 0L) {
            animDuration = 300L;
        }

        v winAnimPair = null;
        switch (animType) {
            case 0:
                winAnimPair = b(subAnimTypeType);
                break;
            case 1:
                winAnimPair = c(subAnimTypeType);
                break;
            case 2:
                winAnimPair = d(subAnimTypeType);
                break;
            case 3:
                winAnimPair = e(subAnimTypeType);
                break;
            case 4:
                winAnimPair = f(subAnimTypeType);
                break;
            case 5:
                winAnimPair = g(subAnimTypeType);
                break;
            case 6:
                winAnimPair = h(subAnimTypeType);
                break;
            case 7:
                winAnimPair = i(subAnimTypeType);
                break;
            case 8:
                winAnimPair = j(subAnimTypeType);
                break;
            case 9:
                winAnimPair = k(subAnimTypeType);
        }

        if (winAnimPair == null) {
            Animation defaultInAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
            Animation defaultOutAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
            winAnimPair = new v(defaultInAnim, defaultOutAnim);
            animDuration = 200L;
        }

        winAnimPair.a();
        winAnimPair.a(animDuration);
        return winAnimPair;
    }

    private static int a(int animType) {
        return animType + 999;
    }

    public static int a(int oldAnimType, int newAnimType) {
        return -1 == newAnimType ? a(oldAnimType) : newAnimType;
    }

    private static v b(int subAnimType) {
        v winAnimPair = null;
        TranslateAnimation inAnim;
        TranslateAnimation outAnim;
        switch (subAnimType) {
            case 0:
                inAnim = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 1:
                inAnim = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 2:
                inAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, -1.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 3:
                inAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 999:
                inAnim = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 1000:
                inAnim = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 1001:
                inAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 1002:
                inAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, -1.0F, 2, 0.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
        }

        return winAnimPair;
    }

    private static v c(int subAnimTypeType) {
        v winAnimPair = null;
        TranslateAnimation inAnim;
        ScaleAnimation outAnim;
        switch (subAnimTypeType) {
            case 0:
                inAnim = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
                outAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 1:
                inAnim = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
                outAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 2:
                inAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, -1.0F, 2, 0.0F);
                outAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 3:
                inAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
                outAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 999:
                inAnim = new TranslateAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new ScaleAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1000:
                inAnim = new TranslateAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new ScaleAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1001:
                inAnim = new TranslateAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new ScaleAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1002:
                inAnim = new TranslateAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new ScaleAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
        }

        return winAnimPair;
    }

    private static v d(int subAnimType) {
        v winAnimPair = null;
        AlphaAnimation inAnim;
        AlphaAnimation outAnim;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
                inAnim = new AlphaAnimation(0.2F, 1.0F);
                outAnim = new AlphaAnimation(1.0F, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                break;
            case 999:
            case 1000:
            case 1001:
            case 1002:
                inAnim = new AlphaAnimation(0.2F, 1.0F);
                outAnim = new AlphaAnimation(1.0F, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
        }

        return winAnimPair;
    }

    private static v e(int subAnimType) {
        v winAnimPair = null;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            default:
                return winAnimPair;
        }
    }

    private static v f(int subAnimType) {
        v winAnimPair = null;
        ScaleAnimation inAnim;
        TranslateAnimation outAnim;
        switch (subAnimType) {
            case 0:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 2:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 3:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 999:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, -0.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1000:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1001:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, -1.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
                break;
            case 1002:
                inAnim = new ScaleAnimation(1.0F, 1.0F, 1.0F, 1.0F);
                outAnim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
                winAnimPair = new v(inAnim, outAnim);
                winAnimPair.c();
        }

        return winAnimPair;
    }

    private static v g(int subAnimType) {
        v winAnimPair = null;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            default:
                return winAnimPair;
        }
    }

    private static v h(int subAnimType) {
        v winAnimPair = null;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            default:
                return winAnimPair;
        }
    }

    private static v i(int subAnimType) {
        v winAnimPair = null;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            default:
                return winAnimPair;
        }
    }

    private static v j(int subAnimType) {
        v winAnimPair = null;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            default:
                return winAnimPair;
        }
    }

    private static v k(int subAnimType) {
        v winAnimPair = null;
        switch (subAnimType) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 999:
            case 1000:
            case 1001:
            case 1002:
            default:
                return winAnimPair;
        }
    }
}
