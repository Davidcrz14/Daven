package com.daven.videoplayer.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/daven/videoplayer/viewmodel/RenameResult;", "", "()V", "Failure", "InvalidName", "RequiresPermission", "Success", "Lcom/daven/videoplayer/viewmodel/RenameResult$Failure;", "Lcom/daven/videoplayer/viewmodel/RenameResult$InvalidName;", "Lcom/daven/videoplayer/viewmodel/RenameResult$RequiresPermission;", "Lcom/daven/videoplayer/viewmodel/RenameResult$Success;", "app_debug"})
public abstract class RenameResult {
    
    private RenameResult() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/daven/videoplayer/viewmodel/RenameResult$Failure;", "Lcom/daven/videoplayer/viewmodel/RenameResult;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Failure extends com.daven.videoplayer.viewmodel.RenameResult {
        @org.jetbrains.annotations.Nullable
        private final java.lang.Throwable error = null;
        
        public Failure(@org.jetbrains.annotations.Nullable
        java.lang.Throwable error) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Throwable getError() {
            return null;
        }
        
        public Failure() {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Throwable component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.daven.videoplayer.viewmodel.RenameResult.Failure copy(@org.jetbrains.annotations.Nullable
        java.lang.Throwable error) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/daven/videoplayer/viewmodel/RenameResult$InvalidName;", "Lcom/daven/videoplayer/viewmodel/RenameResult;", "()V", "app_debug"})
    public static final class InvalidName extends com.daven.videoplayer.viewmodel.RenameResult {
        @org.jetbrains.annotations.NotNull
        public static final com.daven.videoplayer.viewmodel.RenameResult.InvalidName INSTANCE = null;
        
        private InvalidName() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/daven/videoplayer/viewmodel/RenameResult$RequiresPermission;", "Lcom/daven/videoplayer/viewmodel/RenameResult;", "intentSender", "Landroid/content/IntentSender;", "(Landroid/content/IntentSender;)V", "getIntentSender", "()Landroid/content/IntentSender;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class RequiresPermission extends com.daven.videoplayer.viewmodel.RenameResult {
        @org.jetbrains.annotations.NotNull
        private final android.content.IntentSender intentSender = null;
        
        public RequiresPermission(@org.jetbrains.annotations.NotNull
        android.content.IntentSender intentSender) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.content.IntentSender getIntentSender() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.content.IntentSender component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.daven.videoplayer.viewmodel.RenameResult.RequiresPermission copy(@org.jetbrains.annotations.NotNull
        android.content.IntentSender intentSender) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/daven/videoplayer/viewmodel/RenameResult$Success;", "Lcom/daven/videoplayer/viewmodel/RenameResult;", "updatedVideo", "Lcom/daven/videoplayer/model/Video;", "(Lcom/daven/videoplayer/model/Video;)V", "getUpdatedVideo", "()Lcom/daven/videoplayer/model/Video;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class Success extends com.daven.videoplayer.viewmodel.RenameResult {
        @org.jetbrains.annotations.NotNull
        private final com.daven.videoplayer.model.Video updatedVideo = null;
        
        public Success(@org.jetbrains.annotations.NotNull
        com.daven.videoplayer.model.Video updatedVideo) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.daven.videoplayer.model.Video getUpdatedVideo() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.daven.videoplayer.model.Video component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.daven.videoplayer.viewmodel.RenameResult.Success copy(@org.jetbrains.annotations.NotNull
        com.daven.videoplayer.model.Video updatedVideo) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
}