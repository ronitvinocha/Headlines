package com.byjus.headlines.di.component

import android.content.Context
import com.byjus.headlines.DescriptionActivity
import com.byjus.headlines.di.module.PiccassoModule
import com.byjus.headlines.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface DetailsComponent {
    fun inject(target: DescriptionActivity)
}