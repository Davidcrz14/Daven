package com.daven.videoplayer.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\bH\u0002J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\bJ\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u000eH\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\"\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0016J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/daven/videoplayer/service/AudioPlaybackService;", "Landroid/app/Service;", "()V", "binder", "Lcom/daven/videoplayer/service/AudioPlaybackService$AudioBinder;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "videoTitle", "", "createNotification", "Landroid/app/Notification;", "isPlaying", "", "createNotificationChannel", "", "createPendingIntent", "Landroid/app/PendingIntent;", "action", "getPlayer", "initializePlayer", "videoUri", "title", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "updateNotification", "AudioBinder", "Companion", "app_debug"})
public final class AudioPlaybackService extends android.app.Service {
    @org.jetbrains.annotations.Nullable
    private androidx.media3.exoplayer.ExoPlayer player;
    @org.jetbrains.annotations.NotNull
    private final com.daven.videoplayer.service.AudioPlaybackService.AudioBinder binder = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String videoTitle = "DAVEN";
    private static final int NOTIFICATION_ID = 1;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String CHANNEL_ID = "audio_playback_channel";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_PLAY = "com.daven.videoplayer.PLAY";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_PAUSE = "com.daven.videoplayer.PAUSE";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String ACTION_STOP = "com.daven.videoplayer.STOP";
    @org.jetbrains.annotations.NotNull
    public static final com.daven.videoplayer.service.AudioPlaybackService.Companion Companion = null;
    
    public AudioPlaybackService() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    public int onStartCommand(@org.jetbrains.annotations.Nullable
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    public final void initializePlayer(@org.jetbrains.annotations.NotNull
    java.lang.String videoUri, @org.jetbrains.annotations.NotNull
    java.lang.String title) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final androidx.media3.exoplayer.ExoPlayer getPlayer() {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    private final android.app.Notification createNotification(boolean isPlaying) {
        return null;
    }
    
    private final android.app.PendingIntent createPendingIntent(java.lang.String action) {
        return null;
    }
    
    private final void updateNotification(boolean isPlaying) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/daven/videoplayer/service/AudioPlaybackService$AudioBinder;", "Landroid/os/Binder;", "(Lcom/daven/videoplayer/service/AudioPlaybackService;)V", "getService", "Lcom/daven/videoplayer/service/AudioPlaybackService;", "app_debug"})
    public final class AudioBinder extends android.os.Binder {
        
        public AudioBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.daven.videoplayer.service.AudioPlaybackService getService() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/daven/videoplayer/service/AudioPlaybackService$Companion;", "", "()V", "ACTION_PAUSE", "", "ACTION_PLAY", "ACTION_STOP", "CHANNEL_ID", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}