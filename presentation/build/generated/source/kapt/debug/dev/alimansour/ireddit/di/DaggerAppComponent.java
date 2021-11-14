// Generated by Dagger (https://dagger.dev).
package dev.alimansour.ireddit.di;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import dev.alimansour.data.local.LocalDataSource;
import dev.alimansour.data.local.dao.PostsDao;
import dev.alimansour.data.local.database.RedditDatabase;
import dev.alimansour.data.remote.RemoteDataSource;
import dev.alimansour.data.remote.response.RedditService;
import dev.alimansour.domain.repository.PostsRepository;
import dev.alimansour.domain.usecase.AddPostToFavoriteUseCase;
import dev.alimansour.domain.usecase.AddPostToFavoriteUseCase_Factory;
import dev.alimansour.domain.usecase.ClearFavoriteUseCase;
import dev.alimansour.domain.usecase.ClearFavoriteUseCase_Factory;
import dev.alimansour.domain.usecase.DisposeObserversUseCase;
import dev.alimansour.domain.usecase.DisposeObserversUseCase_Factory;
import dev.alimansour.domain.usecase.GetFavoritesUseCase;
import dev.alimansour.domain.usecase.GetFavoritesUseCase_Factory;
import dev.alimansour.domain.usecase.GetPostsUseCase;
import dev.alimansour.domain.usecase.GetPostsUseCase_Factory;
import dev.alimansour.domain.usecase.RemovePostFromFavoriteUseCase;
import dev.alimansour.domain.usecase.RemovePostFromFavoriteUseCase_Factory;
import dev.alimansour.domain.usecase.SearchForPostUseCase;
import dev.alimansour.domain.usecase.SearchForPostUseCase_Factory;
import dev.alimansour.ireddit.ui.MainActivity;
import dev.alimansour.ireddit.ui.favorites.FavoritesAdapter;
import dev.alimansour.ireddit.ui.favorites.FavoritesFragment;
import dev.alimansour.ireddit.ui.favorites.FavoritesFragment_MembersInjector;
import dev.alimansour.ireddit.ui.favorites.FavoritesViewModel;
import dev.alimansour.ireddit.ui.favorites.FavoritesViewModelFactory;
import dev.alimansour.ireddit.ui.favorites.FavoritesViewModelFactory_Factory;
import dev.alimansour.ireddit.ui.home.HomeFragment;
import dev.alimansour.ireddit.ui.home.HomeFragment_MembersInjector;
import dev.alimansour.ireddit.ui.home.HomeViewModel;
import dev.alimansour.ireddit.ui.home.HomeViewModelFactory;
import dev.alimansour.ireddit.ui.home.HomeViewModelFactory_Factory;
import dev.alimansour.ireddit.ui.home.PostsAdapter;
import dev.alimansour.ireddit.util.ConnectivityManager;
import dev.alimansour.ireddit.util.ConnectivityManager_Factory;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerAppComponent implements AppComponent {
  private final DaggerAppComponent appComponent = this;

  private Provider<HttpLoggingInterceptor> providesHttpLoggingInterceptorProvider;

  private Provider<OkHttpClient> providesOkHttpClientProvider;

  private Provider<Retrofit> providesRetrofitClientProvider;

  private Provider<RedditService> provideRedditServiceProvider;

  private Provider<RemoteDataSource> provideRemoteDataSourceProvider;

  private Provider<Context> contextProvider;

  private Provider<RedditDatabase> provideRedditDatabaseProvider;

  private Provider<PostsDao> providePostsDaoProvider;

  private Provider<LocalDataSource> provideLocalDataSourceProvider;

  private Provider<CompositeDisposable> providesCompositeDisposableProvider;

  private Provider<PostsRepository> providePostsRepositoryProvider;

  private Provider<GetPostsUseCase> getPostsUseCaseProvider;

  private Provider<SearchForPostUseCase> searchForPostUseCaseProvider;

  private Provider<AddPostToFavoriteUseCase> addPostToFavoriteUseCaseProvider;

  private Provider<DisposeObserversUseCase> disposeObserversUseCaseProvider;

  private Provider<HomeViewModelFactory> homeViewModelFactoryProvider;

  private Provider<ConnectivityManager> connectivityManagerProvider;

  private Provider<GetFavoritesUseCase> getFavoritesUseCaseProvider;

  private Provider<RemovePostFromFavoriteUseCase> removePostFromFavoriteUseCaseProvider;

  private Provider<ClearFavoriteUseCase> clearFavoriteUseCaseProvider;

  private Provider<FavoritesViewModelFactory> favoritesViewModelFactoryProvider;

  private DaggerAppComponent(Context contextParam) {

    initialize(contextParam);

  }

  public static AppComponent.Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Context contextParam) {
    this.providesHttpLoggingInterceptorProvider = DoubleCheck.provider(RetrofitModule_ProvidesHttpLoggingInterceptorFactory.create());
    this.providesOkHttpClientProvider = DoubleCheck.provider(RetrofitModule_ProvidesOkHttpClientFactory.create(providesHttpLoggingInterceptorProvider));
    this.providesRetrofitClientProvider = DoubleCheck.provider(RetrofitModule_ProvidesRetrofitClientFactory.create(providesOkHttpClientProvider));
    this.provideRedditServiceProvider = DoubleCheck.provider(RetrofitModule_ProvideRedditServiceFactory.create(providesRetrofitClientProvider));
    this.provideRemoteDataSourceProvider = DoubleCheck.provider(AppModule_ProvideRemoteDataSourceFactory.create(provideRedditServiceProvider));
    this.contextProvider = InstanceFactory.create(contextParam);
    this.provideRedditDatabaseProvider = DoubleCheck.provider(RoomModule_ProvideRedditDatabaseFactory.create(contextProvider));
    this.providePostsDaoProvider = DoubleCheck.provider(RoomModule_ProvidePostsDaoFactory.create(provideRedditDatabaseProvider));
    this.provideLocalDataSourceProvider = DoubleCheck.provider(AppModule_ProvideLocalDataSourceFactory.create(providePostsDaoProvider));
    this.providesCompositeDisposableProvider = DoubleCheck.provider(AppModule_ProvidesCompositeDisposableFactory.create());
    this.providePostsRepositoryProvider = DoubleCheck.provider(AppModule_ProvidePostsRepositoryFactory.create(provideRemoteDataSourceProvider, provideLocalDataSourceProvider, providesCompositeDisposableProvider));
    this.getPostsUseCaseProvider = GetPostsUseCase_Factory.create(providePostsRepositoryProvider);
    this.searchForPostUseCaseProvider = SearchForPostUseCase_Factory.create(providePostsRepositoryProvider);
    this.addPostToFavoriteUseCaseProvider = AddPostToFavoriteUseCase_Factory.create(providePostsRepositoryProvider);
    this.disposeObserversUseCaseProvider = DisposeObserversUseCase_Factory.create(providePostsRepositoryProvider);
    this.homeViewModelFactoryProvider = DoubleCheck.provider(HomeViewModelFactory_Factory.create(getPostsUseCaseProvider, searchForPostUseCaseProvider, addPostToFavoriteUseCaseProvider, disposeObserversUseCaseProvider));
    this.connectivityManagerProvider = DoubleCheck.provider(ConnectivityManager_Factory.create(contextProvider));
    this.getFavoritesUseCaseProvider = GetFavoritesUseCase_Factory.create(providePostsRepositoryProvider);
    this.removePostFromFavoriteUseCaseProvider = RemovePostFromFavoriteUseCase_Factory.create(providePostsRepositoryProvider);
    this.clearFavoriteUseCaseProvider = ClearFavoriteUseCase_Factory.create(providePostsRepositoryProvider);
    this.favoritesViewModelFactoryProvider = DoubleCheck.provider(FavoritesViewModelFactory_Factory.create(getFavoritesUseCaseProvider, removePostFromFavoriteUseCaseProvider, clearFavoriteUseCaseProvider, disposeObserversUseCaseProvider));
  }

  @Override
  public ViewModelComponent.Builder viewModelComponentBuilder() {
    return new ViewModelComponentBuilder(appComponent);
  }

  private static final class Builder implements AppComponent.Builder {
    private Context context;

    @Override
    public Builder context(Context context) {
      this.context = Preconditions.checkNotNull(context);
      return this;
    }

    @Override
    public AppComponent build() {
      Preconditions.checkBuilderRequirement(context, Context.class);
      return new DaggerAppComponent(context);
    }
  }

  private static final class ViewModelComponentBuilder implements ViewModelComponent.Builder {
    private final DaggerAppComponent appComponent;

    private Context context;

    private FragmentActivity activity;

    private ViewModelComponentBuilder(DaggerAppComponent appComponent) {
      this.appComponent = appComponent;
    }

    @Override
    public ViewModelComponentBuilder context(Context context) {
      this.context = Preconditions.checkNotNull(context);
      return this;
    }

    @Override
    public ViewModelComponentBuilder activity(FragmentActivity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ViewModelComponent build() {
      Preconditions.checkBuilderRequirement(context, Context.class);
      Preconditions.checkBuilderRequirement(activity, FragmentActivity.class);
      return new ViewModelComponentImpl(appComponent, context, activity);
    }
  }

  private static final class ViewModelComponentImpl implements ViewModelComponent {
    private final DaggerAppComponent appComponent;

    private final ViewModelComponentImpl viewModelComponentImpl = this;

    private Provider<FragmentActivity> activityProvider;

    private Provider<HomeViewModel> provideHomeViewModelProvider;

    private Provider<FavoritesViewModel> provideFavoritesViewModelProvider;

    private ViewModelComponentImpl(DaggerAppComponent appComponent, Context contextParam,
        FragmentActivity activityParam) {
      this.appComponent = appComponent;

      initialize(contextParam, activityParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final Context contextParam, final FragmentActivity activityParam) {
      this.activityProvider = InstanceFactory.create(activityParam);
      this.provideHomeViewModelProvider = DoubleCheck.provider(ViewModelModule_ProvideHomeViewModelFactory.create(activityProvider, appComponent.homeViewModelFactoryProvider));
      this.provideFavoritesViewModelProvider = DoubleCheck.provider(ViewModelModule_ProvideFavoritesViewModelFactory.create(activityProvider, appComponent.favoritesViewModelFactoryProvider));
    }

    @Override
    public void inject(MainActivity mainActivity) {
    }

    @Override
    public void inject(HomeFragment homeFragment) {
      injectHomeFragment(homeFragment);
    }

    @Override
    public void inject(FavoritesFragment favoritesFragment) {
      injectFavoritesFragment(favoritesFragment);
    }

    private HomeFragment injectHomeFragment(HomeFragment instance) {
      HomeFragment_MembersInjector.injectHomeViewModel(instance, provideHomeViewModelProvider.get());
      HomeFragment_MembersInjector.injectPostsAdapter(instance, new PostsAdapter());
      HomeFragment_MembersInjector.injectConnectivityManager(instance, appComponent.connectivityManagerProvider.get());
      return instance;
    }

    private FavoritesFragment injectFavoritesFragment(FavoritesFragment instance) {
      FavoritesFragment_MembersInjector.injectFavoritesViewModel(instance, provideFavoritesViewModelProvider.get());
      FavoritesFragment_MembersInjector.injectFavoritesAdapter(instance, new FavoritesAdapter());
      return instance;
    }
  }
}
