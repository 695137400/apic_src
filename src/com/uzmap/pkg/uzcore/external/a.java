package com.uzmap.pkg.uzcore.external;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.uzmap.pkg.uzcore.UZCoreUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class a extends Dialog implements OnShowListener, OnItemClickListener {
    protected String b;
    protected String c;
    protected int d = 1275068416;
    protected int e = -921103;
    protected int f = -1644826;
    protected int g = -16745729;
    protected int h = -16752400;
    protected int i = -7368817;
    private com.uzmap.pkg.uzcore.external.a.bRelativeLayout a;
    private List<String> j;
    private com.uzmap.pkg.uzcore.external.a.cBaseAdapter k;
    private boolean l;
    private final Context m;

    public a(Context context, Object o) {
        super(context, 16973835);
        this.m = context;
        this.requestWindowFeature(1);
        Window window = this.getWindow();
        window.setBackgroundDrawableResource(17170445);
        window.setGravity(87);
        if (com.uzmap.pkg.uzcore.external.l.a >= 14) {
            window.setDimAmount(0.4F);
        }

        this.setOnShowListener(this);
        this.setCanceledOnTouchOutside(false);
    }

    public abstract void a(JSONObject var1);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.a, com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.e));
    }

    public void a(JSONObject style, JSONObject data) {
        if (style != null) {
            String lcolor = style.optString("layerColor", null);
            String incolor = style.optString("itemNormalColor", null);
            String ipcolor = style.optString("itemPressColor", null);
            String fncolor = style.optString("fontNormalColor", null);
            String fpcolor = style.optString("fontPressColor", null);
            String tfcolor = style.optString("titleFontColor", null);
            if (lcolor != null) {
                this.a(UZCoreUtil.parseCssColor(lcolor));
            }

            if (incolor != null) {
                this.b(UZCoreUtil.parseCssColor(incolor));
            }

            if (ipcolor != null) {
                this.c(UZCoreUtil.parseCssColor(ipcolor));
            }

            if (fncolor != null) {
                this.d(UZCoreUtil.parseCssColor(fncolor));
            }

            if (fpcolor != null) {
                this.e(UZCoreUtil.parseCssColor(fpcolor));
            }

            if (tfcolor != null) {
                this.f(UZCoreUtil.parseCssColor(tfcolor));
            }
        }

        this.k = new com.uzmap.pkg.uzcore.external.a.cBaseAdapter();
        this.a = new com.uzmap.pkg.uzcore.external.a.bRelativeLayout(this.m);
        this.b(data);
        this.a.setVisibility(4);
        com.uzmap.pkg.uzcore.external.l.a(this.a, 2);
        this.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                onItemClick(null, null, j != null ? j.size() : 0, 0L);
            }
        });
        this.a.a(this);
    }

    private void b(JSONObject data) {
        if (data != null) {
            this.j = new ArrayList();
            String title = data.optString("title");
            String cancelTitle = data.optString("cancelTitle");
            String destructiveTitle = data.optString("destructiveTitle");
            this.a(title);
            this.b(destructiveTitle);
            this.c(cancelTitle);
            JSONArray items = data.optJSONArray("buttons");
            if (items != null) {
                int l = items.length();
                String[] buttons = new String[l];

                for (int i = 0; i < l; ++i) {
                    buttons[i] = items.optString(i);
                }

                this.a(buttons);
            }

        }
    }

    public void a(int color) {
        this.d = color;
    }

    public void b(int color) {
        this.e = color;
    }

    public void c(int color) {
        this.f = color;
    }

    public void d(int color) {
        this.g = color;
    }

    public void e(int color) {
        this.h = color;
    }

    public void f(int color) {
        this.i = color;
    }

    private boolean aBoolean() {
        return this.c != null;
    }

    private boolean b() {
        return this.b != null;
    }

    public void a(String title) {
        if (!TextUtils.isEmpty(title)) {
            this.b = title;
            this.j.add(title);
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c = str;
            this.j.add(str);
        }
    }

    public void a(String[] buttons) {
        if (buttons != null) {
            String[] var5 = buttons;
            int var4 = buttons.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                String button = var5[var3];
                this.j.add(button);
            }

            this.k.notifyDataSetChanged();
        }

    }

    public void c(String t) {
        this.a.a(t);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == 4 || super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            if (this.l) {
                return true;
            }

            this.onItemClick(null, null, this.j != null ? this.j.size() : 0, 0L);
        }

        return super.onKeyUp(keyCode, event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.l) {
            return true;
        } else {
            this.onItemClick(null, null, this.j != null ? this.j.size() : 0, 0L);
            return true;
        }
    }

    public void show() {
        this.l = true;
        super.show();
        if (this.a != null) {
        }
    }

    public void onShow(DialogInterface dialog) {
        Animation anim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 1.0F, 2, 0.0F);
        anim.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                l = false;
            }
        });
        anim.setDuration(250L);
        anim.setInterpolator(new DecelerateInterpolator());
        this.a.setVisibility(0);
        this.a.startAnimation(anim);
    }

    private void c(final JSONObject json) {
        this.l = true;
        Animation anim = new TranslateAnimation(2, 0.0F, 2, 0.0F, 2, 0.0F, 2, 1.0F);
        anim.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                dismiss();
                l = false;
                Runnable action = new Runnable() {
                    public void run() {
                        a(json);
                    }
                };
                com.uzmap.pkg.uzcore.c.b(action);
            }
        });
        anim.setDuration(200L);
        anim.setInterpolator(new DecelerateInterpolator());
        this.a.startAnimation(anim);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!this.l) {
            if (position != 0 || !this.b()) {
                this.l = true;
                ++position;
                if (this.b()) {
                    --position;
                }

                JSONObject json = new JSONObject();

                try {
                    json.put("buttonIndex", position);
                } catch (Exception var8) {
                    var8.printStackTrace();
                }

                this.c(json);
            }
        }
    }

    private StateListDrawable stateListDrawable(boolean title, float tlRadius, float trRadius, float brRadius, float blRadius) {
        int[] normalShap = new int[]{this.e, this.e, this.e, this.e};
        int[] pressShap = new int[]{this.f, this.f, this.f, this.f};
        float[] radius = new float[]{tlRadius, tlRadius, trRadius, trRadius, brRadius, brRadius, blRadius, blRadius};
        GradientDrawable normalState = new GradientDrawable(Orientation.TOP_BOTTOM, normalShap);
        normalState.setCornerRadii(radius);
        GradientDrawable pressState = new GradientDrawable(Orientation.TOP_BOTTOM, pressShap);
        pressState.setCornerRadii(radius);
        StateListDrawable selector = new StateListDrawable();
        if (!title) {
            selector.addState(new int[]{16842919}, pressState);
        }

        selector.addState(new int[0], normalState);
        return selector;
    }

    private ColorStateList colorStateList(boolean destructivel) {
        int[][] states = new int[][]{{16842919, 16842910}, {16842910, 16842908}, {16842910}, {16842908}, {16842909}, new int[0]};
        int norColor = this.g;
        int preColor = this.h;
        if (destructivel) {
            norColor = -65536;
        }

        int[] color = new int[]{preColor, preColor, norColor, preColor, norColor, norColor};
        ColorStateList colorStateList = new ColorStateList(states, color);
        return colorStateList;
    }

    private class aTextView extends TextView {
        public aTextView(Context context) {
            super(context);
            this.setTextSize(17.0F);
            this.setTextColor(colorStateList(false));
            com.uzmap.pkg.uzcore.external.l.a(this, stateListDrawable(false, 6.0F, 6.0F, 6.0F, 6.0F));
        }
    }

    private class bRelativeLayout extends RelativeLayout {
        protected ListView a;
        protected com.uzmap.pkg.uzcore.external.a.aTextView b;

        public bRelativeLayout(Context context) {
            super(context);
            android.view.ViewGroup.LayoutParams glp = com.uzmap.pkg.uzcore.external.l.a(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.d);
            this.setLayoutParams(glp);
            this.a(context);
        }

        public void a(String str) {
            if (TextUtils.isEmpty(str)) {
                str = com.uzmap.pkg.uzcore.d.c;
            }

            this.b.setText(str);
        }

        public void a(OnItemClickListener listener) {
            this.a.setOnItemClickListener(listener);
        }

        private void a(Context context) {
            LinearLayout lLayout = new LinearLayout(context);
            lLayout.setOrientation(1);
            android.widget.RelativeLayout.LayoutParams rlp = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.e);
            rlp.addRule(13, -1);
            lLayout.setLayoutParams(rlp);
            this.addView(lLayout);
            LinearLayout listLayout = new LinearLayout(context);
            listLayout.setOrientation(1);
            int padding = UZCoreUtil.dipToPix(10);
            listLayout.setPadding(padding, 0, padding, padding);
            android.widget.LinearLayout.LayoutParams listlp = com.uzmap.pkg.uzcore.external.l.c(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.e);
            listLayout.setLayoutParams(listlp);
            lLayout.addView(listLayout);
            int tcolor = 0;
            this.a = new ListView(context);
            android.widget.LinearLayout.LayoutParams lvlp = com.uzmap.pkg.uzcore.external.l.c(com.uzmap.pkg.uzcore.external.l.d, com.uzmap.pkg.uzcore.external.l.e);
            lvlp.weight = 1.0F;
            this.a.setLayoutParams(lvlp);
            this.a.setBackgroundColor(tcolor);
            this.a.setFadingEdgeLength(1);
            this.a.setDescendantFocusability(393216);
            this.a.setCacheColorHint(tcolor);
            this.a.setDivider(new ColorDrawable(-3355444));
            this.a.setDividerHeight(UZCoreUtil.dipToPix(1));
            listLayout.addView(this.a);
            this.a.setSelector(new ColorDrawable(tcolor));
            this.a.setAdapter(k);
            this.b = new aTextView(context);
            this.b.setText("Cancel");
            this.b.setGravity(17);
            android.widget.LinearLayout.LayoutParams cblp = com.uzmap.pkg.uzcore.external.l.c(com.uzmap.pkg.uzcore.external.l.d, UZCoreUtil.dipToPix(46));
            int tbm = UZCoreUtil.dipToPix(10);
            cblp.topMargin = tbm;
            cblp.bottomMargin = tbm;
            this.b.setLayoutParams(cblp);
            listLayout.addView(this.b);
            this.b.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    onItemClick(null, null, j.size(), 0L);
                }
            });
        }
    }

    class cBaseAdapter extends BaseAdapter {
        public int getCount() {
            return j != null ? j.size() : 0;
        }

        public String a(int position) {
            return j.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            dRelativeLayout result = (dRelativeLayout) convertView;
            boolean titleItem = b() && position == 0;
            boolean destructivelItem = false;
            if (aBoolean()) {
                if (b()) {
                    destructivelItem = 1 == position;
                } else {
                    destructivelItem = position == 0;
                }
            }

            if (result == null) {
                result = new dRelativeLayout(getContext(), titleItem, destructivelItem);
            }

            String text = this.a(position);
            if (TextUtils.isEmpty(text)) {
                text = "";
            }

            result.a(text);
            result.a(titleItem, destructivelItem);
            StateListDrawable selector = null;
            if (position == 0) {
                selector = stateListDrawable(titleItem, 6.0F, 6.0F, 0.0F, 0.0F);
            } else if (position == this.getCount() - 1) {
                selector = stateListDrawable(titleItem, 0.0F, 0.0F, 6.0F, 6.0F);
            } else {
                selector = stateListDrawable(titleItem, 0.0F, 0.0F, 0.0F, 0.0F);
            }

            com.uzmap.pkg.uzcore.external.l.a(result, selector);
            if (titleItem) {
                result.setEnabled(false);
            }

            return result;
        }

        // $FF: synthetic method
        public Object getItem(int var1) {
            return this.a(var1);
        }
    }

    private class dRelativeLayout extends RelativeLayout {
        private final TextView b;

        public dRelativeLayout(Context context, boolean title, boolean destructivel) {
            super(context);
            this.b = new TextView(context);
            LayoutParams rlp = com.uzmap.pkg.uzcore.external.l.b(com.uzmap.pkg.uzcore.external.l.d, UZCoreUtil.dipToPix(34));
            rlp.addRule(13, -1);
            int lm = UZCoreUtil.dipToPix(15);
            int tm = UZCoreUtil.dipToPix(5);
            rlp.leftMargin = lm;
            rlp.topMargin = tm;
            rlp.rightMargin = lm;
            rlp.bottomMargin = tm;
            this.b.setLayoutParams(rlp);
            this.b.setGravity(17);
            this.b.setTextSize(16.0F);
            this.addView(this.b);
        }

        public void a(String text) {
            this.b.setText(text);
        }

        public void a(boolean title, boolean destructivel) {
            if (title) {
                this.b.setTextColor(i);
            } else {
                this.b.setTextColor(colorStateList(destructivel));
            }

        }
    }
}
