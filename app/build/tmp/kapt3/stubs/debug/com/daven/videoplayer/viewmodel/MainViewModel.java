package com.daven.videoplayer.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\rJ\u0006\u0010\u0018\u001a\u00020\u0015J*\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\r2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00150\u001dJ\u000e\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011\u00a8\u0006\""}, d2 = {"Lcom/daven/videoplayer/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/daven/videoplayer/repository/VideoRepository;", "(Lcom/daven/videoplayer/repository/VideoRepository;)V", "_isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_videos", "", "Lcom/daven/videoplayer/model/Video;", "allVideos", "currentFilter", "", "currentQuery", "isLoading", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "videos", "getVideos", "applyCurrentFilter", "", "filterVideos", "filter", "loadVideos", "renameVideo", "video", "newName", "onResult", "Lkotlin/Function1;", "Lcom/daven/videoplayer/viewmodel/RenameResult;", "toggleFavorite", "updateSearchQuery", "query", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.daven.videoplayer.repository.VideoRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.daven.videoplayer.model.Video>> _videos = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.daven.videoplayer.model.Video>> videos = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.daven.videoplayer.model.Video> allVideos;
    @org.jetbrains.annotations.NotNull
    private java.lang.String currentFilter = "all";
    @org.jetbrains.annotations.NotNull
    private java.lang.String currentQuery = "";
    
    public MainViewModel(@org.jetbrains.annotations.NotNull
    com.daven.videoplayer.repository.VideoRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.daven.videoplayer.model.Video>> getVideos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void loadVideos() {
    }
    
    public final void filterVideos(@org.jetbrains.annotations.NotNull
    java.lang.String filter) {
    }
    
    public final void updateSearchQuery(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    private final void applyCurrentFilter() {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull
    com.daven.videoplayer.model.Video video) {
    }
    
    public final void renameVideo(@org.jetbrains.annotations.NotNull
    com.daven.videoplayer.model.Video video, @org.jetbrains.annotations.NotNull
    java.lang.String newName, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.daven.videoplayer.viewmodel.RenameResult, kotlin.Unit> onResult) {
    }
}