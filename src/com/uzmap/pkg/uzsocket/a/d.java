package com.uzmap.pkg.uzsocket.a;

import com.uzmap.pkg.uzsocket.c.g;
import com.uzmap.pkg.uzsocket.e.f;
import com.uzmap.pkg.uzsocket.e.h;
import com.uzmap.pkg.uzsocket.e.i;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class d implements com.uzmap.pkg.uzsocket.a.a {
   public static final List<com.uzmap.pkg.uzsocket.b.a> b = new ArrayList(4);
   public static int a = 16384;

   static {
      b.add(new com.uzmap.pkg.uzsocket.b.c());
      b.add(new com.uzmap.pkg.uzsocket.b.b());
      b.add(new com.uzmap.pkg.uzsocket.b.e());
      b.add(new com.uzmap.pkg.uzsocket.b.d());
   }

   public final BlockingQueue<ByteBuffer> e;
   public final BlockingQueue<ByteBuffer> f;
   private final e j;
   public SelectionKey c;
   public ByteChannel d;
   private volatile boolean h = false;
   private aemnu i;
   private List<com.uzmap.pkg.uzsocket.b.a> k;
   private com.uzmap.pkg.uzsocket.b.a l;
   private com.uzmap.pkg.uzsocket.a.a.b m;
   private com.uzmap.pkg.uzsocket.d.d.aemnu n;
   private ByteBuffer o;
   private com.uzmap.pkg.uzsocket.e.a p;
   private String q;
   private Integer r;
   private Boolean s;
   private String t;

   public d(e listener, com.uzmap.pkg.uzsocket.b.a draft) {
      this.i = aemnu.a;
      this.o = ByteBuffer.allocate(0);
      if (listener != null && (draft != null || this.m != com.uzmap.pkg.uzsocket.a.a.b.b)) {
         this.e = new LinkedBlockingQueue();
         this.f = new LinkedBlockingQueue();
         this.j = listener;
         this.m = com.uzmap.pkg.uzsocket.a.a.b.a;
         if (draft != null) {
            this.l = draft.c();
         }

      } else {
         throw new IllegalArgumentException("parameters must not be null");
      }
   }

   public void a(ByteBuffer socketBuffer) {
      assert socketBuffer.hasRemaining();

      if (this.i != aemnu.a) {
         this.c(socketBuffer);
      } else if (this.b(socketBuffer)) {
         assert this.o.hasRemaining() != socketBuffer.hasRemaining() || !socketBuffer.hasRemaining();

         if (socketBuffer.hasRemaining()) {
            this.c(socketBuffer);
         } else if (this.o.hasRemaining()) {
            this.c(this.o);
         }
      }

      assert this.d() || this.e() || !socketBuffer.hasRemaining();

   }

   private boolean b(ByteBuffer socketBufferNew) {
      ByteBuffer socketBuffer;
      ByteBuffer buf;
      if (this.o.capacity() == 0) {
         socketBuffer = socketBufferNew;
      } else {
         if (this.o.remaining() < socketBufferNew.remaining()) {
            buf = ByteBuffer.allocate(this.o.capacity() + socketBufferNew.remaining());
            this.o.flip();
            buf.put(this.o);
            this.o = buf;
         }

         this.o.put(socketBufferNew);
         this.o.flip();
         socketBuffer = this.o;
      }

      socketBuffer.mark();

      try {
         com.uzmap.pkg.uzsocket.b.a.bemnu handshakestate;
         if (this.l == null) {
            handshakestate = this.d(socketBuffer);
            if (handshakestate == com.uzmap.pkg.uzsocket.b.a.bemnu.a) {
               try {
                  this.e(ByteBuffer.wrap(com.uzmap.pkg.uzsocket.h.b.a(this.j.a(this))));
                  this.a(-3, "");
               } catch (com.uzmap.pkg.uzsocket.c.b var15) {
                  this.c(1006, "remote peer closed connection before flashpolicy could be transmitted", true);
               }

               return false;
            }
         }

         buf = null;

         try {
            f tmphandshake;
            if (this.m == com.uzmap.pkg.uzsocket.a.a.b.b) {
               if (this.l != null) {
                  tmphandshake = this.l.d(socketBuffer);
                  if (!(tmphandshake instanceof com.uzmap.pkg.uzsocket.e.a)) {
                     this.b(1002, "wrong http function", false);
                     return false;
                  }

                  com.uzmap.pkg.uzsocket.e.a handshake = (com.uzmap.pkg.uzsocket.e.a) tmphandshake;
                  handshakestate = this.l.a(handshake);
                  if (handshakestate == com.uzmap.pkg.uzsocket.b.a.bemnu.a) {
                     this.a(handshake);
                     return true;
                  }

                  this.a(1002, "the handshake did finaly not match");
                  return false;
               }

               Iterator var20 = this.k.iterator();

               while (var20.hasNext()) {
                  com.uzmap.pkg.uzsocket.b.a d = (com.uzmap.pkg.uzsocket.b.a) var20.next();
                  d = d.c();

                  try {
                     d.a(this.m);
                     socketBuffer.reset();
                     tmphandshake = d.d(socketBuffer);
                     if (!(tmphandshake instanceof com.uzmap.pkg.uzsocket.e.a)) {
                        this.b(1002, "wrong http function", false);
                        return false;
                     }

                     com.uzmap.pkg.uzsocket.e.a handshake = (com.uzmap.pkg.uzsocket.e.a) tmphandshake;
                     handshakestate = d.a(handshake);
                     if (handshakestate == com.uzmap.pkg.uzsocket.b.a.bemnu.a) {
                        this.t = handshake.a();

                        i response;
                        try {
                           response = this.j.a(this, d, handshake);
                        } catch (com.uzmap.pkg.uzsocket.c.b var12) {
                           this.b(var12.a(), var12.getMessage(), false);
                           return false;
                        } catch (RuntimeException var13) {
                           this.j.a(this, var13);
                           this.b(-1, var13.getMessage(), false);
                           return false;
                        }

                        this.a(d.a(d.a(handshake, response), this.m));
                        this.l = d;
                        this.a(handshake);
                        return true;
                     }
                  } catch (com.uzmap.pkg.uzsocket.c.d var14) {
                  }
               }

               if (this.l == null) {
                  this.a(1002, "no draft matches");
               }

               return false;
            }

            if (this.m == com.uzmap.pkg.uzsocket.a.a.b.a) {
               this.l.a(this.m);
               tmphandshake = this.l.d(socketBuffer);
               if (!(tmphandshake instanceof h)) {
                  this.b(1002, "wrong http function", false);
                  return false;
               }

               h handshake = (h) tmphandshake;
               handshakestate = this.l.a(this.p, handshake);
               if (handshakestate == com.uzmap.pkg.uzsocket.b.a.bemnu.a) {
                  try {
                     this.j.a(this, this.p, handshake);
                  } catch (com.uzmap.pkg.uzsocket.c.b var10) {
                     this.b(var10.a(), var10.getMessage(), false);
                     return false;
                  } catch (RuntimeException var11) {
                     this.j.a(this, var11);
                     this.b(-1, var11.getMessage(), false);
                     return false;
                  }

                  this.a(handshake);
                  return true;
               }

               this.a(1002, "draft " + this.l + " refuses handshake");
            }
         } catch (com.uzmap.pkg.uzsocket.c.d var16) {
            this.a(var16);
         }
      } catch (com.uzmap.pkg.uzsocket.c.a var17) {
         if (this.o.capacity() == 0) {
            socketBuffer.reset();
            int newsize = var17.a();
            if (newsize == 0) {
               newsize = socketBuffer.capacity() + 16;
            } else {
               assert var17.a() >= socketBuffer.remaining();
            }

            this.o = ByteBuffer.allocate(newsize);
            this.o.put(socketBufferNew);
         } else {
            this.o.position(this.o.limit());
            this.o.limit(this.o.capacity());
         }
      }

      return false;
   }

   private void c(ByteBuffer socketBuffer) {
      try {
         List<com.uzmap.pkg.uzsocket.d.d> frames = this.l.c(socketBuffer);
         Iterator var4 = frames.iterator();

         while (true) {
            while (var4.hasNext()) {
               com.uzmap.pkg.uzsocket.d.d f = (com.uzmap.pkg.uzsocket.d.d) var4.next();
               com.uzmap.pkg.uzsocket.d.d.aemnu curop = f.f();
               boolean fin = f.d();
               if (curop == com.uzmap.pkg.uzsocket.d.d.aemnu.f) {
                  int code = 1005;
                  String reason = "";
                  if (f instanceof com.uzmap.pkg.uzsocket.d.a) {
                     com.uzmap.pkg.uzsocket.d.a cf = (com.uzmap.pkg.uzsocket.d.a) f;
                     code = cf.a();
                     reason = cf.b();
                  }

                  if (this.i == aemnu.d) {
                     this.a(code, reason, true);
                  } else if (this.l.b() == com.uzmap.pkg.uzsocket.b.a.aemnu.c) {
                     this.c(code, reason, true);
                  } else {
                     this.b(code, reason, false);
                  }
               } else if (curop == com.uzmap.pkg.uzsocket.d.d.aemnu.d) {
                  this.j.b(this, f);
               } else if (curop == com.uzmap.pkg.uzsocket.d.d.aemnu.e) {
                  this.j.c(this, f);
               } else if (fin && curop != com.uzmap.pkg.uzsocket.d.d.aemnu.a) {
                  if (this.n != null) {
                     throw new com.uzmap.pkg.uzsocket.c.b(1002, "Continuous frame sequence not completed.");
                  }

                  if (curop == com.uzmap.pkg.uzsocket.d.d.aemnu.b) {
                     try {
                        this.j.a(this, com.uzmap.pkg.uzsocket.h.b.a(f.c()));
                     } catch (RuntimeException var11) {
                        this.j.a(this, var11);
                     }
                  } else {
                     if (curop != com.uzmap.pkg.uzsocket.d.d.aemnu.c) {
                        throw new com.uzmap.pkg.uzsocket.c.b(1002, "non control or continious frame expected");
                     }

                     try {
                        this.j.a(this, f.c());
                     } catch (RuntimeException var10) {
                        this.j.a(this, var10);
                     }
                  }
               } else {
                  if (curop != com.uzmap.pkg.uzsocket.d.d.aemnu.a) {
                     if (this.n != null) {
                        throw new com.uzmap.pkg.uzsocket.c.b(1002, "Previous continuous frame sequence not completed.");
                     }

                     this.n = curop;
                  } else if (fin) {
                     if (this.n == null) {
                        throw new com.uzmap.pkg.uzsocket.c.b(1002, "Continuous frame sequence was not started.");
                     }

                     this.n = null;
                  } else if (this.n == null) {
                     throw new com.uzmap.pkg.uzsocket.c.b(1002, "Continuous frame sequence was not started.");
                  }

                  try {
                     this.j.a(this, f);
                  } catch (RuntimeException var12) {
                     this.j.a(this, var12);
                  }
               }
            }

            return;
         }
      } catch (com.uzmap.pkg.uzsocket.c.b var13) {
         this.j.a(this, var13);
         this.a(var13);
      }
   }

   private void c(int code, String message, boolean remote) {
      if (this.i != aemnu.d && this.i != aemnu.e) {
         if (this.i == aemnu.c) {
            if (code == 1006) {
               assert !remote;

               this.i = aemnu.d;
               this.b(code, message, false);
               return;
            }

            if (this.l.b() != com.uzmap.pkg.uzsocket.b.a.aemnu.a) {
               try {
                  if (!remote) {
                     try {
                        this.j.a(this, code, message);
                     } catch (RuntimeException var5) {
                        this.j.a(this, var5);
                     }
                  }

                  this.a(new com.uzmap.pkg.uzsocket.d.b(code, message));
               } catch (com.uzmap.pkg.uzsocket.c.b var6) {
                  this.j.a(this, var6);
                  this.b(1006, "generated frame is invalid", false);
               }
            }

            this.b(code, message, remote);
         } else if (code == -3) {
            assert remote;

            this.b(-3, message, true);
         } else {
            this.b(-1, message, false);
         }

         if (code == 1002) {
            this.b(code, message, remote);
         }

         this.i = aemnu.d;
         this.o = null;
      }
   }

   public void a(int code, String message) {
      this.c(code, message, false);
   }

   protected synchronized void a(int code, String message, boolean remote) {
      if (this.i != aemnu.e) {
         if (this.c != null) {
            this.c.cancel();
         }

         if (this.d != null) {
            try {
               this.d.close();
            } catch (IOException var6) {
               this.j.a(this, var6);
            }
         }

         try {
            this.j.a(this, code, message, remote);
         } catch (RuntimeException var5) {
            this.j.a(this, var5);
         }

         if (this.l != null) {
            this.l.a();
         }

         this.p = null;
         this.i = aemnu.e;
         this.e.clear();
      }
   }

   protected void a(int code, boolean remote) {
      this.a(code, "", remote);
   }

   public void b(int code, String message) {
      this.a(code, message, false);
   }

   protected synchronized void b(int code, String message, boolean remote) {
      if (!this.h) {
         this.r = code;
         this.q = message;
         this.s = remote;
         this.h = true;
         this.j.b(this);

         try {
            this.j.b(this, code, message, remote);
         } catch (RuntimeException var5) {
            this.j.a(this, var5);
         }

         if (this.l != null) {
            this.l.a();
         }

         this.p = null;
      }
   }

   public void b() {
      if (this.g() == aemnu.a) {
         this.a(-1, true);
      } else if (this.h) {
         this.a(this.r, this.q, this.s);
      } else if (this.l.b() == com.uzmap.pkg.uzsocket.b.a.aemnu.a) {
         this.a(1000, true);
      } else if (this.l.b() == com.uzmap.pkg.uzsocket.b.a.aemnu.b) {
         if (this.m == com.uzmap.pkg.uzsocket.a.a.b.b) {
            this.a(1006, true);
         } else {
            this.a(1000, true);
         }
      } else {
         this.a(1006, true);
      }

   }

   public void a(int code) {
      this.c(code, "", false);
   }

   public void a(com.uzmap.pkg.uzsocket.c.b e) {
      this.c(e.a(), e.getMessage(), false);
   }

   public void a(String text) throws g {
      if (text == null) {
         throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
      } else {
         this.a(this.l.a(text, this.m == com.uzmap.pkg.uzsocket.a.a.b.a));
      }
   }

   private void a(Collection<com.uzmap.pkg.uzsocket.d.d> frames) {
      if (!this.c()) {
         throw new g();
      } else {
         Iterator var3 = frames.iterator();

         while (var3.hasNext()) {
            com.uzmap.pkg.uzsocket.d.d f = (com.uzmap.pkg.uzsocket.d.d) var3.next();
            this.a(f);
         }

      }
   }

   public void a(com.uzmap.pkg.uzsocket.d.d framedata) {
      this.e(this.l.a(framedata));
   }

   private com.uzmap.pkg.uzsocket.b.a.bemnu d(ByteBuffer request) throws com.uzmap.pkg.uzsocket.c.a {
      request.mark();
      if (request.limit() > com.uzmap.pkg.uzsocket.b.a.c.length) {
         return com.uzmap.pkg.uzsocket.b.a.bemnu.b;
      } else if (request.limit() < com.uzmap.pkg.uzsocket.b.a.c.length) {
         throw new com.uzmap.pkg.uzsocket.c.a(com.uzmap.pkg.uzsocket.b.a.c.length);
      } else {
         for (int flash_policy_index = 0; request.hasRemaining(); ++flash_policy_index) {
            if (com.uzmap.pkg.uzsocket.b.a.c[flash_policy_index] != request.get()) {
               request.reset();
               return com.uzmap.pkg.uzsocket.b.a.bemnu.b;
            }
         }

         return com.uzmap.pkg.uzsocket.b.a.bemnu.a;
      }
   }

   public void a(com.uzmap.pkg.uzsocket.e.b handshakedata) throws com.uzmap.pkg.uzsocket.c.d {
      assert this.i != aemnu.b : "shall only be called once";

      this.p = this.l.a(handshakedata);
      this.t = handshakedata.a();

      assert this.t != null;

      try {
         this.j.a(this, this.p);
      } catch (com.uzmap.pkg.uzsocket.c.b var3) {
         throw new com.uzmap.pkg.uzsocket.c.d("Handshake data rejected by client.");
      } catch (RuntimeException var4) {
         this.j.a(this, var4);
         throw new com.uzmap.pkg.uzsocket.c.d("rejected because of" + var4);
      }

      this.a(this.l.a(this.p, this.m));
   }

   private void e(ByteBuffer buf) {
      com.uzmap.pkg.uzsocket.g.f.a("-------- Websoket Engine Write --------\nlength: " + buf.remaining() + " , \ncontent: " + (buf.remaining() > 1000 ? "too big to display" : new String(buf.array())));
      this.e.add(buf);
      this.j.b(this);
   }

   private void a(List<ByteBuffer> bufs) {
      Iterator var3 = bufs.iterator();

      while (var3.hasNext()) {
         ByteBuffer b = (ByteBuffer) var3.next();
         this.e(b);
      }

   }

   private void a(f d) {
      com.uzmap.pkg.uzsocket.g.f.a("-------- Websoket Engine Open --------\nusing draft: " + this.l.getClass().getSimpleName());
      this.i = aemnu.c;

      try {
         this.j.a(this, d);
      } catch (RuntimeException var3) {
         this.j.a(this, var3);
      }

   }

   public boolean c() {
      assert this.i != aemnu.c || !this.h;

      return this.i == aemnu.c;
   }

   public boolean d() {
      return this.i == aemnu.d;
   }

   public boolean e() {
      return this.h;
   }

   public boolean f() {
      return this.i == aemnu.e;
   }

   public aemnu g() {
      return this.i;
   }

   public int hashCode() {
      return super.hashCode();
   }

   public String toString() {
      return super.toString();
   }

   public InetSocketAddress a() {
      return this.j.c(this);
   }
}
