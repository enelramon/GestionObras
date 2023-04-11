package com.ucne.gestionobrasapp.data.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.gestionobrasapp.data.remote.*
import com.ucne.gestionobrasapp.data.repositoy.adelantos.AdelantosApiRepository
import com.ucne.gestionobrasapp.data.repositoy.adelantos.AdelantosApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.nominas.NominasApiRepository
import com.ucne.gestionobrasapp.data.repositoy.nominas.NominasApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.pagos.PagosApiRepository
import com.ucne.gestionobrasapp.data.repositoy.pagos.PagosApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.personas.PersonasApiRepository
import com.ucne.gestionobrasapp.data.repositoy.personas.PersonasApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.proyectos.ProyectosApiRepository
import com.ucne.gestionobrasapp.data.repositoy.proyectos.ProyectosApiRepositoryImp
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepository
import com.ucne.gestionobrasapp.data.repositoy.tipos.TiposApiRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract  fun bindProyectosRepository(impl: ProyectosApiRepositoryImp): ProyectosApiRepository

    @Binds
    abstract  fun bindPersonasRepository(impl: PersonasApiRepositoryImp): PersonasApiRepository

    @Binds
    abstract  fun bindNominasRepository(impl: NominasApiRepositoryImp): NominasApiRepository

    @Binds
    abstract  fun bindTiposRepository(impl: TiposApiRepositoryImp): TiposApiRepository

    @Binds
    abstract  fun bindPagosRepository(impl: PagosApiRepositoryImp): PagosApiRepository

    @Binds
    abstract  fun bindAdelantosRepository(impl: AdelantosApiRepositoryImp): AdelantosApiRepository

}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    /* Proyectos */

    @Singleton
    @Provides
    fun providesProyectosApi(moshi: Moshi): ProyectosApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionobraswebapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ProyectosApi::class.java)
    }

    /* Personas */

    @Singleton
    @Provides
    fun providesPersonasApi(moshi: Moshi): PersonasApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionobraswebapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PersonasApi::class.java)
    }

    /* Nominas */

    @Singleton
    @Provides
    fun providesNominasApi(moshi: Moshi): NominasApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionobraswebapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(NominasApi::class.java)
    }

    /* Tipos */

    @Singleton
    @Provides
    fun providesTiposApi(moshi: Moshi): TiposApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionobraswebapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TiposApi::class.java)
    }

    /* Adelantos */

    @Singleton
    @Provides
    fun providesAdelantosApi(moshi: Moshi): AdelantosApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionobraswebapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AdelantosApi::class.java)
    }

    /* Pagos */

    @Singleton
    @Provides
    fun providesPagosApi(moshi: Moshi): PagosApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionobraswebapi.azurewebsites.net")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PagosApi::class.java)
    }
}