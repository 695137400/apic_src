//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.aa;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.uzmap.pkg.uzcore.uzmodule.aa.k;
import org.json.JSONObject;

public class e {
    private SensorManager a;
    private com.uzmap.pkg.a.aa.e.a b;
    private com.uzmap.pkg.a.aa.e.b c;
    private com.uzmap.pkg.a.aa.e.c d;
    private com.uzmap.pkg.a.aa.e.f e;
    private com.uzmap.pkg.a.aa.e.d f;
    private com.uzmap.pkg.a.aa.e.ee g;

    public e() {
    }

    public boolean a(int type, int rate, k callbackContext) {
        if (rate < 0) {
            rate = 1;
        }

        if (this.a == null) {
            this.a = (SensorManager)com.uzmap.pkg.uzcore.b.a().b().getSystemService("sensor");
        }

        if (this.a == null) {
            return false;
        } else {
            switch(type) {
                case 0:
                    Sensor acc = this.a.getDefaultSensor(1);
                    if (acc == null) {
                        return false;
                    }

                    if (this.b != null) {
                        return true;
                    }

                    this.b = new com.uzmap.pkg.a.aa.e.a(callbackContext);
                    this.a.registerListener(this.b, acc, rate);
                    break;
                case 1:
                    Sensor ori = this.a.getDefaultSensor(4);
                    if (ori == null) {
                        return false;
                    }

                    if (this.c != null) {
                        return true;
                    }

                    this.c = new com.uzmap.pkg.a.aa.e.b(callbackContext);
                    this.a.registerListener(this.c, ori, rate);
                    break;
                case 2:
                    Sensor mag = this.a.getDefaultSensor(2);
                    if (mag == null) {
                        return false;
                    }

                    if (this.d != null) {
                        return true;
                    }

                    this.d = new com.uzmap.pkg.a.aa.e.c(callbackContext);
                    this.a.registerListener(this.d, mag, rate);
                    break;
                case 3:
                    Sensor tem = this.a.getDefaultSensor(8);
                    if (tem == null) {
                        return false;
                    }

                    if (this.e != null) {
                        return true;
                    }

                    this.e = new com.uzmap.pkg.a.aa.e.f(callbackContext);
                    this.a.registerListener(this.e, tem, rate);
                    break;
                case 4:
                    Sensor orien = this.a.getDefaultSensor(1);
                    if (orien == null) {
                        return false;
                    }

                    Sensor field = this.a.getDefaultSensor(2);
                    if (field == null) {
                        return false;
                    }

                    if (this.f != null) {
                        return true;
                    }

                    this.f = new com.uzmap.pkg.a.aa.e.d(callbackContext);
                    this.a.registerListener(this.f, orien, rate);
                    this.a.registerListener(this.f, field, rate);
                    break;
                case 5:
                    Sensor pre = this.a.getDefaultSensor(6);
                    if (pre == null) {
                        return false;
                    }

                    if (this.g != null) {
                        return true;
                    }

                    this.g = new com.uzmap.pkg.a.aa.e.ee(callbackContext);
                    this.a.registerListener(this.g, pre, 3);
                    break;
                default:
                    return false;
            }

            return true;
        }
    }

    public void a(int type) {
        k callbackContext = null;
        switch(type) {
            case 0:
                if (this.b != null) {
                    this.a.unregisterListener(this.b);
                    callbackContext = this.b.a();
                    this.b = null;
                }
                break;
            case 1:
                if (this.c != null) {
                    this.a.unregisterListener(this.c);
                    callbackContext = this.c.a();
                    this.c = null;
                }
                break;
            case 2:
                if (this.d != null) {
                    this.a.unregisterListener(this.d);
                    callbackContext = this.d.a();
                    this.d = null;
                }
                break;
            case 3:
                if (this.e != null) {
                    this.a.unregisterListener(this.e);
                    callbackContext = this.e.a();
                    this.e = null;
                }
                break;
            case 4:
                if (this.f != null) {
                    this.a.unregisterListener(this.f);
                    callbackContext = this.f.a();
                    this.f = null;
                }
                break;
            case 5:
                if (this.g != null) {
                    this.a.unregisterListener(this.g);
                    callbackContext = this.g.a();
                    this.g = null;
                }
        }

        if (callbackContext != null) {
            callbackContext.interrupt();
        }

    }

    public void a() {
        if (this.a != null) {
            this.a.unregisterListener(this.b);
            this.a.unregisterListener(this.c);
            this.a.unregisterListener(this.d);
            this.a.unregisterListener(this.e);
            this.a.unregisterListener(this.g);
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.g = null;
        }

    }

    private class a implements SensorEventListener {
        private k b;

        public a(k callbackContext) {
            this.b = callbackContext;
        }

        public k a() {
            return this.b;
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0] * -1.0F;
            float y = event.values[1] * -1.0F;
            float z = event.values[2] * -1.0F;
            JSONObject json = new JSONObject();

            try {
                json.put("x", (double)x);
                json.put("y", (double)y);
                json.put("z", (double)z);
                json.put("proximity", false);
                json.put("status", true);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            this.b.success(json, false);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class b implements SensorEventListener {
        private k b;

        public b(k callbackContext) {
            this.b = callbackContext;
        }

        public k a() {
            return this.b;
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0] * -1.0F;
            float y = event.values[1] * -1.0F;
            float z = event.values[2] * -1.0F;
            JSONObject json = new JSONObject();

            try {
                json.put("x", (double)x);
                json.put("y", (double)y);
                json.put("z", (double)z);
                json.put("proximity", false);
                json.put("status", true);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            this.b.success(json, false);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class c implements SensorEventListener {
        private k b;

        public c(k callbackContext) {
            this.b = callbackContext;
        }

        public k a() {
            return this.b;
        }

        public void onSensorChanged(SensorEvent event) {
            JSONObject json = new JSONObject();

            try {
                json.put("x", (double)event.values[0]);
                json.put("y", (double)event.values[1]);
                json.put("z", (double)event.values[2]);
                json.put("proximity", false);
                json.put("status", true);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            this.b.success(json, false);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class d implements SensorEventListener {
        private k b;
        private float[] c = new float[3];
        private float[] d = new float[3];
        private float[] e = new float[3];
        private float[] f = new float[9];

        public d(k callbackContext) {
            this.b = callbackContext;
        }

        public k a() {
            return this.b;
        }

        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == 1) {
                this.c = event.values;
            }

            if (event.sensor.getType() == 2) {
                this.d = event.values;
            }

            SensorManager.getRotationMatrix(this.f, (float[])null, this.c, this.d);
            SensorManager.getOrientation(this.f, this.e);
            this.e[0] = (float)Math.toDegrees((double)this.e[0]);
            JSONObject json = new JSONObject();

            try {
                json.put("x", (double)(this.e[0] * -1.0F));
                json.put("y", (double)(this.e[1] * -1.0F));
                json.put("z", (double)(this.e[2] * -1.0F));
                json.put("proximity", false);
                json.put("status", true);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            this.b.success(json, false);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class ee implements SensorEventListener {
        private k b;

        public ee(k callbackContext) {
            this.b = callbackContext;
        }

        public k a() {
            return this.b;
        }

        public void onSensorChanged(SensorEvent event) {
            JSONObject json = new JSONObject();

            try {
                json.put("x", (double)event.values[0]);
                json.put("y", (double)event.values[1]);
                json.put("z", (double)event.values[2]);
                json.put("proximity", false);
                json.put("status", true);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            this.b.success(json, false);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    private class f implements SensorEventListener {
        private k b;

        public f(k callbackContext) {
            this.b = callbackContext;
        }

        public k a() {
            return this.b;
        }

        public void onSensorChanged(SensorEvent event) {
            float distance = event.values[0];
            boolean proximity = distance < 1.0F;
            JSONObject json = new JSONObject();

            try {
                json.put("x", 0);
                json.put("y", 0);
                json.put("z", 0);
                json.put("proximity", proximity);
                json.put("status", true);
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            this.b.success(json, false);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
