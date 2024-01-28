package di

import dagger.Component
import data_source.UserApi
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent{
    //fun getNetworkService(): UserApi
}