package com.nan.androidreview.dagger_moudle;

import dagger.Component;

@Component(modules = {HttpActivityModule.class})
public interface HttpActivityComponent {

    void inject(HttpActivity acy);

}
