package com.example.paranikontrolet.di

import com.example.paranikontrolet.data.repository.FirebaseAuthenticatorImpl
import com.example.paranikontrolet.data.repository.FirebaseFirestoreDatabaseImpl
import com.example.paranikontrolet.domain.repository.FirebaseAuthenticator
import com.example.paranikontrolet.domain.repository.FirebaseFirestoreDatabase
import com.example.paranikontrolet.domain.usecase.FirebaseAuthUseCases
import com.example.paranikontrolet.domain.usecase.FirebaseFirestoreUseCases
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.GetCurrentUserUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.SignInUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.SignOutUseCase
import com.example.paranikontrolet.domain.usecase.firebase_auth_usecase.SignUpUseCase
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.GetBudgetFromFirestoreUseCase
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.SaveBudgetUseCase
import com.example.paranikontrolet.domain.usecase.firebase_firestore_usecase.SaveUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseUser() = Firebase.auth
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseAuthenticator(auth: FirebaseAuth): FirebaseAuthenticator {
        return FirebaseAuthenticatorImpl(auth)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDatabase(firestore: FirebaseFirestore): FirebaseFirestoreDatabase {
        return FirebaseFirestoreDatabaseImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthUseCases(auth: FirebaseAuthenticator): FirebaseAuthUseCases {
        return FirebaseAuthUseCases(
            getCurrentUserUseCase = GetCurrentUserUseCase(auth = auth),
            signInUseCase = SignInUseCase(auth = auth),
            signOutUseCase = SignOutUseCase(auth = auth),
            signUpUseCase = SignUpUseCase(auth = auth)
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreUseCases(
        firestore: FirebaseFirestoreDatabase): FirebaseFirestoreUseCases {

        return FirebaseFirestoreUseCases(
            getBudgetFromFirestoreUseCase = GetBudgetFromFirestoreUseCase(firestore = firestore) ,
            saveBudgetUseCase = SaveBudgetUseCase(firestore = firestore),
            saveUserUseCase = SaveUserUseCase(firestore = firestore)
        )
    }


}