package com.daven.videoplayer.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006$"}, d2 = {"Lcom/daven/videoplayer/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/daven/videoplayer/databinding/ActivityMainBinding;", "permissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "videoAdapter", "Lcom/daven/videoplayer/adapter/VideoAdapter;", "viewModel", "Lcom/daven/videoplayer/viewmodel/MainViewModel;", "getViewModel", "()Lcom/daven/videoplayer/viewmodel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "checkPermissionAndLoadVideos", "", "handleVideoIntent", "", "loadVideos", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "playVideo", "video", "Lcom/daven/videoplayer/model/Video;", "setupObservers", "setupRecyclerView", "setupUI", "showMoreOptions", "showPermissionDeniedMessage", "toggleFavorite", "updateEmptyState", "isEmpty", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.daven.videoplayer.databinding.ActivityMainBinding binding;
    private com.daven.videoplayer.adapter.VideoAdapter videoAdapter;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> permissionLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.daven.videoplayer.viewmodel.MainViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final boolean handleVideoIntent() {
        return false;
    }
    
    private final void setupUI() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupObservers() {
    }
    
    private final void checkPermissionAndLoadVideos() {
    }
    
    private final void loadVideos() {
    }
    
    private final void playVideo(com.daven.videoplayer.model.Video video) {
    }
    
    private final void toggleFavorite(com.daven.videoplayer.model.Video video) {
    }
    
    private final void showMoreOptions(com.daven.videoplayer.model.Video video) {
    }
    
    private final void updateEmptyState(boolean isEmpty) {
    }
    
    private final void showPermissionDeniedMessage() {
    }
}