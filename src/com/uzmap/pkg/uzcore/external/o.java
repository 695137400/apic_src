package com.uzmap.pkg.uzcore.external;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import org.json.JSONException;
import org.json.JSONObject;

public class o implements OnCancelListener {
    protected int a;
    protected com.uzmap.pkg.uzcore.uzmodule.aa.c b;

    public o(com.uzmap.pkg.uzcore.uzmodule.aa.c moduleContext) {
        this.b = moduleContext;
        this.a = this.b.f;
    }

    public void a() {
        Builder alert = new Builder(this.b.getContext());
        alert.setTitle(this.b.b);
        alert.setMessage(this.b.d);
        alert.setCancelable(false);
        alert.setOnCancelListener(this);
        alert.setPositiveButton(this.b.e[0], new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                o.this.a(1, null);
            }
        });
        alert.show();
    }

    public void b() {
        Builder confirm = new Builder(this.b.getContext());
        confirm.setMessage(this.b.d);
        confirm.setTitle(this.b.b);
        confirm.setCancelable(true);
        confirm.setOnCancelListener(this);
        String[] buttons = this.b.e;
        if (l.SDK_INT > 10) {
            confirm.setNegativeButton(buttons[0], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(1, null);
                }
            });
            confirm.setNeutralButton(buttons[1], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(2, null);
                }
            });
            if (buttons.length >= 3) {
                confirm.setPositiveButton(buttons[2], new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        o.this.a(3, null);
                    }
                });
            }
        } else {
            confirm.setPositiveButton(buttons[0], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(1, null);
                }
            });
            confirm.setNeutralButton(buttons[1], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(2, null);
                }
            });
            if (buttons.length >= 3) {
                confirm.setNegativeButton(buttons[2], new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        o.this.a(3, null);
                    }
                });
            }
        }

        confirm.show();
    }

    public void c() {
        Builder prompt = new Builder(this.b.getContext());
        prompt.setTitle(this.b.b);
        prompt.setMessage(this.b.d);
        final EditText input = new EditText(this.b.getContext());
        if (this.b.c != null) {
            input.setText(this.b.c);
        }

        input.setInputType(this.b.g);
        input.setSelectAllOnFocus(true);
        String[] buttons = this.b.e;
        prompt.setView(input);
        prompt.setCancelable(true);
        prompt.setOnCancelListener(this);
        if (l.SDK_INT > 10) {
            prompt.setNegativeButton(buttons[0], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(1, input.getText().toString());
                }
            });
            prompt.setNeutralButton(buttons[1], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(2, input.getText().toString());
                }
            });
            if (buttons.length >= 3) {
                prompt.setPositiveButton(buttons[2], new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        o.this.a(3, input.getText().toString());
                    }
                });
            }
        } else {
            prompt.setPositiveButton(buttons[0], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(1, input.getText().toString());
                }
            });
            prompt.setNeutralButton(buttons[1], new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    o.this.a(2, input.getText().toString());
                }
            });
            if (buttons.length >= 3) {
                prompt.setNegativeButton(buttons[2], new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        o.this.a(3, input.getText().toString());
                    }
                });
            }
        }

        prompt.show();
    }

    private void a(int index, String text) {
        JSONObject json = new JSONObject();

        try {
            json.put("buttonIndex", index);
            if (this.a == 2) {
                json.put("text", text != null ? text : "");
            }
        } catch (JSONException var5) {
            var5.printStackTrace();
        }

        this.b.success(json, true);
    }

    public void onCancel(DialogInterface dialog) {
        this.a(0, null);
    }
}
