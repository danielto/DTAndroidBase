package cz.tokar.android.app.myarchitecture1.utils;

import android.arch.lifecycle.LiveData;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cz.tokar.android.app.myarchitecture1.network.model.base.Resource;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * Retrofit LiveData Call adapter to change retrofit response to LiveData
 */

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

  @Override
  public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
    if (getRawType(returnType) != LiveData.class) {
      return null;
    }
    Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
    Class<?> rawObservableType = getRawType(observableType);
    if (rawObservableType != Resource.class) {
      throw new IllegalArgumentException("type must be a resource");
    }
    if (!(observableType instanceof ParameterizedType)) {
      throw new IllegalArgumentException("resource must be parameterized");
    }
    Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);
    return new LiveDataCallAdapter<>(bodyType);
  }
}